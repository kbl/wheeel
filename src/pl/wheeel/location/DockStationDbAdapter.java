/**
 * Sep 6, 2011
 */
package pl.wheeel.location;

import pl.wheeel.db.WheeelDbHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


/**
 * @author kbl
 *
 */
public class DockStationDbAdapter {

	private WheeelDbHelper dbHelper;
	private SQLiteDatabase db;

	public DockStationDbAdapter(Context context) {
		dbHelper = new WheeelDbHelper(context);
	}

	public DockStationDbAdapter open() {
		if(db == null) {
			db = dbHelper.getWritableDatabase();
		}
		return this;
	}

	public void close() {
		dbHelper.close();
	}

}
