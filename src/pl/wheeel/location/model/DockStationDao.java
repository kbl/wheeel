/**
 * Sep 6, 2011
 */
package pl.wheeel.location.model;

import pl.wheeel.db.WheeelDbHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


/**
 * @author kbl
 *
 */
public class DockStationDao {

	private WheeelDbHelper dbHelper;
	private SQLiteDatabase db;

	public DockStationDao(Context context) {
		dbHelper = new WheeelDbHelper(context);
	}

	public DockStationDao open() {
		if(db == null) {
			db = dbHelper.getWritableDatabase();
		}
		return this;
	}

	public void close() {
		dbHelper.close();
	}

}
