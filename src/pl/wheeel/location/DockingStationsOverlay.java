/**
 * Sep 6, 2011
 */
package pl.wheeel.location;

import pl.wheeel.location.model.DockingStation;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;

/**
 * @author kbl
 *
 */
public class DockingStationsOverlay extends ItemizedOverlay<DockingStation> {

	/**
	 * @param defaultMarker
	 */
	public DockingStationsOverlay(Drawable defaultMarker) {
		super(defaultMarker);
	}

	@Override
	protected DockingStation createItem(int i) {
		return null;
	}

	@Override
	public int size() {
		return 0;
	}

}
