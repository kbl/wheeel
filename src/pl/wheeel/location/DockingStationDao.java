/**
 * Sep 6, 2011
 */
package pl.wheeel.location;

import pl.wheeel.db.WheeelDbHelper;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


/**
 * @author kbl
 *
 */
public class DockingStationDao {

	private static final String TABLE = "docking_station";

	private static final String LAT_COLUMN = "lat";
	public static final int LAT_INDEX = 1;

	private static final String LON_COLUMN = "lon";
	public static final int LON_INDEX = 2;

	private static final String RESOURCES_KEY_COLUMN = "res_key";
	public static final int RESOURCES_KEY_INDEX = 3;

	private static final String[] COLUMNS = {LAT_COLUMN, LON_COLUMN, RESOURCES_KEY_COLUMN};

	private WheeelDbHelper dbHelper;
	private SQLiteDatabase db;

	public DockingStationDao(Context context) {
		dbHelper = new WheeelDbHelper(context);
	}

	public DockingStationDao open() {
		if(db == null) {
			db = dbHelper.getReadableDatabase();
		}
		return this;
	}

	public void close() {
		dbHelper.close();
	}

	public Cursor getAllDockingStations() {
		return db.query(TABLE, COLUMNS, null, null, null, null, null);
	}

}
