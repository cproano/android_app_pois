package net.infobosccoma.pois.views.impl.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import net.infobosccoma.pois.models.business.entities.Pois;
import net.infobosccoma.pois.network.Response;
import net.infobosccoma.pois.network.PoisRetrofitSpiceRequest;
import net.infobosccoma.pois.presenters.impl.MainViewPresenterImpl;
import net.infobosccoma.pois.presenters.interfaces.IMainViewPresenter;
import net.infobosccoma.pois.views.impl.adapters.PoisAdapter;

import net.infobosccoma.pois.views.interfaces.IMainView;
import net.infobosccoma.poiscloud.R;

import org.parceler.Parcels;

import java.util.List;


public class MainActivity extends BaseActivity implements IMainView, AdapterView.OnItemClickListener, View.OnClickListener, OnMapReadyCallback {

    // ============================================================================================
    // ATTRIBUTES
    // ============================================================================================
    private PoisAdapter adapter;
    private GoogleMap mMap;
    private ListView listPois;
    private Button cercar;
    private EditText text;
    private ProgressBar progressBar;
    private PoisRetrofitSpiceRequest poisRequiest;
    private IMainViewPresenter presenter;
    private ListPoisListener listPoisListener;
    private UpdateTitularsListener updateTitularsListener;
    private List<Pois> pois;

    // ============================================================================================
    // ACTIVITY LIFE CYCLE
    // ============================================================================================

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_PROGRESS);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        //progressBar = (ProgressBar) findViewById(R.id.progressBar);
        listPois = (ListView) findViewById(R.id.pois_list);
        cercar = (Button) findViewById(R.id.action_search);
        text = (EditText) findViewById(R.id.action_search_city);
        //onSon = (Button) findViewById(R.id.action_locate_pois);
        cercar.setOnClickListener(this);
        //onSon.setOnClickListener(this);
        listPoisListener = new ListPoisListener();
        updateTitularsListener = new UpdateTitularsListener();

        listPois.setAdapter(adapter);
        presenter = new MainViewPresenterImpl();
        presenter.onCreate(this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        registerForContextMenu(listPois);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }


    // ============================================================================================
    // INTERFACE IMPLEMENTED METHODS
    // ============================================================================================

    @Override
    public void createList(PoisAdapter adapter) {
        listPois.setAdapter(adapter);
        listPois.setOnItemClickListener(this);
    }

    @Override
    public PoisAdapter createTitularsAdapter(List<Pois> pois) {
        return new PoisAdapter(this, pois);
    }

    @Override
    public void showList() {

    }

/*    @Override
    public void showProgressBar() {
//        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
//        progressBar.setVisibility(View.GONE);
    }*/


    @Override
    public void goToDetailActivity(Pois pois) {
        Intent i = new Intent(this.getBaseContext(), MapActivity.class);
        i.putExtra("poi", Parcels.wrap(pois));
        startActivityForResult(i, 0);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        presenter.onItemClicked(position);
    }

    // ============================================================================================
    // PRIVATE METHODS
    // ============================================================================================

    private void request() {
        //showProgressBar();
        getSpiceManager().execute(poisRequiest, "Pois", DurationInMillis.ONE_MINUTE, new ListPoisListener());
    }

    public void moveCamera(Pois pois) {
        LatLng pos = new LatLng(Double.parseDouble(pois.getLatitude()),Double.parseDouble(pois.getLongitude()));
        mMap.addMarker(new MarkerOptions().position(pos).title(pois.getName()));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pos, 15), 2000,null);
    }

    @Override
    public void updateListOfPois(Pois.Llista result) {

        mMap.clear();
        for(Pois pois : result){
            LatLng pos = new LatLng(Double.parseDouble(pois.getLatitude()),Double.parseDouble(pois.getLongitude()));
            mMap.addMarker(new MarkerOptions().position(pos).title(pois.getName()));
        }
    }

    /**
     * Actualitzar la llista amb les dades de la llista
     *
     * @param dades la llista de titulars a mostrar
     */
    private void updateTitulars(Pois.Llista dades) {
        if (dades == null) {
            return;
        }
        adapter = new PoisAdapter(this, dades);
        listPois.setAdapter(adapter);
    }

    // ============================================================================================
    // PUBLIC METHODS
    // ============================================================================================

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_contextual_main, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_delete) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Pois removeObject = (Pois) listPois.getItemAtPosition(info.position);

            presenter.delete(removeObject);
            return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public RequestListener<Pois.Llista> getListListener() {
        return listPoisListener;
    }

    @Override
    public RequestListener<Response> getUpdateListener() {
        return updateTitularsListener;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.action_search) {

            if (text.getText().toString().equals("")) {

                presenter.getAllPoisFromService();
            } else {

                presenter.getPoisFromServiceByText(text.getText().toString());

            }
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.setMyLocationEnabled(true);


        //mMap.moveCamera(CameraUpdateFactory.newLatLng(pos));

    }



// ============================================================================================
// INNER CLASS
// ============================================================================================

/**
 * Implements a Listener class to manage received data
 */
public final class ListPoisListener implements RequestListener<Pois.Llista> {

    @Override
    public void onRequestFailure(SpiceException spiceException) {
        //hideProgressBar();
        Toast.makeText(MainActivity.this, R.string.download_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestSuccess(Pois.Llista result) {
        Toast.makeText(MainActivity.this, "Punts d'interès descarregats. ", Toast.LENGTH_SHORT).show();
        presenter.setPois(result);
        presenter.updatePoisOfMain(result);
        presenter.showList();
    }
}

/**
 * Implements a Listener class to manage received data
 */
public final class UpdateTitularsListener implements RequestListener<Response> {

    @Override
    public void onRequestFailure(SpiceException spiceException) {
        // hideProgressBar();
        Toast.makeText(MainActivity.this, R.string.download_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestSuccess(Response response) {
        //hideProgressBar();
        Toast.makeText(MainActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
        if (response.getStatus()) {
            presenter.getAllPoisFromService();
        }
    }

}

}
