package views;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.mapsdemo.R;

/**
 * Created by iconflux-chuni on 8/25/2015.
 */
public class DirectionActivity extends FragmentActivity {

    private SupportMapFragment mapFragment;
    private GoogleMap googleMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mymap);
        googleMap = mapFragment.getMap();
        googleMap.setMyLocationEnabled(true);
//        mapFragment.getMapAsync(this);


        /**
         * Adds a marker to the map.
         */


    }
}
