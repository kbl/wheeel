/**
 * Sep 7, 2011
 */
package pl.wheeel.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author kbl
 *
 */
public class WheeelDbHelper extends SQLiteOpenHelper {

	private static final int VERSION = 1;
	private static final String DB_NAME = "wheeel.db";
	private static final String CREATE_STMT = "CREATE TABLE docking_point (" +
			"_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE," +
			"lat INTEGER NOT NULL," +
			"lon INTEGER NOT NULL);";

	public WheeelDbHelper(Context context) {
		super(context, DB_NAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_STMT);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }

}
