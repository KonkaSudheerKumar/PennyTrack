package com.example.pennytracker;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table PennyTracker(itemname varchar(20), itemprice varchar(20), date varchar(20), time varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertRecord( String eitemname, String eitemprice,  String date, String time) {
    SQLiteDatabase i=this.getWritableDatabase();
    i.execSQL("insert into PennyTracker values(?,?,?,?)",new String[]{eitemname,eitemprice,date,time});
    }

    public String display() {
        SQLiteDatabase dd=this.getWritableDatabase();
        String temp="";
        Cursor c=dd.rawQuery("select * from PennyTracker",null);
        while(c.moveToNext()){

            String eitemname=c.getString(0);
            String eitemprice=c.getString(1);
            String date=c.getString(2);
            String time=c.getString(3);
            temp=temp+eitemname+"|"+eitemprice+"|"+date+"|"+time+"\n";
        }
        return temp;
    }


    public String particularItem(String paritemm) {
        SQLiteDatabase p=this.getWritableDatabase();
        Cursor c=p.rawQuery("select itemname,itemprice ,date,time from PennyTracker where itemname=? ",new String[]{paritemm});
        String tmp="";
        while(c.moveToNext()){

            String r=c.getString(0);
            String d=c.getString(1);
            String mm=c.getString(2);
            String bb=c.getString(3);
            tmp=tmp+" |"+r+" |"+d+" |"+mm+" |"+bb+"\n";

        }return tmp;
    }

    public int getTotalSpent() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(itemprice) FROM PennyTracker", null);
        int totalSpent = 0;
        if (cursor.moveToFirst()) {
            totalSpent = cursor.getInt(0);
        }
        cursor.close();
        return totalSpent;
    }

    public void deleteItem(String par) {
        SQLiteDatabase d=this.getWritableDatabase();
        d.execSQL("delete from PennyTracker where itemname=?",new String[]{par});
    }

    public void parDate(String pardate) {
        SQLiteDatabase d=this.getWritableDatabase();
        Cursor c=d.rawQuery("select * from PennyTracker where date=?",new String[]{pardate});
        String tmp="";
        while(c.moveToNext()){
            String eitemname=c.getString(0);
            String eitemprice=c.getString(1);
            String date=c.getString(2);
            String time=c.getString(3);
            tmp=tmp+eitemname+"|"+eitemprice+"|"+date+"|"+time+"\n";
        }
    }

    public String fromDate(String fromdate) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select * from PennyTracker where date >= ?", new String[]{fromdate});
        String tmp="";
        while (c.moveToNext()) {
            String eitemname=c.getString(0);
            String eitemprice=c.getString(1);
            String date=c.getString(2);
            String time=c.getString(3);
            tmp=tmp+eitemname+"|"+eitemprice+"|"+date+"|"+time+"\n";
        }
        c.close();
        return tmp;
    }


    public int getTotalIncome() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(income) FROM PennyTracker", null);
        int totalIncome = 0;
        if (cursor.moveToFirst()) {
            totalIncome = cursor.getInt(0);
        }
        cursor.close();
        return totalIncome;
    }

}

