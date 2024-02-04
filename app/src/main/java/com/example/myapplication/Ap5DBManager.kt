package com.example.myapplication

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Ap5DBManager (context: Context,
                    name: String?,
                    factory: SQLiteDatabase.CursorFactory?,
                    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {

    // ap5 테이블 생성
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("CREATE TABLE ap5 (airLine text, FName text, RNum INTEGER)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}