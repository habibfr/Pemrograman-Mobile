package com.habibfr.aplikasi_sqlite.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;

    static final String DATABASE_NAME = "habibfr.jmp_db";

    public static final String TABLE_SQLite = "sqlite";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ADDRESS = "address";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " + TABLE_SQLite + " ( " +
//                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                COLUMN_NAME + " TEXT NOT NULL, " +
//                COLUMN_ADDRESS + " TEXT NOT NULL" + " )";
//        System.out.println(SQL_CREATE_MOVIE_TABLE);

        String query = "CREATE TABLE " + TABLE_SQLite + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT NOT NULL,"
                + COLUMN_ADDRESS + " TEXT NOT NULL)";

        // at last we are calling a exec sql
        // method to execute above sql query
//        db.execSQL(query);

        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SQLite);
        onCreate(sqLiteDatabase);
    }

    public ArrayList<HashMap<String, String>> getAllData() {
        ArrayList<HashMap<String, String>> wordList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_SQLite;

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(COLUMN_ID, cursor.getString(0));
                map.put(COLUMN_NAME, cursor.getString(1));
                map.put(COLUMN_ADDRESS, cursor.getString(2));
                wordList.add(map);
            } while (cursor.moveToNext());
        }

        System.out.println("getAllData : " + wordList);

        return wordList;
    }

    public void insert(String name, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryValues = "INSERT INTO " + TABLE_SQLite + "(name, address) " +
                " VALUES ('" + name + "', '" + address + "')";

        System.out.println("insert : " + queryValues);
        db.execSQL(queryValues);
        db.close();
    }

    public void update(int id, String name, String address) {
        SQLiteDatabase db = this.getWritableDatabase();

        String queryValues = "UPDATE " + TABLE_SQLite + " SET " +
                COLUMN_NAME + " = '" + name + "', " +
                COLUMN_ADDRESS + " = '" + address + "' " +
                "WHERE " + COLUMN_ID + " = '" + id + "'";

        System.out.println("update : " + queryValues);
        db.execSQL(queryValues);
        db.close();
    }

    public void delete(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        String updateQuery = "DELETE FROM " + TABLE_SQLite + " WHERE " + COLUMN_ID + " = '" + id + "'";

        System.out.println("delete " + updateQuery);
        db.execSQL(updateQuery);
        db.close();
    }
}
