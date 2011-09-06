/**
 * Sep 6, 2011
 */
package pl.wheeel.location;

import pl.wheeel.R;
import android.os.Bundle;

import com.google.android.maps.MapActivity;

/**
 * @author kbl
 *
 */
public class WheeelMapActivity extends MapActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map);
	}

	@Override
	protected boolean isRouteDisplayed() { return false; }

}
