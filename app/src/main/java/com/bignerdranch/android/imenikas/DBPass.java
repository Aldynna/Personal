package com.bignerdranch.android.imenikas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DBPass extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBPass.db";
    public static final String CONTACTS_TABLE_NAME = "password";
    public static final String CONTACTS_COLUMN_ID = "pass";

    public DBPass(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

        db.execSQL(
                "create table password" +
                        "(pass text)"
        );

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS password");

        onCreate(db);
    }

    public boolean insertPass (String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("pass", pass);


        db.insert("password", null, contentValues);
        return true;
    }
    public boolean isSetPass(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res=db.rawQuery( "select * from password", null );
        res.moveToFirst();
        if(res!=null && res.getCount()>0) return true;
        return false;
    }

    public String getPass(String pass) {
        String id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res=db.rawQuery( "select * from password", null );

        //res.moveToFirst();
        res.moveToFirst();
        if(res!=null && res.getCount()>0)
         id = res.getString(res.getColumnIndex("pass"));
        else id="";


        // String uname = crs.getString(crs.getColumnIndex("NAME"));
      return id;
    }

}
