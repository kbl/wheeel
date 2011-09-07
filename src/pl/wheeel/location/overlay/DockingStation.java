/**
 * Sep 6, 2011
 */
package pl.wheeel.location.overlay;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

/**
 * @author kbl
 *
 */
public class DockingStation extends OverlayItem {

	public DockingStation(GeoPoint point, String title, String snippet) {
		super(point, title, snippet);
	}

}
