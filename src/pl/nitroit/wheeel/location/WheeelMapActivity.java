/**
 * Sep 6, 2011
 */
package pl.nitroit.wheeel.location;

import java.util.List;

import pl.nitroit.wheeel.R;
import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;

/**
 * @author kbl
 *
 */
public class WheeelMapActivity extends MapActivity {

	private static final int MIN_DISTANCE_NOTIFICATION = 5;
	private static final int MIN_TIME_NOTIFICATION = 3000;

	private static final int INITIAL_ZOOM_LEVEL = 15;
	private static final GeoPoint MARKET_SQUARE_WROCLAW = new GeoPoint(51110096, 17032413);

	private MapView mapView;
	private MyLocationOverlay locationOverlay;
	private LocationManager locationManager;
	private DockingStationsOverlay dockingStationsOverlay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map);

		initMap();
		initMyLocation();
	}

	@Override
	protected void onStart() {
		super.onStart();
		locationManager.requestLocationUpdates(
				LocationManager.GPS_PROVIDER,
				MIN_TIME_NOTIFICATION,
				MIN_DISTANCE_NOTIFICATION,
				dockingStationsOverlay);
	}

	@Override
	protected void onStop() {
		super.onStop();
		locationManager.removeUpdates(dockingStationsOverlay);
	}

	private void initMap() {
		mapView = (MapView) findViewById(R.id.mapView);
		mapView.displayZoomControls(true);
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		MapController controller = mapView.getController();
		controller.setZoom(INITIAL_ZOOM_LEVEL);
		controller.animateTo(MARKET_SQUARE_WROCLAW);
	}

	private void initMyLocation() {
		displayToastIfGpsIsDisabled();
		locationOverlay = new MyLocationOverlay(this, mapView);
		locationOverlay.enableMyLocation();
		dockingStationsOverlay =
				new DockingStationsOverlay(
						this,
						getResources().getDrawable(R.drawable.flag_blue),
						getResources().getDrawable(R.drawable.flag_green));
		List<Overlay> overlays = mapView.getOverlays();
		overlays.add(locationOverlay);
		overlays.add(dockingStationsOverlay);
	}

	private void displayToastIfGpsIsDisabled() {
		if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			Toast.makeText(this, R.string.gpsDisabledMsg, Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		MapController mapController = mapView.getController();
		switch(keyCode) {
			case KeyEvent.KEYCODE_3:
				mapController.zoomIn();
				break;
			case KeyEvent.KEYCODE_1:
				mapController.zoomOut();
				break;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected boolean isRouteDisplayed() { return false; }

}
