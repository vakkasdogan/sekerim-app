package com.example.saglikd2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelper extends SQLiteOpenHelper {
    public MyHelper(Context context){
        super(context,"MyDatabase",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "create Table myTable (xValues INTEGER, yValues INTEGER )";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insertData(long valX, int valY){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("xValues",valX);
        contentValues.put("yValues",valY);

        db.insert("myTable",null,contentValues);
    }
}
