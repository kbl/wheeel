/**
 * Sep 6, 2011
 */
package pl.nitroit.wheeel.location;

import java.util.List;

import pl.nitroit.wheeel.R;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.KeyEvent;

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

	private static final int INITIAL_ZOOM_LEVEL = 15;
	private static final GeoPoint MARKET_SQUARE_WROCLAW = new GeoPoint(51110096, 17032413);

	private MapView mapView;
	private MyLocationOverlay locationOverlay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map);

		initMap();
		initMyLocation();
	}

	private void initMap() {
		mapView = (MapView) findViewById(R.id.mapView);
		mapView.displayZoomControls(true);
		MapController controller = mapView.getController();
		controller.setZoom(INITIAL_ZOOM_LEVEL);
		controller.animateTo(MARKET_SQUARE_WROCLAW);
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

	private void initMyLocation() {
		locationOverlay = new MyLocationOverlay(this, mapView);
		locationOverlay.enableMyLocation();
		DockingStationsOverlay dockingStationsOverlay =
				new DockingStationsOverlay(
						this,
						getResources().getDrawable(R.drawable.flag_blue),
						getResources().getDrawable(R.drawable.flag_green));
		registerAsLocationListener(dockingStationsOverlay);

		List<Overlay> overlays = mapView.getOverlays();
		overlays.add(locationOverlay);
		overlays.add(dockingStationsOverlay);
	}

	private void registerAsLocationListener(LocationListener listener) {
		LocationManager locationManager =
				(LocationManager) getSystemService(LOCATION_SERVICE);
		locationManager.requestLocationUpdates(
				LocationManager.GPS_PROVIDER,
				0,
				0,
				listener);
	}

	@Override
	protected boolean isRouteDisplayed() { return false; }

}
