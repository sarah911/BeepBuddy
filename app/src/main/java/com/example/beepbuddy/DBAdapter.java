package com.example.beepbuddy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * BeepBuddy created by singla.pallavi12
 * student ID : 991524414
 * on 2019-11-28
 */
public class DBAdapter {

    public static final String KEY_ROWID = "_id";

    public static final String KEY_BUILDING_CODE = "buildingCode";
    public static final String KEY_CAR_PLATE = "carPlate";
    public static final String KEY_HOST_SUITE = "hostSuite";
    public static final String KEY_PARKING_DURATION = "parkingDuration";
    public static final String KEY_DATE = "strDate";
    public static final String KEY_TIME = "strTime";
    public static final String KEY_PARKING_AMOUNT = "parkingAmount";

    private static final String TAG = "DBAdapter";

    private static final String DATABASE_NAME = "MyDB";
    private static final String DATABASE_TABLE = "users";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE =
            "create table users (_id integer primary key autoincrement, "
                    + "buildingCode text not null, carPlate text not null, hostSuite text not null, " +
                    "parkingDuration text not null, strDate text not null, strTime text not null, parkingAmount text not null);";

    private final Context context;

    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBAdapter(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            try
            {
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS users");
            onCreate(db);
        }
    }

    public DBAdapter open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    public long insertUser(String buildingCode, String carPlate, String hostSuite,
                           String parkingDuration, String strDate, String strTime, String parkingAmount)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_BUILDING_CODE, buildingCode);
        initialValues.put(KEY_CAR_PLATE, carPlate);
        initialValues.put(KEY_HOST_SUITE, hostSuite);
        initialValues.put(KEY_PARKING_DURATION, parkingDuration);
        initialValues.put(KEY_DATE, strDate);
        initialValues.put(KEY_TIME, strTime);
        initialValues.put(KEY_PARKING_AMOUNT, parkingAmount);

        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    public Cursor getAllContacts()
    {
        return db.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_BUILDING_CODE,
                KEY_CAR_PLATE, KEY_HOST_SUITE, KEY_PARKING_DURATION, KEY_DATE, KEY_TIME, KEY_PARKING_AMOUNT}, null, null, null, null, null);
    }

    public void close()
    {
        DBHelper.close();
    }

}
