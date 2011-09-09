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
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

/**
 * @author kbl
 *
 */
public class DockingStationsOverlay extends ItemizedOverlay<OverlayItem>
		implements LocationListener {

	private static final double GEO_POINT_1E6 = 1.0e6;
	private static final int ZERO_BASED = 1;

	private List<OverlayItem> overlayItems = new ArrayList<OverlayItem>();
	private Drawable defaultMarker;
	private Drawable nearestMarker;

	public DockingStationsOverlay(Context context, Drawable defaultMarker, Drawable nearestMarker) {
		super(boundCenterBottom(defaultMarker));
		prepareMarkers(defaultMarker, nearestMarker);
		populateOverlayItems(context);
	}

	private void prepareMarkers(Drawable defaultM, Drawable nearestM) {
		nearestMarker = nearestM;
		defaultMarker = defaultM;

		nearestMarker.setBounds(0, 0,
				nearestMarker.getIntrinsicWidth(),
				nearestMarker.getIntrinsicHeight());
		defaultMarker.setBounds(0, 0,
				defaultMarker.getIntrinsicWidth(),
				defaultMarker.getIntrinsicHeight());
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
			createOverlayItem(dockingStations, dockingStationTitles);
			populate();
		} while(dockingStations.moveToNext());
	}

	private void createOverlayItem(Cursor dockingStations,
			String[] dockingStationTitles) {
		int lat = dockingStations.getInt(DockingStationDao.DockingStationColumns.LAT_INDEX);
		int lon = dockingStations.getInt(DockingStationDao.DockingStationColumns.LON_INDEX);
		int id = dockingStations.getInt(DockingStationDao.DockingStationColumns.ID_INDEX);

		String title = dockingStationTitles[id - ZERO_BASED];
		String snippet = title;

		overlayItems.add(new OverlayItem(new GeoPoint(lat, lon), title, snippet));
	}

	@Override
	protected OverlayItem createItem(int i) {
		return overlayItems.get(i);
	}

	@Override
	public int size() {
		return overlayItems.size();
	}

	@Override
	public void onLocationChanged(Location l) {
		float minDistance = Float.MAX_VALUE;
		int minItemIndex = 0;
		float[] results = new float[1];
		for(int i = 0, size = overlayItems.size(); i < size; i++) {
			OverlayItem item = overlayItems.get(i);
			GeoPoint p = item.getPoint();
			Location.distanceBetween(
					l.getLatitude(), l.getLongitude(),
					p.getLatitudeE6() / GEO_POINT_1E6, p.getLongitudeE6() / GEO_POINT_1E6,
					results);
			item.setMarker(defaultMarker);
			if(minDistance > results[0]) {
				minDistance = results[0];
				minItemIndex = i;
			}
		}
		overlayItems.get(minItemIndex).setMarker(nearestMarker);
		populate();
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) { }

	@Override
	public void onProviderEnabled(String provider) { }

	@Override
	public void onProviderDisabled(String provider) { }

}
