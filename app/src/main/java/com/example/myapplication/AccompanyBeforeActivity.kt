package com.example.myapplication

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat


class AccompanyBeforeActivity : AppCompatActivity() {
    lateinit var ap2DBManager: Ap2DBManager
    lateinit var ap4DBManager: Ap4DBManager
    lateinit var ap5DBManager: Ap5DBManager
    lateinit var sqlitedb: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accompanybefore)

        // 액션바 제목, 글자 색 변경
        val spannableString = SpannableString("나의 동행")
        spannableString.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(this, R.color.black)),
            0, spannableString.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        supportActionBar?.title = spannableString

        var addBtn = findViewById<ImageButton>(R.id.addBtn)
        var tvDestination = findViewById<TextView>(R.id.tvDestination)
        var tvDate1 = findViewById<TextView>(R.id.tvDate1)
        var tvTime1 = findViewById<TextView>(R.id.tvTime1)
        var tvTime2 = findViewById<TextView>(R.id.tvTime2)
        var tvAirLine = findViewById<TextView>(R.id.tvAirLine)
        var tvFName = findViewById<TextView>(R.id.tvFName)
        var tvRNum = findViewById<TextView>(R.id.tvRNum)
        var afterApply = findViewById<RelativeLayout>(R.id.afterApply)
        var afterApply_visibility: Boolean = false
        var toAdoptionDetaildpage1 = findViewById<Button>(R.id.toAdoptionDetaildpage1)
        var lastVolunteerLayout = findViewById<RelativeLayout>(R.id.lastVolunteerLayout)
        var cursor: Cursor


        // ap2, ap4, ap5 dbManager를 생성하여 테이블 관리
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


        // ApplyPage7Activity에서 intent로 afterApply_visibility 값을 받아옴
        afterApply_visibility = intent.getBooleanExtra("afterApply_visibility", false)


        // afterApply_visibility 혹은 ApplyPage7Activity.VISIBILITY중 하나 이상이 true이면 신청 후 정보 화면이 뜨게함
        if(ApplyPage7Activity.VISIBILITY == true) {

            // afterApply 레이아웃을 보이게 만듦
            afterApply.visibility = View.VISIBLE

            val params = lastVolunteerLayout.layoutParams as ViewGroup.MarginLayoutParams
            params.topMargin = 630

            // 변경된 마진 값을 lastVolunteerLayout에 적용
            lastVolunteerLayout.layoutParams = params

        } else {
            afterApply.visibility = View.GONE
        }


        cursor.close()
        sqlitedb.close()
        ap2DBManager.close()
        ap4DBManager.close()
        ap5DBManager.close()

        toAdoptionDetaildpage1.setOnClickListener({
            val intent = Intent(this, AdoptionDetailedpage::class.java)
            startActivity(intent)
        })

        // 다음 화면으로 전환
        addBtn.setOnClickListener({
            val intent = Intent(this, ApplyPage1Activity::class.java)
            startActivity(intent)
        })
    }
}