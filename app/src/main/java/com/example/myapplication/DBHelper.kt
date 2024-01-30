package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context?) :
    SQLiteOpenHelper(context, "Login.db", null, 1){
    override fun onCreate(MyDB: SQLiteDatabase) {
        MyDB.execSQL("CREATE TABLE users(username TEXT PRIMARY KEY, password TEXT)")
    }

    override fun onUpgrade(MyDB: SQLiteDatabase, i: Int, i1: Int) {
        MyDB.execSQL("DROP TABLE IF EXISTS users")
    }

    fun insertData(username: String?, password: String?): Boolean {
        val MyDB = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("username", username)
        contentValues.put("password", password)
        val result = MyDB.insert("users", null, contentValues)
        return if (result == -1L) false else true
    }

    fun checkUsername(username: String): Boolean {
        val MyDB = this.writableDatabase
        var res = true
        val cursor = MyDB.rawQuery("SELECT * FROM users WHERE username = ?", arrayOf(username))
        if (cursor.count <=0 ) res = false
        return res
    }
    fun checkUserpass(username: String, password: String): Boolean {
        val MyDB = this.writableDatabase
        var res = true
        val cursor = MyDB.rawQuery("SELECT * FROM users WHERE username = ? AND password = ?",
            arrayOf(username, password))
        if (cursor.count <=0 ) res = false
        return cursor.count > 0
    }

    companion object {
        const val DBNAME = "Login.db"
    }
}