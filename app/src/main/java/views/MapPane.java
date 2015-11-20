package views;

import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.mapsdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iconflux-chuni on 8/21/2015.
 */
public class MapPane extends FragmentActivity implements OnMapReadyCallback {

    private SupportMapFragment mapFragment;
    private GoogleMap googleMap;

    private int currentPt = 0;

    // Keep track of our markers
    private List<Marker> markers = new ArrayList<Marker>();
    private final Handler mHandler = new Handler();
    private Marker selectedMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mymap);
        googleMap = mapFragment.getMap();
        googleMap.setMyLocationEnabled(true);
        mapFragment.getMapAsync(this);

        addMarkerToMap(new LatLng(23.0300, 72.5800));
        addMarkerToMap(new LatLng(22.5560, 72.9510));
        addMarkerToMap(new LatLng(22.3000, 73.2000));  // Sydney
        addMarkerToMap(new LatLng(21.7000, 72.9700));  // Fiji
        addMarkerToMap(new LatLng(21.1700, 72.8300));  // Hawaii
         // Mountain View
//        addMarkerToMap(new LatLng(45.4000, 75.6667));


//        addMarkerToMap(new LatLng(23.0271829,72.5084621));
//        addMarkerToMap(new LatLng(23.0271829,72.5084621));
//        addMarkerToMap(new LatLng(23.0919405,72.5839823));
//        addMarkerToMap(new LatLng(23.0395677,72.5660045));
//        addMarkerToMap(new LatLng(23.0452583,72.5170425));
//        addMarkerToMap(new LatLng(22.3000,73.2000));


//        addMarkerToMap(new LatLng(50.961813797827055,3.5168474167585373));
//        addMarkerToMap(new LatLng(50.96085423274633,3.517405651509762));
//        addMarkerToMap(new LatLng(50.96020550146382,3.5177918896079063));
//        addMarkerToMap(new LatLng(50.95936754348453,3.518972061574459));
//        addMarkerToMap(new LatLng(50.95877285446026,3.5199161991477013));
//        addMarkerToMap(new LatLng(50.958179213755905,3.520646095275879));
//        addMarkerToMap(new LatLng(50.95901719316589,3.5222768783569336));
//        addMarkerToMap(new LatLng(50.95954430150347,3.523542881011963));
//        addMarkerToMap(new LatLng(50.95873336312275,3.5244011878967285));
//        addMarkerToMap(new LatLng(50.95955781702322,3.525688648223877));
//        addMarkerToMap(new LatLng(50.958855004782116,3.5269761085510254));
    }

    /**
     * Adds a marker to the map.
     */
    public void addMarkerToMap(LatLng latLng) {
        Marker marker = googleMap.addMarker(new MarkerOptions().position(latLng)
                .title("title")
                .snippet("snippet"));
        markers.add(marker);
    }

//    @Override
//    public void onMapReady(GoogleMap map) {
//        LatLng sydney = new LatLng(-33.867, 151.206);
//
//        map.setMyLocationEnabled(true);
//        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));
//
//        map.addMarker(new MarkerOptions()
//                .title("Sydney")
//                .snippet("The most populous city in Australia.")
//                .position(sydney));
//    }


//    @Override
//    public void onMapReady(GoogleMap map) {
//        map.moveCamera(CameraUpdateFactory.newLatLngZoom(
//                new LatLng(-18.142, 178.431), 2));
//
//        // Other supported types include: MAP_TYPE_NORMAL,
//        // MAP_TYPE_TERRAIN, MAP_TYPE_HYBRID and MAP_TYPE_NONE
//        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
//    }

