package com.remember.jeanju34min.rememberme.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.remember.jeanju34min.rememberme.Activity.MainActivity;

/**
 * Created by jeanju34.min on 2017-11-01.
 */

public class DbHelper {

    public static final int DB_ID = 0;
    public static final int DB_TITLE = 1;
    public static final int DB_ADDR = 2;
    public static final int DB_LNG = 3;
    public static final int DB_LAT = 4;
    public static final int DB_URL = 5;

    private static final String DATABASE_NAME = "wannagobook.db";
    private static final int DATABASE_VERSION = 1;
    public static SQLiteDatabase mDB;
    private DatabaseHelper mDBHelper;
    private Context mCtx;
    Cursor mCursor = null;

    private class DatabaseHelper extends SQLiteOpenHelper {

        // 생성자
        public DatabaseHelper(Context context, String name,
                              SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        // 최초 DB를 만들때 한번만 호출된다.
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(WannagoDB.CreateDB._CREATE);

        }

        // 버전이 업데이트 되었을 경우 DB를 다시 만들어 준다.
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+WannagoDB.CreateDB._TABLENAME);
            onCreate(db);
        }
    }

    public DbHelper(Context context){
        this.mCtx = context;
    }

    public DbHelper open() throws SQLException {
        mDBHelper = new DatabaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
        mDB = mDBHelper.getWritableDatabase();
        mCursor = mDB.rawQuery("SELECT * FROM "+ WannagoDB.CreateDB._TABLENAME, null);
        return this;
    }

    public void insert(String title, String address, long longitude, long latitude, String URL){
        // DB에 입력한 값으로 행 추가
        Log.v("MY_TAG", "INSERT TO DB");
        mDB.execSQL("INSERT INTO " + WannagoDB.CreateDB._TABLENAME + " VALUES(null, '" + title + "', '" + address + "', " + longitude + ", " + latitude +", " + URL + ");");
    }

    public void delete(int position) {
        // 입력한 항목과 일치하는 행 삭제
        mDB.execSQL("DELETE FROM " + WannagoDB.CreateDB._TABLENAME + " WHERE _ID = " + position + ";");
    }

    public void close(){
        mCursor.close();
        mDB.close();
    }

    public int getCount() {
        return mCursor.getCount();
    }

    public String getURL(int position){
        mCursor.moveToFirst();
        for (int i = 0; i<position; i++) {
            mCursor.moveToNext();
        }
        if(mCursor == null)
            return null;
        else
            return mCursor.getString(DB_URL);
    }

    public int getID(int position) {
        mCursor.moveToFirst();
        for (int i = 0; i<position; i++) {
            mCursor.moveToNext();
        }
        if(mCursor == null)
            return -1;
        else
            return mCursor.getInt(DB_ID);
    }

    public String getTitle(int position) {
        mCursor.moveToFirst();
        for (int i = 0; i<position; i++) {
            mCursor.moveToNext();
        }
        if(mCursor == null)
            return null;
        else
            return mCursor.getString(DB_TITLE);
    }

    public String getAddress(int position) {
        mCursor.moveToFirst();
        for (int i = 0; i<position; i++) {
            mCursor.moveToNext();
        }

        if(mCursor == null)
            return null;
        else
            return mCursor.getString(DB_ADDR);
    }

}
