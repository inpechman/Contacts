package com.sruly.stu.contacts.logic;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by stu on 4/30/2018.
 *
 */

public class DataBaseMgr {
    private static DataBaseMgr instance;
    Context context;
    private SQLiteDatabase db;

    private DataBaseMgr(Context context){
        this.context = context;
        db = context.openOrCreateDatabase("contacts.db", Context.MODE_PRIVATE, null);
        createTables();
    }

    private void createTables() {
        db.execSQL("CREATE TABLE IF NOT EXISTS all_contacts (first_name TEXT, last_name TEXT, id TEXT, birth_year INTEGER)");
    }

    public static DataBaseMgr getInstance(Context context){
        if (instance == null){
            instance = new  DataBaseMgr(context);
        }
        return instance;
    }

    public synchronized void insertContact(Contact contact){
        String firstName = contact.getFirstName();
        String lastName = contact.getLastName();
        String id = contact.getId();
        int birthYear = contact.getBirthYear();
        db.execSQL("INSERT INTO all_contacts VALUES ('" +
                firstName + "', '" +
                lastName + "', " +
                id + ", " +
                birthYear +
                ")");


    }

    public synchronized void deleteContactByName(String firstName, String lastName){

    }

    public synchronized ArrayList<Contact> getByDate(int year, boolean reverse){
        return null;
    }

    public synchronized ArrayList<Contact> getAll(){
        ArrayList<Contact> contactArrayList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM all_contacts ORDER BY birth_year ASC", null);
        if (cursor != null){
            if (cursor.moveToFirst()){
                do {
                    Contact contact = new Contact(
                            cursor.getString(cursor.getColumnIndex("first_name")),
                            cursor.getString(cursor.getColumnIndex("last_name")),
                            cursor.getString(cursor.getColumnIndex("id")),
                            cursor.getInt(cursor.getColumnIndex("birth_year"))
                            );
                    contactArrayList.add(contact);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return contactArrayList;
    }


}
