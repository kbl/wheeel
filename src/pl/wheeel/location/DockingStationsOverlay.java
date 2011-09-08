/**
 * Sep 6, 2011
 */
package pl.wheeel.location;

import pl.wheeel.R;
import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

/**
 * @author kbl
 *
 */
public class DockingStationsOverlay extends ItemizedOverlay<OverlayItem> {

	private static final int ZERO_BASED = 1;

	private OverlayItem[] overlayItems;
	//private Drawable nearestMarker;

	public DockingStationsOverlay(Context context, Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
		//this.nearestMarker = nearestMarker;
		populateOverlayItems(context);
	}

	private void populateOverlayItems(Context context) {
		DockingStationDao dao = new DockingStationDao(context);
		Cursor dockingStations = dao.open().getAllDockingStations();
		String[] dockingStationTitles = context.getResources().
				getStringArray(R.array.dockingStationTitle);

		try {
			retriveDataFromCursor(dockingStations, dockingStationTitles);
		}
		finally {
			dao.close();
			dockingStations.close();
		}
	}

	private void retriveDataFromCursor(Cursor dockingStations,
			String[] dockingStationTitles) {
		overlayItems = new OverlayItem[dockingStations.getCount()];
		int tableIndex = 0;
		dockingStations.moveToFirst();
		do {
			int lat = dockingStations.getInt(DockingStationDao.LAT_INDEX);
			int lon = dockingStations.getInt(DockingStationDao.LON_INDEX);
			int id = dockingStations.getInt(DockingStationDao.ID_INDEX);

			String title = dockingStationTitles[id - ZERO_BASED];
			String snippet = title;

			overlayItems[tableIndex++] = new OverlayItem(new GeoPoint(lat, lon), title, snippet);
			populate();
		} while(dockingStations.moveToNext());
	}

	@Override
	protected OverlayItem createItem(int i) {
		OverlayItem item = overlayItems[i];
		return item;
	}

	@Override
	public int size() {
		return overlayItems.length;
	}

}
