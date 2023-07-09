package com.example.pasi.abwaylinetest;

import android.content.Context;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.location.LocationListener;

import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private LocationManager locationManager;
    private LocationListener gpsLocationListener;

//    private FusedLocationProviderClient mFusedLocationClient;
    private LocationRequest mLocationRequest;
    private Location lastKnownLocation;
    private long lastKnownLocationTimeStamp;
    private Location locationAPoint;
    private Location locationBPoint;
    private Timer updateTimer;
//    private int dbg_NumOfSimultaneousLocations = 0;
//    private int dbg_MaxNumOfSimultaneousLocations;

    /*
    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            if (locationResult == null) {
                lastKnownLocation = null;
                updateFields();
                return;
            }

            Location mostAccurateLocation = null;
            double bestAccuracyValue = 9999;

            boolean mockProviderFound = false;

            dbg_NumOfSimultaneousLocations = 0;

            for (Location location : locationResult.getLocations()) {
                // Update UI with location data

                dbg_NumOfSimultaneousLocations++;

                if (location.isFromMockProvider())
                {
                    // Prioritize mock provider over anything else
                    // (provider seems to always be "fused" here...)
                    if (location.hasAccuracy()) {
                        if ((location.getAccuracy() < bestAccuracyValue) || !mockProviderFound) {
                            mostAccurateLocation = location;
                            bestAccuracyValue = location.getAccuracy();
                        } else if (mostAccurateLocation == null) {
                            mostAccurateLocation = location;
                        }
                    } else if (bestAccuracyValue == 9999) {
                        mostAccurateLocation = location;
                    }

                    mockProviderFound = true;
                }

                if (location.hasAccuracy()) {
                    if (!mockProviderFound) {
                        if (location.getAccuracy() < bestAccuracyValue) {
                            mostAccurateLocation = location;
                            bestAccuracyValue = location.getAccuracy();
                        } else if (mostAccurateLocation == null) {
                            mostAccurateLocation = location;
                        }
                    }
                } else if ((bestAccuracyValue == 9999) && !mockProviderFound) {
                    mostAccurateLocation = location;
                }
            }

            if (dbg_NumOfSimultaneousLocations > 0) {
                // This is differentiated just for debugging purposes...
                if (dbg_NumOfSimultaneousLocations > dbg_MaxNumOfSimultaneousLocations)
                {
                    dbg_MaxNumOfSimultaneousLocations = dbg_NumOfSimultaneousLocations;
                }
            }

            if (mostAccurateLocation != null) {
                lastKnownLocation = mostAccurateLocation;
                lastKnownLocationTimeStamp = android.os.SystemClock.uptimeMillis();
                updateFields();
            } else {
                lastKnownLocation = null;
                updateFields();
            }
        }

        ;
    };
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (gpsLocationListener == null) {
            gpsLocationListener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    lastKnownLocation = location;
                    lastKnownLocationTimeStamp = android.os.SystemClock.uptimeMillis();
                    updateFields();
                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            };
        }

        if (locationManager == null) {
            locationManager = getSystemService(LocationManager.class);
        }

//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, (float) 0.0001, gpsLocationListener);

//        LocationProvider gpsProvider = locationManager.getProvider(LocationManager.GPS_PROVIDER);











        setContentView(R.layout.activity_main);

//        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
//        createLocationRequest();
/*
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);

//        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();

        SettingsClient client = LocationServices.getSettingsClient(this);
        Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());

        task.addOnSuccessListener(this, new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                // All location settings are satisfied. The client can initialize
                // location requests here.
                // ...

//                startLocationUpdates();
            }
        });

        task.addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (e instanceof ResolvableApiException) {
                    // Location settings are not satisfied, but this can be fixed
                    // by showing the user a dialog.
                    try {
                        // Show the dialog by calling startResolutionForResult(),
                        // and check the result in onActivityResult().
                        ResolvableApiException resolvable = (ResolvableApiException) e;
                        resolvable.startResolutionForResult(MainActivity.this,
                                1337); //REQUEST_CHECK_SETTINGS);
                    } catch (IntentSender.SendIntentException sendEx) {
                        // Ignore the error.
                    }
                }
            }
        });
*/

        /*        if ( ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {

            ActivityCompat.requestPermissions( this, new String[] {  android.Manifest.permission.ACCESS_FINE_LOCATION  },
                    LocationService.MY_PERMISSION_ACCESS_COURSE_LOCATION );

        }

        if ( Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission( context, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission( context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return  ;
        }
        */
