package com.nielsreijn.points;

/**
 * Created by reijn on 28-9-2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DataSource {
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] pointsAllColumns = {MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_TEACHER,
            MySQLiteHelper.COLUMN_SUBJECT, MySQLiteHelper.COLUMN_GRADE};

    public DataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
        database = dbHelper.getWritableDatabase();
        dbHelper.close();
    }

    // Opens the database to use it
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    // Closes the database when you no longer need it
    public void close() {
        dbHelper.close();
    }

    public long createPoint(String teacher, String subject, String grade) {
        // If the database is not open yet, open it
        if (!database.isOpen())
            open();

        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_TEACHER, teacher);
        values.put(MySQLiteHelper.COLUMN_SUBJECT, subject);
        values.put(MySQLiteHelper.COLUMN_GRADE, grade);
        long insertId = database.insert(MySQLiteHelper.TABLE_POINTS, null, values);

        // If the database is open, close it
        if (database.isOpen())
            close();

        return insertId;
    }

    public void deletePoint(long id) {
        if (!database.isOpen())
            open();

        database.delete(MySQLiteHelper.TABLE_POINTS, MySQLiteHelper.COLUMN_ID + " =?", new String[]{Long.toString(id)});

        if (database.isOpen())
            close();
    }

    public void updatePoint(bPoint bpoint) {
        if (!database.isOpen())
            open();

        ContentValues args = new ContentValues();
        args.put(MySQLiteHelper.COLUMN_TEACHER, bpoint.getTeacher());
        args.put(MySQLiteHelper.COLUMN_SUBJECT, bpoint.getSubject());
        args.put(MySQLiteHelper.COLUMN_GRADE, bpoint.getGrade());
        database.update(MySQLiteHelper.TABLE_POINTS, args, MySQLiteHelper.COLUMN_ID + "=?", new String[]{Long.toString(bpoint.getId())});

        if (database.isOpen())
            close();
    }

    public Cursor getAllPointsCursor() {
        if (!database.isOpen())
            open();

        Cursor cursor = database.rawQuery(
                "SELECT * FROM " + MySQLiteHelper.TABLE_POINTS, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        if (database.isOpen())
            close();

        return cursor;
    }

    public void deleteAllPoints(){
        if (!database.isOpen())
            open();

        database.delete(MySQLiteHelper.TABLE_POINTS, null, null);

        if (database.isOpen())
            close();
    }


    private bPoint cursorTobPoint(Cursor cursor) {
        try {
            bPoint bPoint = new bPoint();
            bPoint.setId(cursor.getLong(cursor.getColumnIndexOrThrow(MySQLiteHelper.COLUMN_ID)));
            bPoint.setTeacher(cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.COLUMN_TEACHER)));
            bPoint.setSubject(cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.COLUMN_SUBJECT)));
            bPoint.setGrade(cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.COLUMN_GRADE)));
            return bPoint;
        } catch (CursorIndexOutOfBoundsException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public bPoint getbPoint(long columnId) {
        if (!database.isOpen())
            open();

        Cursor cursor = database.rawQuery(
                "SELECT points.*" +
                        " FROM " + MySQLiteHelper.TABLE_POINTS +
                        " WHERE points." + MySQLiteHelper.COLUMN_ID + " = " + columnId, null);

        cursor.moveToFirst();
        bPoint bPoint = cursorTobPoint(cursor);
        cursor.close();

        if (database.isOpen())
            close();

        return bPoint;
    }



}