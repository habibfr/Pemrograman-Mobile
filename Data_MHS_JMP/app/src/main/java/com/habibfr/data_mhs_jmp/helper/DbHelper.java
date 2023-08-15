package com.habibfr.data_mhs_jmp.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    static final String DATABASE_NAME = "mahasiswa.db";
//    static final String DATABASE_NAME = "jmp_db";

    public static final String TABLE_SQLite = "mahasiswa";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOMOR = "nomor";
    public static final String COLUMN_NAMA = "nama";
    public static final String COLUMN_ALAMAT = "alamat";
    public static final String COLUMN_TANGGAL_LAHIR = "tgl_lahir";
    public static final String COLUMN_JENIS_KELAMIN = "jenis_kelamin";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_SQLite + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NOMOR + " TEXT,"
            + COLUMN_NAMA + " TEXT,"
            + COLUMN_TANGGAL_LAHIR + " TEXT,"
            + COLUMN_JENIS_KELAMIN + " TEXT,"
            + COLUMN_ALAMAT + " TEXT)";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
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
                map.put(COLUMN_NOMOR, cursor.getString(1));
                map.put(COLUMN_NAMA, cursor.getString(2));
                map.put(COLUMN_TANGGAL_LAHIR, cursor.getString(3));
                map.put(COLUMN_JENIS_KELAMIN, cursor.getString(4));
                map.put(COLUMN_ALAMAT, cursor.getString(5));
                wordList.add(map);
            } while (cursor.moveToNext());
        }

        System.out.println("getAllData : " + wordList);

        return wordList;
    }

    public void insert(String nomor, String name, String hbd, String jk, String alamat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMOR, nomor);
        values.put(COLUMN_NAMA, name);
        values.put(COLUMN_TANGGAL_LAHIR, hbd);
        values.put(COLUMN_JENIS_KELAMIN, jk);
        values.put(COLUMN_ALAMAT, alamat);

        db.insert(TABLE_SQLite, null, values);
        db.close();
    }

    public void update(int id, String nomor, String name, String hbd, String jk, String alamat) {
        SQLiteDatabase db = this.getWritableDatabase();

        String queryValues = "UPDATE " + TABLE_SQLite + " SET " +
                COLUMN_NOMOR + " = '" + nomor + "', " +
                COLUMN_NAMA + " = '" + name + "', " +
                COLUMN_TANGGAL_LAHIR + " = '" + hbd + "', " +
                COLUMN_JENIS_KELAMIN + " = '" + jk + "', " +
                COLUMN_ALAMAT + " = '" + alamat + "' " +
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
