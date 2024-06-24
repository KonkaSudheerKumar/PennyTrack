package com.example.pennytracker;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class signprocess extends SQLiteOpenHelper {
    public signprocess(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("create table signprocess(name varchar(20), email varchar(20), username varchar(20), password varchar(20), income varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertRecord(String namee, String emaill, String usernamee, String passwordd, String incomee) {
       SQLiteDatabase i=this.getWritableDatabase();
       i.execSQL("insert into signprocess values(?,?,?,?,?)",new String[]{namee,emaill,usernamee,passwordd,incomee});
    }

    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM signprocess WHERE username=? AND password=?", new String[]{username, password});
        boolean exists = cursor.moveToFirst();
        cursor.close();
        db.close();
        return exists;
    }

    public int getTotalIncome(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT income FROM signprocess WHERE username=?", new String[]{username});
        int totalIncome = 0;
        if (cursor.moveToFirst()) {
            totalIncome = cursor.getInt(0); // Retrieve income as an integer
        }
        cursor.close();
        db.close();
        return totalIncome;
    }


}
