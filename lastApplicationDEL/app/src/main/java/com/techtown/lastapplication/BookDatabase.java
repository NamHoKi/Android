package com.techtown.lastapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BookDatabase {

	public static final String TAG = "BookDatabase";/*** TAG for debugging */
	private static BookDatabase database;/*** Singleton instance */
	public static String DATABASE_NAME = "book.db";/*** database name*/
	public static String TABLE_BOOK_INFO = "BOOK_INFO";/*** table name for BOOK_INFO*/
	public static int DATABASE_VERSION = 1;/*** version*/
	private DatabaseHelper dbHelper;/*** Helper class defined*/
	private SQLiteDatabase db; /*** Database object*/
	private Context context;

	public static String NAME = "NAME", AUTHOR= "AUTHOR", CONTENTS = "CONTENTS", TIME = "TIME",
			PHOTOIMAGE = "PHOTOIMAGE", NUMBER = "NUMBER", LAT = "LAT", LON = "LON";
    /* Constructor*/
	private BookDatabase(Context context) {
		this.context = context;
	}

	public static BookDatabase getInstance(Context context) {
		if (database == null) {
			database = new BookDatabase(context);
		}
		return database;
	}

	/** open database* @return */
    public boolean open() {
    	println("opening database [" + DATABASE_NAME + "].");

    	dbHelper = new DatabaseHelper(context);
    	db = dbHelper.getWritableDatabase();

    	return true;
    }

    /*** close database   */
    public void close() {
    	println("closing database [" + DATABASE_NAME + "].");
    	db.close();
    	database = null;
    }

    /**
     * execute raw query using the input SQL
     * close the cursor after fetching any result
     *
     * @param SQL
     * @return
     */
    public Cursor rawQuery(String SQL) {
		println("\nexecuteQuery called.\n");

		Cursor c1 = null;
		try {
			c1 = db.rawQuery(SQL, null);
			println("cursor count : " + c1.getCount());
		} catch(Exception ex) {
    		Log.e(TAG, "Exception in executeQuery", ex);
    	}

		return c1;
	}

    public boolean execSQL(String SQL) {
		println("\nexecute called.\n");

		try {
			Log.d(TAG, "SQL : " + SQL);
			db.execSQL(SQL);
	    } catch(Exception ex) {
			Log.e(TAG, "Exception in executeQuery", ex);
			return false;
		}

		return true;
	}

    private class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context) { super(context, DATABASE_NAME, null, DATABASE_VERSION); }

        public void onCreate(SQLiteDatabase _db) {
        	// TABLE_BOOK_INFO
        	println("creating table [" + TABLE_BOOK_INFO + "].");

        	// drop existing table
        	String DROP_SQL = "drop table if exists " + TABLE_BOOK_INFO;
        	try {
        		_db.execSQL(DROP_SQL);
        	} catch(Exception ex) {
        		Log.e(TAG, "Exception in DROP_SQL", ex);
        	}

        	// create table
        	String CREATE_SQL = "create table " + TABLE_BOOK_INFO + "("
		        			+ "  _id INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT, "
							+ "  NUMBER INTEGER,"
		        			+ "  NAME TEXT, "
		        			+ "  AUTHOR TEXT, "
		        			+ "  CONTENTS TEXT, "
							+ "  TIME TEXT, "
							+ "  LAT DOUBLE,"
							+ "  LON DOUBLE,"
							+ "  PHOTOIMAGE BLOB, "
		        			+ "  CREATE_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP "
		        			+ ")";
            try {
            	_db.execSQL(CREATE_SQL);
            } catch(Exception ex) {
        		Log.e(TAG, "Exception in CREATE_SQL", ex);
        	}

//			// insert 5 book records
//			insertRecord(_db,1, "지갑", "남호기", "과사무실에 맡겼습니다.", getTime(),122,-33);
//			insertRecord(_db,2, "휴대폰", "강유정", "A13-2331", getTime(),23,31);
//			insertRecord(_db,3, "라이언", "김민성", "카카오 프렌즈 샵", getTime(),11,10);
//			insertRecord(_db, 4,"모자", "오동의", "메트로시티", getTime(),-100,50);
//			insertRecord(_db, 5,"틴트", "이소연", "A12-1305", getTime(),13,5);

		}
		long mNow;
		Date mDate;
		SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		private String getTime(){
			mNow = System.currentTimeMillis();
			mDate = new Date(mNow);
			return mFormat.format(mDate);
		}

        public void onOpen(SQLiteDatabase db) {
        	println("opened database [" + DATABASE_NAME + "].");

        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        	println("Upgrading database from version " + oldVersion + " to " + newVersion + ".");
        	if (oldVersion < 2) {   // version 1
        	}
        }

