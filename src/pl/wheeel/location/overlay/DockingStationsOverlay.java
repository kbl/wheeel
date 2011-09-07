/**
 * Sep 6, 2011
 */
package pl.wheeel.location.overlay;

import pl.wheeel.location.DockingStationDao;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;

/**
 * @author kbl
 *
 */
public class DockingStationsOverlay extends ItemizedOverlay<DockingStation> {

	private DockingStationDao dao;
	private Cursor allDockingStations;
	private Context context;
	private Resources resources;

	public DockingStationsOverlay(Context context, Drawable defaultMarker) {
		super(defaultMarker);
		DockingStationDao dao = new DockingStationDao(context);
		allDockingStations = dao.open().getAllDockingStations();
		dao.close();
		resources = context.getResources();
		this.context = context;
	}

	@Override
	protected DockingStation createItem(int i) {
		allDockingStations.moveToPosition(i);
		int lat = allDockingStations.getInt(DockingStationDao.LAT_INDEX);
		int lon = allDockingStations.getInt(DockingStationDao.LON_INDEX);

		String title = null;
		String snippet = null;

		return new DockingStation(new GeoPoint(lat, lon), title, snippet);
	}

	@Override
	public int size() {
		return allDockingStations.getCount();
	}

}
