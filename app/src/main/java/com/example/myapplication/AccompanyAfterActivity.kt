package com.example.myapplication

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class AccompanyAfterActivity : AppCompatActivity() {
    lateinit var ap2DBManager: Ap2DBManager
    lateinit var ap4DBManager: Ap4DBManager
    lateinit var ap5DBManager: Ap5DBManager
    lateinit var sqlitedb: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accompanyafter)

        // 액션바 제목, 글자 색 변경
        val spannableString = SpannableString("나의 동행")
        spannableString.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(this, R.color.black)),
            0, spannableString.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        supportActionBar?.title = spannableString

        var tvDestination = findViewById<TextView>(R.id.tvDestination)
        var tvDate1 = findViewById<TextView>(R.id.tvDate1)
        //var tvDate2 = findViewById<TextView>(R.id.tvDate2)
        var tvTime1 = findViewById<TextView>(R.id.tvTime1)
        var tvTime2 = findViewById<TextView>(R.id.tvTime2)
        var tvAirLine = findViewById<TextView>(R.id.tvAirLine)
        var tvFName = findViewById<TextView>(R.id.tvFName)
        var tvRNum = findViewById<TextView>(R.id.tvRNum)
        var cursor: Cursor


        ap2DBManager = Ap2DBManager(this, "ap2", null, 1)
        ap4DBManager = Ap4DBManager(this,"ap4", null, 1)
        ap5DBManager = Ap5DBManager(this, "ap5", null, 1)

        // db ap2상의 마지막 행의 정보가 화면에 뜸
        sqlitedb = ap2DBManager.readableDatabase
        cursor = sqlitedb.rawQuery("SELECT * FROM ap2", null)

        if(cursor.moveToLast()) {
            tvDestination.text = cursor.getString(cursor.getColumnIndex("destination")).toString()
        }

        // db ap4상의 마지막 행의 정보가 화면에 뜸
        sqlitedb = ap4DBManager.readableDatabase
        cursor = sqlitedb.rawQuery("SELECT * FROM ap4", null)

        if(cursor.moveToLast()) {
            tvDate1.text = cursor.getString(cursor.getColumnIndex("Date1")).toString()
            //tvDate2.text = cursor.getString(cursor.getColumnIndex("Date2")).toString()
            tvTime1.text = cursor.getString(cursor.getColumnIndex("Time1")).toString()
            tvTime2.text = cursor.getString(cursor.getColumnIndex("Time2")).toString()
        }

        // db ap5상의 마지막 행의 정보가 화면에 뜸
        sqlitedb = ap5DBManager.readableDatabase
        cursor = sqlitedb.rawQuery("SELECT * FROM ap5", null)

        if(cursor.moveToLast()) {
            tvAirLine.text = cursor.getString(cursor.getColumnIndex("airLine")).toString()
            tvFName.text = cursor.getString(cursor.getColumnIndex("FName")).toString()
            tvRNum.text = cursor.getString(cursor.getColumnIndex("RNum")).toString()
        }


        cursor.close()
        sqlitedb.close()
        ap2DBManager.close()
        ap4DBManager.close()
        ap5DBManager.close()


    }
}