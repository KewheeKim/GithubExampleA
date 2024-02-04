package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class ApplyPage3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_applypage3)

        // 액션바 제목, 글자 색 변경
        val spannableString = SpannableString("해외 입양 이동 봉사 신청")
        spannableString.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(this, R.color.black)),
            0, spannableString.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        supportActionBar?.title = spannableString


        var nextBtn = findViewById<Button>(R.id.nextBtn)

        nextBtn.setOnClickListener({

            //다음 화면으로 전환
            val intent = Intent(this, ApplyPage4Activity::class.java)
            startActivity(intent)
        })
    }


}