//		private void insertRecord(SQLiteDatabase _db,int number, String name, String author, String contents, String time, double lat, double lon) {
//			try {
//				_db.execSQL( "insert into " + TABLE_BOOK_INFO + "(NUMBER, NAME, AUTHOR, CONTENTS, TIME, LAT, LON) values ('" + number + "', '" + name + "', '" + author + "', '" + contents +  "', '" + time +"', '" + lat +  "', '"+ lon +"');" );
//			} catch(Exception ex) {
//				Log.e(TAG, "Exception in executing insert SQL.", ex);
//			}
//		}
//		public void deleteRecord(int position) {
//			try {
//
//				db.execSQL("delete from BOOK_INFO where _id='" + position + "';");
//			} catch(Exception ex) {
//				Log.e(TAG, "Exception in executing insert SQL.", ex);
//			}
//		}
//		private void deleteRecord(SQLiteDatabase _db, int position)
//        {
//
//            db.delete(TABLE_BOOK_INFO, "NAME" + "=" + position,null );
//
//        }

    }
	public void insertRecord(int number, String name, String author, String contents, String time, double lat, double lon, byte[] photo) {
		db = dbHelper.getWritableDatabase();
		ContentValues cv = new  ContentValues();
		cv.put(NUMBER,    number);
		cv.put(NAME,    name);
		cv.put(AUTHOR,    author);
		cv.put(CONTENTS,    contents);
		cv.put(TIME, time);
		cv.put(LAT,    lat);
		cv.put(LON,    lon);
		cv.put(PHOTOIMAGE,   photo);
		db.insert( "BOOK_INFO", null, cv );

	}
	public void deleteRecord(String title) {

		// 입력한 항목과 일치하는 행 삭제
		try {
			db.execSQL("delete from  BOOK_INFO  where name='" + title + "';");
//			db.execSQL("select * from BOOK_INFO");
//            db.execSQL("update BOOK_INFO set ");
		} catch(Exception ex) {
			Log.e(TAG, "Exception in executing insert SQL.", ex);
		}
	}

//	public void update(){
////    	try{
////    		db.execSQL("update BOOK_INFO set _id = '" + i );
////		}
//	}

	public ArrayList<BookInfo> selectAll() {
		ArrayList<BookInfo> result = new ArrayList<BookInfo>();
		db = dbHelper.getWritableDatabase();
		try {
			Cursor cursor = db.rawQuery("select _id NUMBER, NAME, AUTHOR, CONTENTS, TIME, LAT, LON, PHOTOIMAGE from BOOK_INFO", null);
			for (int i = 0; i < cursor.getCount(); i++) {
				cursor.moveToNext();
				int number = cursor.getInt(0);
				String name = cursor.getString(1);
				String author = cursor.getString(2);
				String contents = cursor.getString(3);
				String time = cursor.getString(4);
				double lat = cursor.getDouble(5);
				double lon = cursor.getDouble(6);
				byte[] photoimage = cursor.getBlob(7);

				BookInfo info = new BookInfo(number, name, author, contents, time, lat, lon, photoimage);
				result.add(info);
			}

		} catch(Exception ex) {
			Log.e(TAG, "Exception in executing insert SQL.", ex);
		}

		return result;
	}

    private void println(String msg) {
    	Log.d(TAG, msg);
    }


}
