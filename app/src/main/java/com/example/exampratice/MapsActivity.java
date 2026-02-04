package com.example.exampratice;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Get the map fragment
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add polyline (Adelaide to Sydney route - approximate)
        mMap.addPolyline(new PolylineOptions()
                .clickable(true)                    // makes polyline tappable
                .color(0xFF0000FF)                  // Blue color
                .width(10f)                         // thicker line
                .add(
                        new LatLng(-34.9285, 138.6007),  // Adelaide
                        new LatLng(-34.0, 140.0),           // intermediate point
                        new LatLng(-33.8, 145.0),
                        new LatLng(-33.6, 148.0),
                        new LatLng(-33.8688, 151.2093)      // Sydney
                ));

        // Optional: Add markers at start and end
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-34.9285, 138.6007))
                .title("Adelaide")
                .snippet("Start of route"));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-33.8688, 151.2093))
                .title("Sydney")
                .snippet("End of route"));

        // Move camera to show the whole route nicely
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(-34.0, 145.0),  // roughly center of the route
                5.5f));                    // zoom level to see most of the path
    }

    // This method is called when a button with android:onClick="takeMeSomeWhere" is clicked
    public void takeMeSomeWhere(View view) {
        LatLng kathmandu = new LatLng(27.7103, 85.3222);

        // Add marker in Kathmandu
        mMap.addMarker(new MarkerOptions()
                .position(kathmandu)
                .title("You are in Kathmandu")
                .snippet("Capital city of Nepal"));

        // Animate camera to Kathmandu
        mMap.animateCamera(
                CameraUpdateFactory.newLatLngZoom(kathmandu, 12f),  // zoom 12 = good city view
                2000,  // 2 seconds animation
                new GoogleMap.CancelableCallback() {
                    @Override
                    public void onFinish() {
                        Toast.makeText(getApplicationContext(),
                                "Welcome to Kathmandu!", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancel() {
                        // animation cancelled - optional
                    }
                });
    }
}