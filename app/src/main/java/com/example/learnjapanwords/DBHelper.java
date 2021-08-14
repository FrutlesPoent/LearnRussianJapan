package com.example.learnjapanwords;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1; // old or not
    public static final String DATABASE_NAME = "japanWords";
    public static final String TABLE_WORDS = "russian";

    public static final String KEY_ID = "_id";
    public static final String KEY_JAPAN ="japan";
    public static final String KEY_RUSSIAN ="russia";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void deleteDB(Context context) {
        context.deleteDatabase(DATABASE_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_WORDS + "(" + KEY_ID + "integer primary key,"
                    + KEY_JAPAN + " text," + KEY_RUSSIAN + " text" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_WORDS);

        onCreate(db);
    }

    public void dbEnterData(String russia, String japan) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_JAPAN, japan);
        contentValues.put(KEY_RUSSIAN, russia);

        database.insert(TABLE_WORDS, null, contentValues);

        Cursor cursor = database.query(TABLE_WORDS, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(KEY_ID);
            int russiaId = cursor.getColumnIndex(KEY_RUSSIAN);
            int japanId = cursor.getColumnIndex(KEY_JAPAN);

            do {
//                Log.d("mLog", " ID = " + cursor.getInt(idIndex) + ", nameR = " + cursor.getString(russiaId) + ", nameJ = " + cursor.getString(japanId));
            } while (cursor.moveToNext());
        }
        cursor.close();
        close();

        }
}
