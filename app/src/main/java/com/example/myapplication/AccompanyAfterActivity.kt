package com.example.myapplication

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AccompanyAfterActivity : AppCompatActivity() {
    lateinit var ap2DBManager: Ap2DBManager
    lateinit var sqlitedb: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accompanyafter)

        //액션바 이름 변경
        getSupportActionBar()?.setTitle("나의 동행")


        ap2DBManager = Ap2DBManager(this, "ap2", null, 1)
        sqlitedb = ap2DBManager.readableDatabase

        var tvDestination = findViewById<TextView>(R.id.tvDestination)
        var cursor: Cursor
        cursor = sqlitedb.rawQuery("SELECT * FROM ap2", null)

        // 마지막으로 입력한 정보(도착지)가 화면에 뜨게함
        if(cursor.moveToLast()) {
            tvDestination.text = cursor.getString(cursor.getColumnIndex("destination")).toString()
        }

        cursor.close()
        sqlitedb.close()
        ap2DBManager.close()


    }
}