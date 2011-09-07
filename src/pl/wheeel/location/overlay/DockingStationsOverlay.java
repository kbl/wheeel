/**
 * Sep 6, 2011
 */
package pl.wheeel.location.overlay;

import pl.wheeel.R;
import pl.wheeel.location.DockingStationDao;
import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;

/**
 * @author kbl
 *
 */
public class DockingStationsOverlay extends ItemizedOverlay<DockingStation> {

	private static final int ZERO_BASED = 1;

	private Cursor allDockingStations;
	private String[] dockingStationTitles;

	public DockingStationsOverlay(Context context, Drawable defaultMarker) {
		super(defaultMarker);
		DockingStationDao dao = new DockingStationDao(context);
		allDockingStations = dao.open().getAllDockingStations();
		dao.close();
		dockingStationTitles =
				context.getResources().getStringArray(R.array.dockingStationTitle);
	}

	@Override
	protected DockingStation createItem(int i) {
		allDockingStations.moveToPosition(i);
		int lat = allDockingStations.getInt(DockingStationDao.LAT_INDEX);
		int lon = allDockingStations.getInt(DockingStationDao.LON_INDEX);
		int id = allDockingStations.getInt(DockingStationDao.ID_INDEX);

		String title = dockingStationTitles[id - ZERO_BASED];
		String snippet = title;

		return new DockingStation(new GeoPoint(lat, lon), title, snippet);
	}

	@Override
	public int size() {
		return allDockingStations.getCount();
	}

}
