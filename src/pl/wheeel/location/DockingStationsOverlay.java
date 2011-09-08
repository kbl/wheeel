/**
 * Sep 6, 2011
 */
package pl.wheeel.location;

import java.util.ArrayList;
import java.util.List;

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

	private List<OverlayItem> overlayItems = new ArrayList<OverlayItem>();

	public DockingStationsOverlay(Context context, Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
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
		dockingStations.moveToFirst();
		do {
			int lat = dockingStations.getInt(DockingStationDao.LAT_INDEX);
			int lon = dockingStations.getInt(DockingStationDao.LON_INDEX);
			int id = dockingStations.getInt(DockingStationDao.ID_INDEX);

			String title = dockingStationTitles[id - ZERO_BASED];
			String snippet = title;

			overlayItems.add(new OverlayItem(new GeoPoint(lat, lon), title, snippet));
			populate();
		} while(dockingStations.moveToNext());
	}

	@Override
	protected OverlayItem createItem(int i) {
		return overlayItems.get(i);
	}

	@Override
	public int size() {
		return overlayItems.size();
	}

}
