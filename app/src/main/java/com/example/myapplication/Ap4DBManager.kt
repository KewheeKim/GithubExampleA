package com.example.myapplication

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Ap4DBManager (context: Context,
                    name: String?,
                    factory: SQLiteDatabase.CursorFactory?,
                    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {

    // ap4 테이블 생성
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("CREATE TABLE ap4 (Date1 text, Date2 text, Time1 text, Time2 text)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}