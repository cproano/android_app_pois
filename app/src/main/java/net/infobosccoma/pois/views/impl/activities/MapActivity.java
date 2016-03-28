package net.infobosccoma.pois.views.impl.activities;

import android.location.Location;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import net.infobosccoma.pois.models.business.entities.Pois;
import net.infobosccoma.poiscloud.R;

import org.parceler.Parcels;

import java.util.List;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Pois poi;
    private List<Pois> pois;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.setMyLocationEnabled(true);

        if(poi == null){
            for(Pois punt : pois){
                // Add a marker in Sydney and move the camera
                LatLng latlang = new LatLng(Double.parseDouble(punt.getLatitude()),Double.parseDouble(punt.getLongitude()));
                mMap.addMarker(new MarkerOptions().position(latlang).title(punt.getName()));
            }
        }else{
            // Add a marker in Sydney and move the camera
            LatLng pos = new LatLng(Double.parseDouble(poi.getLatitude()),Double.parseDouble(poi.getLongitude()));
            mMap.addMarker(new MarkerOptions().position(pos).title(poi.getName()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(pos));

        }

    }
}