//        startLocationUpdates();

        ((Button) findViewById(R.id.button_SetAPoint)).setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                locationAPoint = lastKnownLocation;
                updateFields();
            }
        });

        ((Button) findViewById(R.id.button_SetBPoint)).setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                locationBPoint = lastKnownLocation;
                updateFields();
            }
        });

        updateFields();

        if (savedInstanceState != null) {
            if ((savedInstanceState.containsKey("Latitude_A")) &&
                    (savedInstanceState.containsKey("Longitude_A"))) {
                locationAPoint = new Location("");
                locationAPoint.setLatitude(savedInstanceState.getDouble("Latitude_A"));
                locationAPoint.setLongitude(savedInstanceState.getDouble("Longitude_A"));
            }

            if ((savedInstanceState.containsKey("Latitude_B")) &&
                    (savedInstanceState.containsKey("Longitude_B"))) {
                locationBPoint = new Location("");
                locationBPoint.setLatitude(savedInstanceState.getDouble("Latitude_B"));
                locationBPoint.setLongitude(savedInstanceState.getDouble("Longitude_B"));
            }
        }
        else {
            loadPreferences();
        }
    }

    private Runnable timerUpdate = new Runnable() {
        @Override
        public void run() {
            updateFields();
        }
    };

/*    protected void createLocationRequest() {
//        mLocationRequest = new LocationRequest();

        // Let's try if 1500 (instead of 1000) removed occasional hops
        // Could it be that mock provider sometimes gives positions in just over 1 second interval
        // and fused provider falls to some lower resolution provider (or something)
        mLocationRequest.setInterval(1500);
        mLocationRequest.setFastestInterval(500);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }
*/

    @Override
    protected void onPause() {
        super.onPause();
        updateTimer.cancel();
        updateTimer = null;
        savePreferences();
        stopLocationUpdates();
    }

    @Override
    protected void onResume() {
        super.onResume();
        startLocationUpdates();

        updateTimer = new Timer();
        updateTimer.schedule(new TimerTask() {
            //            @override
            public void run() {
                runOnUiThread(timerUpdate);
            }
        }, 100, 100);
    }


    private void startLocationUpdates() {
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, (float) 0.0001, gpsLocationListener);
/*        mFusedLocationClient.requestLocationUpdates(mLocationRequest,
                mLocationCallback,
                null);
*/
    }

    private void stopLocationUpdates() {
        locationManager.removeUpdates(gpsLocationListener);
//        mFusedLocationClient.removeLocationUpdates(mLocationCallback);
    }

    private void updateFields() {
        TextView locationAge = (TextView) findViewById(R.id.location_age);

        TextView accuracy = (TextView) findViewById(R.id.accuracy);
        TextView latitude = (TextView) findViewById(R.id.latitude);
        TextView longitude = (TextView) findViewById(R.id.longitude);

        TextView latitudeA = (TextView) findViewById(R.id.latitude_A);
        TextView longitudeA = (TextView) findViewById(R.id.longitude_A);

        TextView latitudeB = (TextView) findViewById(R.id.latitude_B);
        TextView longitudeB = (TextView) findViewById(R.id.longitude_B);

        TextView distanceA = (TextView) findViewById(R.id.distanceA);
        TextView distanceB = (TextView) findViewById(R.id.distanceB);
        TextView distanceAB = (TextView) findViewById(R.id.distanceAB);
        TextView distanceToWayline = (TextView) findViewById(R.id.distanceToABWayline);

        ProgressBar distanceToWayline_Left = (ProgressBar) findViewById(R.id.progressBar_DiffLeft);
        ProgressBar distanceToWayline_Right = (ProgressBar) findViewById(R.id.progressBar_DiffRight);

        TextView provider = (TextView) findViewById(R.id.provider);
        TextView debug1 = (TextView) findViewById(R.id.debug1);
        TextView debug2 = (TextView) findViewById(R.id.debug2);
        TextView debug3 = (TextView) findViewById(R.id.debug3);
        TextView debug4 = (TextView) findViewById(R.id.debug4);

        if (lastKnownLocation != null) {
            double age = (android.os.SystemClock.uptimeMillis() - lastKnownLocationTimeStamp) / 1000.;

            locationAge.setText("Age: " + String.format("%.1f", age) + " s");
        }
        else {
            locationAge.setText("Age: N/A");
        }


        // Location doesn't seem to necessarily be updated with every GNSS-update if location doesn't change
        // -> Do not invalidate location regardless of it's age
//        if ((lastKnownLocation != null) && (android.os.SystemClock.uptimeMillis() - lastKnownLocationTimeStamp < (5000)) {
        if (lastKnownLocation != null) {
            if (lastKnownLocation.hasAccuracy()) {
                accuracy.setText("Accuracy: " + String.format("%.3f", lastKnownLocation.getAccuracy()) + " m");
            } else {
                accuracy.setText("Accuracy: N/A");
            }

            latitude.setText("Latitude: " + String.format("%.8f", lastKnownLocation.getLatitude()));
            longitude.setText("Longitude: " + String.format("%.8f", lastKnownLocation.getLongitude()));

            EarthSurfaceVector3D surfaceVector3D = new EarthSurfaceVector3D(lastKnownLocation);

            if (locationAPoint != null) {
                distanceA.setText("Distance to A: " + String.format("%.3f", surfaceVector3D.getDistance(locationAPoint)) + " m");
            } else {
                distanceA.setText("Distance to A: N/A");
            }

            if (locationBPoint != null) {
                distanceB.setText("Distance to B: " + String.format("%.3f", surfaceVector3D.getDistance(locationBPoint)) + " m");
            } else {
                distanceB.setText("Distance to B: N/A");
            }

            if ((locationAPoint != null) && (locationBPoint != null)) {
                EarthSurfaceVector3D surfaceVector3DAPoint = new EarthSurfaceVector3D(locationAPoint);
                EarthSurfaceVector3D surfaceVector3DBPoint = new EarthSurfaceVector3D(locationBPoint);
                EarthSurfaceVector3D surfaceVectorLocation = new EarthSurfaceVector3D(lastKnownLocation);

                Vector3D vecAPoint = new Vector3D(surfaceVector3DAPoint);
                Vector3D vecBPoint = new Vector3D(surfaceVector3DBPoint);
                Vector3D vecLocation = new Vector3D(surfaceVectorLocation);

                Vector3D crossVec = vecAPoint.crossProduct(vecBPoint);

                crossVec.normalize();

                debug1.setText("crossX: " + Double.toString(crossVec.x));
                debug2.setText("crossY: " + Double.toString(crossVec.y));
                debug3.setText("crossZ: " + Double.toString(crossVec.z));

                if (crossVec.length() != 0) {
                    double dotProd = -crossVec.dotProduct(vecLocation);

                    distanceToWayline.setText(String.format("%.3f", dotProd) + " m");

                    if (dotProd < -1) {
                        dotProd = -1;
                    } else if (dotProd > 1) {
                        dotProd = 1;
                    }

                    if (dotProd < 0) {
                        distanceToWayline_Left.setProgress((int) (1000 * (-dotProd)));
                        distanceToWayline_Right.setProgress(0);
                    } else {
                        distanceToWayline_Left.setProgress(0);
                        distanceToWayline_Right.setProgress((int) (1000 * (dotProd)));
                    }

                } else {
                    distanceToWayline.setText("N/A");
                    distanceToWayline_Left.setProgress(1000);
                    distanceToWayline_Right.setProgress(1000);
                }
            } else {
                distanceToWayline.setText("N/A");
                distanceToWayline_Left.setProgress(1000);
                distanceToWayline_Right.setProgress(1000);
            }

            provider.setText("Provider: " + lastKnownLocation.getProvider());

/*
            debug1.setText("X: " + Double.toString(surfaceVector3D.getX()));
            debug2.setText("Y: " + Double.toString(surfaceVector3D.getY()));
            debug3.setText("Z: " + Double.toString(surfaceVector3D.getZ()));
*/
        } else {
            accuracy.setText("Accuracy: N/A");
            latitude.setText("Latitude: N/A");
            longitude.setText("Longitude: N/A");
            distanceA.setText("Distance to A: N/A");
            distanceB.setText("Distance to B: N/A");
            distanceToWayline.setText("N/A");
            distanceToWayline_Left.setProgress(1000);
            distanceToWayline_Right.setProgress(1000);
            provider.setText("Provider: N/A");
        }

        if (locationAPoint != null) {
            latitudeA.setText("Latitude A: " + String.format("%.8f", locationAPoint.getLatitude()));
            longitudeA.setText("Longitude A: " + String.format("%.8f", locationAPoint.getLongitude()));
        } else {
            latitudeA.setText("Latitude A: N/A");
            longitudeA.setText("Longitude A: N/A");
        }

        if (locationBPoint != null) {
            latitudeB.setText("Latitude B: " + String.format("%.8f", locationBPoint.getLatitude()));
            longitudeB.setText("Longitude B: " + String.format("%.8f", locationBPoint.getLongitude()));
        } else {
            latitudeB.setText("Latitude B: N/A");
            longitudeB.setText("Longitude B: N/A");
        }

        if ((locationAPoint != null) && (locationBPoint != null)) {
            EarthSurfaceVector3D surfaceVector3DBPoint = new EarthSurfaceVector3D(locationBPoint);
            distanceAB.setText("Distance from A to B: " + String.format("%.3f", surfaceVector3DBPoint.getDistance(locationAPoint)) + " m");
        } else {
            distanceAB.setText("Distance from A to B: N/A");
        }

//        debug4.setText("Locations: " + dbg_NumOfSimultaneousLocations + ", max: " + dbg_MaxNumOfSimultaneousLocations);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

//        outState.putDouble("Latitude_Location", lastKnownLocation.getLatitude());
//        outState.putDouble("Longitude_Location", lastKnownLocation.getLongitude());

        if (locationAPoint != null) {
            outState.putDouble("Latitude_A", locationAPoint.getLatitude());
            outState.putDouble("Longitude_A", locationAPoint.getLongitude());
        }

        if (locationAPoint != null) {
            outState.putDouble("Latitude_B", locationBPoint.getLatitude());
            outState.putDouble("Longitude_B", locationBPoint.getLongitude());
        }
    }

    private void loadPreferences() {
        SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE);

        if (prefs.contains("Latitude_A") && prefs.contains("Longitude_A")) {
            locationAPoint = new Location("");
            locationAPoint.setLatitude(Double.longBitsToDouble(prefs.getLong("Latitude_A", 0)));
            locationAPoint.setLongitude(Double.longBitsToDouble(prefs.getLong("Longitude_A", 0)));
        }

        if (prefs.contains("Latitude_B") && prefs.contains("Longitude_B")) {
            locationBPoint = new Location("");
            locationBPoint.setLatitude(Double.longBitsToDouble(prefs.getLong("Latitude_B", 0)));
            locationBPoint.setLongitude(Double.longBitsToDouble(prefs.getLong("Longitude_B", 0)));
        }
    }

    private void savePreferences() {
        SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        if (locationAPoint != null) {
            editor.putLong("Latitude_A", Double.doubleToRawLongBits(locationAPoint.getLatitude()));
            editor.putLong("Longitude_A", Double.doubleToRawLongBits(locationAPoint.getLongitude()));
        }

        if (locationBPoint != null) {
            editor.putLong("Latitude_B", Double.doubleToRawLongBits(locationBPoint.getLatitude()));
            editor.putLong("Longitude_B", Double.doubleToRawLongBits(locationBPoint.getLongitude()));
        }

        editor.commit();
    }
/*
    @Override
    protected void onStop() {
        super.onStop();
    }
    */
}