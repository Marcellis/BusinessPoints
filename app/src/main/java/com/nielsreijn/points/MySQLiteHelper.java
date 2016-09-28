package com.nielsreijn.points;

/**
 * Created by reijn on 28-9-2016.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {
    // Database info
    private static final String DATABASE_NAME = "points.db";
    private static final int DATABASE_VERSION = 2;

    // Points table
    public static final String TABLE_POINTS = "points";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TEACHER = "teacher";
    public static final String COLUMN_SUBJECT = "subject";
    public static final String COLUMN_GRADE = "grade";

    // Creating the table
    private static final String DATABASE_CREATE_POINTS =
            "CREATE TABLE " + TABLE_POINTS +
                    "(" +
                    COLUMN_ID + " integer primary key autoincrement, " +
                    COLUMN_TEACHER + " text not null, " +
                    COLUMN_SUBJECT + " text not null, " +
                    COLUMN_GRADE + " text not null " +
                    ");";

    // Mandatory constructor which passes the context, database name and database version and passes it to the parent
    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        // Execute the sql to create the table points
        database.execSQL(DATABASE_CREATE_POINTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*
         * When the database gets upgraded you should handle the update to make sure there is no data loss.
         * This is the default code you put in the upgrade method, to delete the table and call the oncreate again.
         */
        if (oldVersion == 1) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_POINTS);
            onCreate(db);
        }
    }
}