//    GoogleMap.CancelableCallback myCancelableCallback;

    @Override
    public void onMapReady(GoogleMap map) {

//        CameraPosition cameraPosition =
//                new CameraPosition.Builder()
//                        .target(new LatLng(23.0300, 72.5800))
//                        .bearing(45)
//                        .tilt(90)
//                        .zoom(googleMap.getCameraPosition().zoom)
//                        .build();
//
//        map.animateCamera(
//                CameraUpdateFactory.newLatLng(markers.get(0).getPosition()),
//                MyCancelableCallback);
        startAnimation(true);
    }

    private Animator animator = new Animator();

    GoogleMap.CancelableCallback MyCancelableCallback =
            new GoogleMap.CancelableCallback() {

                @Override
                public void onCancel() {
                    System.out.println("onCancelled called");
                }

                @Override
                public void onFinish() {
                    if (++currentPt < markers.size()) {

//						//Get the current location
//						Location startingLocation = new Location("starting point");
//						startingLocation.setLatitude(googleMap.getCameraPosition().target.latitude);
//						startingLocation.setLongitude(googleMap.getCameraPosition().target.longitude);
//
//						//Get the target location
//						Location endingLocation = new Location("ending point");
//						endingLocation.setLatitude(markers.get(currentPt).getPosition().latitude);
//						endingLocation.setLongitude(markers.get(currentPt).getPosition().longitude);
//
//						//Find the Bearing from current location to next location
//						float targetBearing = startingLocation.bearingTo(endingLocation);


                        float targetBearing = bearingBetweenLatLngs(googleMap.getCameraPosition().target, markers.get(currentPt).getPosition());

                        LatLng targetLatLng = markers.get(currentPt).getPosition();
                        //float targetZoom = zoomBar.getProgress();


                        System.out.println("currentPt  = " + currentPt);
                        System.out.println("size  = " + markers.size());
                        //Create a new CameraPosition
                        CameraPosition cameraPosition =
                                new CameraPosition.Builder()
                                        .target(targetLatLng)
                                        .tilt(currentPt < markers.size() - 1 ? 90 : 0)
                                        .bearing(targetBearing)
                                        .zoom(googleMap.getCameraPosition().zoom)
                                        .build();

                        googleMap.animateCamera(
                                CameraUpdateFactory.newCameraPosition(cameraPosition),
                                5000,
                                MyCancelableCallback);
                        System.out.println("Animate to: " + markers.get(currentPt).getPosition() + "\n" +
                                "Bearing: " + targetBearing);

                        markers.get(currentPt).showInfoWindow();

                    } else {
                        //info.setText("onFinish()");
                    }
                }
            };


    public void startAnimation(boolean showPolyLine) {
        if (markers.size()>2) {
            animator.initialize(showPolyLine);
        }
    }

    private Polyline polyLine;

    private void updatePolyLine(LatLng latLng) {
        List<LatLng> points = polyLine.getPoints();
        points.add(latLng);
        polyLine.setPoints(points);
    }

    private Location convertLatLngToLocation(LatLng latLng) {
        Location loc = new Location("someLoc");
        loc.setLatitude(latLng.latitude);
        loc.setLongitude(latLng.longitude);
        return loc;
    }

    private float bearingBetweenLatLngs(LatLng begin,LatLng end) {
        Location beginL= convertLatLngToLocation(begin);
        Location endL= convertLatLngToLocation(end);

        return beginL.bearingTo(endL);
    }

    public class Animator implements Runnable {

        private static final int ANIMATE_SPEEED = 5000;
        private static final int ANIMATE_SPEEED_TURN = 6000;
        private static final int BEARING_OFFSET = 20;

        private final Interpolator interpolator = new LinearInterpolator();

        int currentIndex = 0;

        float tilt = 90;
        float zoom = 26.5f;
        boolean upward=true;

        long start = SystemClock.uptimeMillis();

        LatLng endLatLng = null;
        LatLng beginLatLng = null;

        boolean showPolyline = false;

        private Marker trackingMarker;

        public void reset() {
//            resetMarkers();
            start = SystemClock.uptimeMillis();
            currentIndex = 0;
            endLatLng = getEndLatLng();
            beginLatLng = getBeginLatLng();
        }

        public void stop() {
            trackingMarker.remove();
            mHandler.removeCallbacks(animator);
        }

        public void initialize(boolean showPolyLine) {
            reset();
            this.showPolyline = showPolyLine;
            highLightMarker(0);

            if (showPolyLine) {
                polyLine = initializePolyLine();
            }

            // We first need to put the camera in the correct position for the first run (we need 2 markers for this).....
            LatLng markerPos = markers.get(0).getPosition();
            LatLng secondPos = markers.get(1).getPosition();

            setupCameraPositionForMovement(markerPos, secondPos);
        }

        private void setupCameraPositionForMovement(LatLng markerPos,
                                                    LatLng secondPos) {

            float bearing = bearingBetweenLatLngs(markerPos,secondPos);

            trackingMarker = googleMap.addMarker(new MarkerOptions().position(markerPos)
                    .title("title")
                    .snippet("snippet"));

            CameraPosition cameraPosition =
                    new CameraPosition.Builder()
                            .target(markerPos)
                            .bearing(bearing + BEARING_OFFSET)
                            .tilt(90)
                            .zoom(googleMap.getCameraPosition().zoom >=16 ? googleMap.getCameraPosition().zoom : 16)
                            .build();

            googleMap.animateCamera(
                    CameraUpdateFactory.newCameraPosition(cameraPosition),
                    ANIMATE_SPEEED_TURN,
                    new GoogleMap.CancelableCallback() {

                        @Override
                        public void onFinish() {
                            System.out.println("finished camera");
                            animator.reset();
                            Handler handler = new Handler();
                            handler.post(animator);
                        }

                        @Override
                        public void onCancel() {
                            System.out.println("cancelling camera");
                        }
                    }
            );
        }

        private Polyline polyLine;
        private PolylineOptions rectOptions = new PolylineOptions();


        private Polyline initializePolyLine() {
            //polyLinePoints = new ArrayList<LatLng>();
            rectOptions.add(markers.get(0).getPosition());
            return googleMap.addPolyline(rectOptions);
        }

        /**
         * Add the marker to the polyline.
         */
        private void updatePolyLine(LatLng latLng) {
            List<LatLng> points = polyLine.getPoints();
            points.add(latLng);
            polyLine.setPoints(points);
        }


        public void stopAnimation() {
            animator.stop();
        }

        public void startAnimation(boolean showPolyLine) {
            if (markers.size()>2) {
                animator.initialize(showPolyLine);
            }
        }


        @Override
        public void run() {

            long elapsed = SystemClock.uptimeMillis() - start;
            double t = interpolator.getInterpolation((float)elapsed/ANIMATE_SPEEED);

//			LatLng endLatLng = getEndLatLng();
//			LatLng beginLatLng = getBeginLatLng();

            double lat = t * endLatLng.latitude + (1-t) * beginLatLng.latitude;
            double lng = t * endLatLng.longitude + (1-t) * beginLatLng.longitude;
            LatLng newPosition = new LatLng(lat, lng);

            trackingMarker.setPosition(newPosition);

            if (showPolyline) {
                updatePolyLine(newPosition);
            }

            // It's not possible to move the marker + center it through a cameraposition update while another camerapostioning was already happening.
            //navigateToPoint(newPosition,tilt,bearing,currentZoom,false);
            //navigateToPoint(newPosition,false);

            if (t< 1) {
                mHandler.postDelayed(this, 16);
            } else {

                System.out.println("Move to next marker.... current = " + currentIndex + " and size = " + markers.size());
                // imagine 5 elements -  0|1|2|3|4 currentindex must be smaller than 4
                if (currentIndex<markers.size()-2) {

                    currentIndex++;

                    endLatLng = getEndLatLng();
                    beginLatLng = getBeginLatLng();

                    start = SystemClock.uptimeMillis();

                    LatLng begin = getBeginLatLng();
                    LatLng end = getEndLatLng();

                    float bearingL = bearingBetweenLatLngs(begin, end);

                    highLightMarker(currentIndex);

                    CameraPosition cameraPosition =
                            new CameraPosition.Builder()
                                    .target(end) // changed this...
                                    .bearing(bearingL  + BEARING_OFFSET)
                                    .tilt(tilt)
                                    .zoom(googleMap.getCameraPosition().zoom)
                                    .build();

                    googleMap.animateCamera(
                            CameraUpdateFactory.newCameraPosition(cameraPosition),
                            ANIMATE_SPEEED_TURN,
                            null
                    );

                    start = SystemClock.uptimeMillis();
                    mHandler.postDelayed(animator, 16);

                } else {
                    currentIndex++;
                    highLightMarker(currentIndex);
                    stopAnimation();
                }
            }
        }

//        private void resetMarkers() {
//            for (Marker marker : this.markers) {
//                marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
//            }
//        }


        private LatLng getEndLatLng() {
            return markers.get(currentIndex+1).getPosition();
        }

        private LatLng getBeginLatLng() {
            return markers.get(currentIndex).getPosition();
        }

        private void adjustCameraPosition() {
            //System.out.println("tilt = " + tilt);
            //System.out.println("upward = " + upward);
            //System.out.println("zoom = " + zoom);
            if (upward) {

                if (tilt<90) {
                    tilt ++;
                    zoom-=0.01f;
                } else {
                    upward=false;
                }

            } else {
                if (tilt>0) {
                    tilt --;
                    zoom+=0.01f;
                } else {
                    upward=true;
                }
            }
        }
    };


    /**
     * Highlight the marker by index.
     */
    private void highLightMarker(int index) {
        highLightMarker(markers.get(index));
    }

    /**
     * Highlight the marker by marker.
     */
    private void highLightMarker(Marker marker) {

		/*
		for (Marker foundMarker : this.markers) {
			if (!foundMarker.equals(marker)) {
				foundMarker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
			} else {
				foundMarker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
				foundMarker.showInfoWindow();
			}
		}
		*/
        marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        marker.showInfoWindow();

        //Utils.bounceMarker(googleMap, marker);

        this.selectedMarker=marker;
    }

