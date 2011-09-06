/**
 * Sep 6, 2011
 */
package pl.wheeel.location;

import pl.wheeel.R;
import android.os.Bundle;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

/**
 * @author kbl
 *
 */
public class WheeelMapActivity extends MapActivity {

	private MapView mMapView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map);

		mMapView = (MapView) findViewById(R.id.mapView);
		mMapView.displayZoomControls(true);
	}

	@Override
	protected boolean isRouteDisplayed() { return false; }

}
