package com.example.itmba_transportcostcomparator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "TransportTracker.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_REPORTS = "reports";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_MODE = "mode_of_transport";
    public static final String COLUMN_TYPE = "transport_type";
    public static final String COLUMN_DAILY_COST = "daily_cost";
    public static final String COLUMN_MONTHLY_COST = "monthly_cost";
    public static final String COLUMN_MONTHLY_DISTANCE = "monthly_distance";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_RECOMMENDATION = "recommendation";
    public static final String COLUMN_DATE = "date_created";

    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_REPORTS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_MODE + " TEXT, " +
                    COLUMN_TYPE + " TEXT, " +
                    COLUMN_DAILY_COST + " REAL, " +
                    COLUMN_MONTHLY_COST + " REAL, " +
                    COLUMN_MONTHLY_DISTANCE + " REAL, " +
                    COLUMN_CATEGORY + " TEXT, " +
                    COLUMN_RECOMMENDATION + " TEXT, " +
                    COLUMN_DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REPORTS);
        onCreate(db);
    }

    public long insertReport(String mode, String type, double dailyCost, double monthlyCost, double monthlyDistance, String category, String recommendation) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MODE, mode);
        values.put(COLUMN_TYPE, type);
        values.put(COLUMN_DAILY_COST, dailyCost);
        values.put(COLUMN_MONTHLY_COST, monthlyCost);
        values.put(COLUMN_MONTHLY_DISTANCE, monthlyDistance);
        values.put(COLUMN_CATEGORY, category);
        values.put(COLUMN_RECOMMENDATION, recommendation);
        return db.insert(TABLE_REPORTS, null, values);
    }

    public Cursor getReport(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_REPORTS, null, COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)}, null, null, null);
    }

    public Cursor getAllReports() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(
                "SELECT " + COLUMN_ID + " AS _id, * FROM " + TABLE_REPORTS + " ORDER BY " + COLUMN_ID + " DESC",
                null
        );
    }
}