//    @Override
//    public void onMapReady(GoogleMap map) {
//        // Some buildings have indoor maps. Center the camera over
//        // the building, and a floor picker will automatically appear.
//        CameraPosition cameraPosition =
//                new CameraPosition.Builder()
//                        .target(new LatLng(-33.866, 151.195))
//                        .bearing(90)
//                        .tilt(90)
//                        .zoom(googleMap.getCameraPosition().zoom)
//                        .build();
//
//        map.animateCamera(
//                CameraUpdateFactory.newCameraPosition(cameraPosition),
//                5000,
//                new GoogleMap.CancelableCallback() {
//
//                    @Override
//                    public void onFinish() {
//                    }
//
//                    @Override
//                    public void onCancel() {
//                    }
//                }
//        );
//
//    }


//    @Override
//    public void onMapReady(GoogleMap map) {
//        map.moveCamera(CameraUpdateFactory.newLatLngZoom(
//                new LatLng(41.889, -87.622), 16));
//
//        // You can customize the marker image using images bundled with
//        // your app, or dynamically generated bitmaps.
//        map.addMarker(new MarkerOptions()
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_truck))
//                .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
//                .position(new LatLng(41.889, -87.622)));
//    }


//    @Override
//    public void onMapReady(GoogleMap map) {
//        LatLng mapCenter = new LatLng(41.889, -87.622);
//
//        map.moveCamera(CameraUpdateFactory.newLatLngZoom(mapCenter, 13));
//
//        // Flat markers will rotate when the map is rotated,
//        // and change perspective when the map is tilted.
//        map.addMarker(new MarkerOptions()
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_truck))
//                .position(mapCenter)
//                .flat(true)
//                .rotation(245));
//
//        CameraPosition cameraPosition = CameraPosition.builder()
//                .target(mapCenter)
//                .zoom(13)
//                .bearing(90)
//                .build();
//
//        // Animate the change in camera view over 2 seconds
//        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition),
//                2000, null);
//    }


//    @Override
//    public void onMapReady(GoogleMap map) {
//        map.moveCamera(CameraUpdateFactory.newLatLngZoom(
//                new LatLng(-18.142, 178.431), 2));
//
//        // Polylines are useful for marking paths and routes on the map.
//        map.addPolyline(new PolylineOptions().geodesic(true)
//                .add(new LatLng(-33.866, 151.195))  // Sydney
//                .add(new LatLng(-18.142, 178.431))  // Fiji
//                .add(new LatLng(21.291, -157.821))  // Hawaii
//                .add(new LatLng(37.423, -122.091))  // Mountain View
//        );
//    }


}
