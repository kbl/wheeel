/**
 * Sep 6, 2011
 */
package pl.wheeel.location.model;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

/**
 * @author kbl
 *
 */
public class DockStation extends OverlayItem {

	public DockStation(GeoPoint point, String title, String snippet) {
		super(point, title, snippet);
	}

}
