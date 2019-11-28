package com.example.beepbuddy.view;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.beepbuddy.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.JsonObject;
import com.mapbox.api.geocoding.v5.models.CarmenFeature;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.plugins.places.autocomplete.PlaceAutocomplete;
import com.mapbox.mapboxsdk.plugins.places.autocomplete.model.PlaceOptions;
import com.mapbox.mapboxsdk.style.layers.PropertyFactory;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;

import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconImage;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconOffset;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {
    private static final int REQUEST_CODE_AUTOCOMPLETE = 1;
    private static final int PERMISSION_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean mLocationPermissionGranted;
    private Location mLastKnownLocation;
    private static final String TAG = "MapsActivity";
    private FusedLocationProviderClient fusedLocationClient;
    private MapView mapView;
    private MapboxMap mapboxMap;
    double latitude, longitude;
    private LatLng currentPosition = new LatLng(43.4675, -79.6877);
    private GeoJsonSource geoJsonSource;
    private CarmenFeature home;
    private CarmenFeature work;
    private String geojsonSourceLayerId = "geojsonSourceLayerId";
    private String symbolIconId = "symbolIconId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, "pk.eyJ1Ijoic3V6aXJ5dSIsImEiOiJjazNpNDV1amcwNGV4M2R0NzlieXcwNXYwIn0.7hqHZLhiW1n5xeHI33VpXw");
        setContentView(R.layout.activity_maps);

        getLocationPermission();
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        getDeviceLocation();

        setContentView(R.layout.activity_maps);

        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull final MapboxMap mapboxMap) {
        this.mapboxMap = mapboxMap;
                LatLng currentPosition = new LatLng(latitude, longitude);

                geoJsonSource = new GeoJsonSource("source-id",
                        Feature.fromGeometry(Point.fromLngLat(currentPosition.getLongitude(),
                                currentPosition.getLatitude())));

                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {

                        style.addImage(("marker_icon"), BitmapFactory.decodeResource(
                                getResources(), R.drawable.marker));

                        style.addSource(geoJsonSource);

                        style.addLayer(new SymbolLayer("layer-id", "source-id")
                                .withProperties(
                                        iconImage("marker_icon"),
                                        PropertyFactory.iconIgnorePlacement(true),
                                        PropertyFactory.iconAllowOverlap(true)
                                ));

                        CameraPosition position = new CameraPosition.Builder()
                                .target(new LatLng(latitude, longitude))
                                .zoom(16)
                                .tilt(0)
                                .build();
                        // Map is set up and the style has loaded. Now you can add data or make other map adjustments

                        mapboxMap.moveCamera(CameraUpdateFactory.newCameraPosition(position));

                        initSearchFab();
                        addUserLocations();

                        // Create an empty GeoJSON source using the empty feature collection
                        setUpSource(style);


                        style.addImage(symbolIconId, BitmapFactory.decodeResource(
                                MapsActivity.this.getResources(), R.drawable.marker));
                        // Set up a new symbol layer for displaying the searched location's feature coordinates
                        setupLayer(style);

                    }
                });}



    private void initSearchFab() {
        findViewById(R.id.fab_location_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new PlaceAutocomplete.IntentBuilder()
                        .accessToken(Mapbox.getAccessToken() != null ? Mapbox.getAccessToken() : "pk.eyJ1Ijoic3V6aXJ5dSIsImEiOiJjazNpNDV1amcwNGV4M2R0NzlieXcwNXYwIn0.7hqHZLhiW1n5xeHI33VpXw")
                        .placeOptions(PlaceOptions.builder()
                                .backgroundColor(Color.parseColor("#EEEEEE"))
                                .limit(10)
                                .addInjectedFeature(home)
                                .addInjectedFeature(work)
                                .build(PlaceOptions.MODE_CARDS))
                        .build(MapsActivity.this);
                startActivityForResult(intent, REQUEST_CODE_AUTOCOMPLETE);
            }
        });
    }

    private void addUserLocations() {
        home = CarmenFeature.builder().text("Mapbox SF Office")
                .geometry(Point.fromLngLat(-122.3964485, 37.7912561))
                .placeName("50 Beale St, San Francisco, CA")
                .id("mapbox-sf")
                .properties(new JsonObject())
                .build();

        work = CarmenFeature.builder().text("Mapbox DC Office")
                .placeName("740 15th Street NW, Washington DC")
                .geometry(Point.fromLngLat(-77.0338348, 38.899750))
                .id("mapbox-dc")
                .properties(new JsonObject())
                .build();
    }

    private void setUpSource(@NonNull Style loadedMapStyle) {
        loadedMapStyle.addSource(new GeoJsonSource(geojsonSourceLayerId));
    }

    private void setupLayer(@NonNull Style loadedMapStyle) {
        loadedMapStyle.addLayer(new SymbolLayer("SYMBOL_LAYER_ID", geojsonSourceLayerId).withProperties(
                iconImage(symbolIconId),
                iconOffset(new Float[] {0f, -8f})
        ));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_AUTOCOMPLETE) {

// Retrieve selected location's CarmenFeature
            CarmenFeature selectedCarmenFeature = PlaceAutocomplete.getPlace(data);

// Create a new FeatureCollection and add a new Feature to it using selectedCarmenFeature above.
// Then retrieve and update the source designated for showing a selected location's symbol layer icon

            if (mapboxMap != null) {
                Style style = mapboxMap.getStyle();
                if (style != null) {
                    GeoJsonSource source = style.getSourceAs(geojsonSourceLayerId);
                    if (source != null) {
                        source.setGeoJson(FeatureCollection.fromFeatures(
                                new Feature[] {Feature.fromJson(selectedCarmenFeature.toJson())}));
                    }

// Move map camera to the selected location
                    mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(
                            new CameraPosition.Builder()
                                    .target(new LatLng(((Point) selectedCarmenFeature.geometry()).latitude(),
                                            ((Point) selectedCarmenFeature.geometry()).longitude()))
                                    .zoom(14)
                                    .build()), 4000);
                }
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }


    private void getLocationPermission() {
        //check if for the current app permission for accessing fine location is granted
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            //request for permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSION_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    private void getDeviceLocation() {
        //get current location of the device

        try {
            if (mLocationPermissionGranted) {
                Task<Location> locationResult;
                locationResult = fusedLocationClient.getLastLocation();

                locationResult.addOnCompleteListener(this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        //check if fetching the last location was successful or not
                        if (task.isSuccessful()) {
                            //means we got the last location and now we set it on our map and
                            // then move the camera accordingly

                            mLastKnownLocation = task.getResult();

                            LatLng currentLocation = new LatLng(mLastKnownLocation.getLatitude(),
                                    mLastKnownLocation.getLongitude());

                            latitude = mLastKnownLocation.getLatitude();
                            longitude = mLastKnownLocation.getLongitude();


                            Log.e(TAG, "Lat : " + currentLocation.getLatitude() +
                                    " Lng: " + currentLocation.getLongitude());
                        }
                    }
                });
            }
        } catch (SecurityException e) {
            Log.e(TAG + "Exception", e.getMessage());
        }
    }

}