/**
 * Sep 6, 2011
 */
package pl.nitroit.wheeel.location;

import pl.nitroit.wheeel.db.WheeelDbHelper;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


/**
 * @author kbl
 *
 */
public class DockingStationDao {

	private static final String TABLE = "docking_station";

	public static interface DockingStationColumns {
		int ID_INDEX = 0;
		int LAT_INDEX = 1;
		int LON_INDEX = 2;
	}

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
		return db.query(TABLE, null, null, null, null, null, null);
	}

}
