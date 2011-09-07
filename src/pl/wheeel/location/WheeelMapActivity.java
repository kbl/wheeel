/**
 * Sep 6, 2011
 */
package pl.wheeel.location;

import java.util.List;

import pl.wheeel.R;
import pl.wheeel.location.overlay.DockingStationsOverlay;
import android.os.Bundle;
import android.view.KeyEvent;

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
				new DockingStationsOverlay(this, getResources().getDrawable(R.drawable.icon));

		List<Overlay> overlays = mapView.getOverlays();
		overlays.add(locationOverlay);
		overlays.add(dockingStationsOverlay);
	}

	@Override
	protected boolean isRouteDisplayed() { return false; }

}
