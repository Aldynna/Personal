package com.bignerdranch.android.imenikas;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHNEW extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBNNEW.db";
    public static final String CONTACTS_TABLE_NAME = "notes";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_NAME = "name";
    public static final String CONTACTS_COLUMN_EMAIL = "datum";
    public static final String CONTACTS_COLUMN_STREET = "grad";
    public static final String CONTACTS_COLUMN_CITY = "note";
    public static final String CONTACTS_COLUMN_PHONE = "phone";


    public static final String NOTES_TABLE_NAME = "notes";
    public static final String NOTES_COLUMN_ID = "nid";
    public static final String NOTES_COLUMN_NAME = "name";
    public static final String NOTES_COLUMN_DATE = "datum";
    public static final String NOTES_COLUMN_NOTE= "note";
    public static final String NOTES_COLUMN_GRAD= "grad";



    public DBHNEW(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

        db.execSQL(
                "create table notes" +
                        "(id integer primary key, name text,datum text,grad text, note text)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS notes");

        onCreate(db);
    }


    public boolean insertContact (String name, String grad, String note) {
        SQLiteDatabase db = this.getWritableDatabase();
        String dat = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("datum", dat);
        contentValues.put("grad", grad);
        contentValues.put("note", note);

        db.insert("notes", null, contentValues);
        return true;
    }



    public Cursor getNote(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "select * from notes where id="+id+"", null );
        return res;
    }
    public int getId(int place) {
        int mjesto=0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res=db.rawQuery( "select * from notes", null );
        res.moveToFirst();
        int id=0;
        while(res.isAfterLast() == false)
        {
            if(mjesto==place) {
                id = res.getInt(res.getColumnIndex("id"));
                break;
            }
            res.moveToNext();
            mjesto++;
        }
       // String uname = crs.getString(crs.getColumnIndex("NAME"));
        return id;
    }


    public int numberOfRowsContacts(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
        return numRows;
    }



    public boolean updateContact (Integer id, String name, String datum, String grad, String note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("datum", datum);
        contentValues.put("grad", grad);
        contentValues.put("note", note);
        //contentValues.put("place", place);
        db.update("notes", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }



    public Integer deleteContact (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("notes",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }



    public ArrayList<String> getAllContacts() {
        ArrayList<String> array_list = new ArrayList<String>();




        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from notes", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }



}