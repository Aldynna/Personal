package com.bignerdranch.android.imenikas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by korisnik on 29.06.2017..
 */

public class DBBaza extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String CONTACTS_TABLE_NAME = "contacts";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_NAME = "name";
    public static final String CONTACTS_COLUMN_EMAIL = "email";
    public static final String CONTACTS_COLUMN_STREET = "street";
    public static final String CONTACTS_COLUMN_CITY = "place";
    public static final String CONTACTS_COLUMN_PHONE = "phone";

    public static final String NOTES_TABLE_NAME = "notes";
    public static final String NOTES_COLUMN_ID = "id";
    public static final String NOTES_COLUMN_NAME = "name";
    public static final String NOTES_COLUMN_EMAIL = "datum";
    public static final String NOTES_COLUMN_STREET = "grad";
    public static final String NOTES_COLUMN_CITY = "note";

    public static final String BAZA_TABLE_NAME = "password";
    public static final String BAZA_COLUMN_ID = "pass";



    public DBBaza(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table notes " +
                        "(nid integer primary key, name text,datum text,grad text,note text)"
        );
        db.execSQL(
                "create table contacts " +
                        "(id integer primary key, name text,phone text,email text, street text,place text)"
        );

        db.execSQL(
                "create table password" +
                        "(pass text)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS contacts");
        db.execSQL("DROP TABLE IF EXISTS notes");
        db.execSQL("DROP TABLE IF EXISTS password");
        onCreate(db);
    }



    public boolean insertContact (String name, String phone, String email, String street,String place) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("street", street);
        contentValues.put("place", place);
        db.insert("contacts", null, contentValues);
        return true;
    }
    public int getId(int place) {
        int mjesto=0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res=db.rawQuery( "select * from contacts", null );
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


    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts where id="+id+"", null );
        return res;
    }

    public boolean updateContact (Integer id, String name, String phone, String email, String street,String place) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("street", street);
        contentValues.put("place", place);
        db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }
    public boolean insertPass (String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("pass", pass);


        db.insert("password", null, contentValues);
        return true;
    }


    public Integer deleteContact (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("contacts",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public boolean isSetPass(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res=db.rawQuery( "select * from password", null );
        res.moveToFirst();
        if(res!=null && res.getCount()>0) return true;
        return false;
    }

    public boolean getPass(String pass) {
        int mjesto=0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res=db.rawQuery( "select * from password where pass="+pass+"", null );
        res.moveToFirst();
        if(res!=null && res.getCount()>0) return true;


        // String uname = crs.getString(crs.getColumnIndex("NAME"));
        return false;
    }

    public ArrayList<String> getAllCotacts() {
        ArrayList<String> array_list = new ArrayList<String>();


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }




    public boolean insertNote(String name, String datum, String grad, String note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("datum", datum);
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


    public int getIdN(int place) {
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

    public boolean updateNote (Integer id, String name, String datum, String grad, String note) {
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

    public Integer deleteNote (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("notes",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }


    public ArrayList<String> getAllNotes() {
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
