package pl.wheeel;

import android.os.Bundle;
import android.view.KeyEvent;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

public class WheeelActivity extends MapActivity {

    private MapView mapView;
    private MyLocationOverlay locationOverlay;

    @Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);

        initMap();
        initLocationOverlay();
    }

	private void initMap() {
		mapView = (MapView) findViewById(R.id.mapView);
        mapView.displayZoomControls(true);
	}

	private void initLocationOverlay() {
		locationOverlay = new MyLocationOverlay(this, mapView);
		locationOverlay.enableMyLocation();
		mapView.getOverlays().add(locationOverlay);
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