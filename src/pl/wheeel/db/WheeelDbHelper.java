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
	private static final String CREATE_STMT = "CREATE TABLE docking_station (" +
			"_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE," +
			"lat INTEGER NOT NULL," +
			"lon INTEGER NOT NULL);";

	private static final String SEED_DATA =
			"INSERT INTO docking_station VALUES(1,51110554,17033958);" +
			"INSERT INTO docking_station VALUES(2,51107604,17032670);" +
			"INSERT INTO docking_station VALUES(3,51108129,17039816);" +
			"INSERT INTO docking_station VALUES(4,51111039,17037949);" +
			"INSERT INTO docking_station VALUES(5,51113693,17034730);" +
			"INSERT INTO docking_station VALUES(6,51110366,17054965);" +
			"INSERT INTO docking_station VALUES(7,51114030,17067604);" +
			"INSERT INTO docking_station VALUES(8,51112710,17040009);" +
			"INSERT INTO docking_station VALUES(9,51108190,17021491);" +
			"INSERT INTO docking_station VALUES(10,51111571,17021062);" +
			"INSERT INTO docking_station VALUES(11,51109773,17030450);" +
			"INSERT INTO docking_station VALUES(12,51101244,17028733);" +
			"INSERT INTO docking_station VALUES(13,51099796,17035578);" +
			"INSERT INTO docking_station VALUES(14,51107334,17061992);" +
			"INSERT INTO docking_station VALUES(15,51108136,17064986);" +
			"INSERT INTO docking_station VALUES(16,51112081,17061515);" +
			"INSERT INTO docking_station VALUES(17,51104128,17084834);";

	public WheeelDbHelper(Context context) {
		super(context, DB_NAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_STMT);
		db.execSQL(SEED_DATA);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }

}
