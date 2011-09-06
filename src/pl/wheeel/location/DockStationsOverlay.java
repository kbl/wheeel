/**
 * Sep 6, 2011
 */
package pl.wheeel.location;

import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;

/**
 * @author kbl
 *
 */
public class DockStationsOverlay extends ItemizedOverlay<DockStation> {

	/**
	 * @param defaultMarker
	 */
	public DockStationsOverlay(Drawable defaultMarker) {
		super(defaultMarker);
	}

	@Override
	protected DockStation createItem(int i) {
		return null;
	}

	@Override
	public int size() {
		return 0;
	}

}
