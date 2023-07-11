package com.example.myapplication;



import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Android.db";
    private static final String TABLE_NAME = "Result";
    private static final String COLUMN_QUESTION = "question";
    private static final String COLUMN_QUESTIONNO = "question_no";

    private static final String COLUMN_STATUS = "status";

    private static final String COLUMN_CORRECTANSWER = "correct_answer";




    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                + COLUMN_QUESTION + " TEXT,"
                + COLUMN_QUESTIONNO + " INTEGER,"
                + COLUMN_STATUS + " TEXT,"
                + COLUMN_CORRECTANSWER + " TEXT"
                + ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }

    public Boolean insertQuestion(Question data) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_QUESTION, data.question);
        values.put(COLUMN_QUESTIONNO, data.questionNo);
        values.put(COLUMN_STATUS, data.status);
        values.put(COLUMN_CORRECTANSWER, data.correctAnswer);


        long res =  db.insert(TABLE_NAME, null, values);
        db.close();
        if (res == -1) {
            return false;
        }
        else return true;


    }

    public void clearDB(){
        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "DELETE FROM " + TABLE_NAME;

        db.execSQL(sql);
    }




}

