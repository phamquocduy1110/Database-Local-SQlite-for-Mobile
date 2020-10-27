package com.example.databaselocalsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class SinhVien extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "dvsvdemo";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "sinhvien";
    private static final String KEY_ID = "id";
    private static final String KEY_FIRST = "hosv";
    private static final String KEY_LAST = "tensv";
    private static final String KEY_CLASS = "lop";

    public SinhVien(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + TABLE_NAME +
                "(" +
                KEY_ID + " integer primary key autoincrement, " +
                KEY_FIRST + " text, " +
                KEY_LAST + " text, " +
                KEY_CLASS + " text" +
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);
    }

    public  void taosv (String ho, String ten, String lop) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues noidung = new ContentValues();
        noidung.put(KEY_FIRST, ho);
        noidung.put(KEY_LAST, ten);
        noidung.put(KEY_CLASS, lop);

        String nullColumnHack = null;
        db.insert(TABLE_NAME, nullColumnHack, noidung);
    }

    public Cursor getAllSv() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor con_tro = db.rawQuery("select * from " + TABLE_NAME, null);
        return con_tro;
    }

    public boolean xoasv (String _id) {
        boolean done = false;
        SQLiteDatabase db = this.getWritableDatabase();
        String[] whereArgs ={_id};
        db.delete(TABLE_NAME, KEY_ID +"= ?", whereArgs);
        return done;
    }

    public int capnhatsv(String _id, String ho, String ten, String lop) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        ContentValues values = new ContentValues();
        values.put(KEY_FIRST, ho);
        values.put(KEY_LAST, ten);
        values.put(KEY_CLASS, lop);
        String[] whereArgs= {_id};

        int count = db.update(TABLE_NAME, contentValues, this.KEY_ID +" = ?", whereArgs);
        return count;
    }

    public Cursor timsv(String searchText) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_FIRST + "LIKE '%" + searchText + "%'";
        Cursor cursor = db.rawQuery(sql, null);
        return cursor;
    }


}
