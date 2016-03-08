package net.infobosccoma.titulars.views.impl.activities;

import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import net.infobosccoma.titulars.models.business.entities.Titular;
import net.infobosccoma.titulars.network.Response;
import net.infobosccoma.titulars.network.TitularsRetrofitSpiceRequest;
import net.infobosccoma.titulars.presenters.impl.MainViewPresenterImpl;
import net.infobosccoma.titulars.presenters.interfaces.IMainViewPresenter;
import net.infobosccoma.titulars.views.impl.adapters.TitularsAdapter;

import net.infobosccoma.titulars.views.interfaces.IMainView;
import net.infobosccoma.titularscloud.R;

import java.util.List;


public class MainActivity extends BaseActivity implements IMainView, AdapterView.OnItemClickListener {

    // ============================================================================================
    // ATTRIBUTES
    // ============================================================================================
    private TitularsAdapter adapter;
    private ListView listTitulars;
    private ProgressBar progressBar;
    private TitularsRetrofitSpiceRequest titularsRequest;
    private IMainViewPresenter presenter;
    private ListTitularsListener listTitularsListener;
    private UpdateTitularsListener updateTitularsListener;

    // ============================================================================================
    // ACTIVITY LIFE CYCLE
    // ============================================================================================

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_PROGRESS);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        listTitulars = (ListView) findViewById(R.id.listTitulars);
        listTitularsListener = new ListTitularsListener();
        updateTitularsListener = new UpdateTitularsListener();

        presenter = new MainViewPresenterImpl();
        presenter.onCreate(this);

        registerForContextMenu(listTitulars);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.getTitularsFromService();
    }


    // ============================================================================================
    // INTERFACE IMPLEMENTED METHODS
    // ============================================================================================

    @Override
    public void createList(TitularsAdapter adapter) {
        listTitulars.setAdapter(adapter);
        listTitulars.setOnItemClickListener(this);
    }

    @Override
    public TitularsAdapter createTitularsAdapter(List<Titular> titulars) {
        return new TitularsAdapter(this, titulars);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showList() {
        listTitulars.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideList() {
        listTitulars.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    // ============================================================================================
    // PRIVATE METHODS
    // ============================================================================================

    private void request() {
        showProgressBar();
        getSpiceManager().execute(titularsRequest, "titulars", DurationInMillis.ONE_MINUTE, new ListTitularsListener());
    }


    /**
     * Actualitzar la llista amb les dades de la llista
     *
     * @param dades la llista de titulars a mostrar
     */
    private void updateTitulars(Titular.Llista dades) {
        if (dades == null) {
            return;
        }
        adapter = new TitularsAdapter(this, dades);
        listTitulars.setAdapter(adapter);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_refresh) {
            presenter.getTitularsFromService();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_contextual_main, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_delete) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Titular removeObject = (Titular) listTitulars.getItemAtPosition(info.position);

            presenter.delete(removeObject);
            return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public RequestListener<Titular.Llista> getListListener() {
        return listTitularsListener;
    }

    @Override
    public RequestListener<Response> getUpdateListener() {
        return updateTitularsListener;
    }
    // ============================================================================================
    // INNER CLASS
    // ============================================================================================

    /**
     * Implements a Listener class to manage received data
     */
    public final class ListTitularsListener implements RequestListener<Titular.Llista> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            hideProgressBar();
            Toast.makeText(MainActivity.this, R.string.download_error, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRequestSuccess(Titular.Llista result) {
            Toast.makeText(MainActivity.this, "success ", Toast.LENGTH_SHORT).show();
            presenter.setTitulars(result);
            presenter.showList();
        }
    }

    /**
     * Implements a Listener class to manage received data
     */
    public final class UpdateTitularsListener implements RequestListener<Response> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            hideProgressBar();
            Toast.makeText(MainActivity.this, R.string.download_error, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRequestSuccess(Response response) {
            hideProgressBar();
            Toast.makeText(MainActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
            if(response.getStatus()) {
                presenter.getTitularsFromService();
            }
        }

    }

}
