package com.mad.StayCoza;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FlightDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Flights.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FlightContract.FlightEntry.TABLE_NAME + " (" +
                    FlightContract.FlightEntry._ID + " INTEGER PRIMARY KEY," +
                    FlightContract.FlightEntry.COLUMN_DEPARTURE + " TEXT," +
                    FlightContract.FlightEntry.COLUMN_ARRIVAL + " TEXT," +
                    FlightContract.FlightEntry.COLUMN_DEPARTURE_TIME + " TEXT," +
                    FlightContract.FlightEntry.COLUMN_ARRIVAL_TIME + " TEXT," +
                    FlightContract.FlightEntry.COLUMN_DURATION + " TEXT," +
                    FlightContract.FlightEntry.COLUMN_DATE + " TEXT," +
                    FlightContract.FlightEntry.COLUMN_PRICE + " TEXT," +
                    FlightContract.FlightEntry.COLUMN_AIRLINE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FlightContract.FlightEntry.TABLE_NAME;

    public FlightDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
