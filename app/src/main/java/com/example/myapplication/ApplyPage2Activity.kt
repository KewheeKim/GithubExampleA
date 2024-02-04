package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class ApplyPage2Activity : AppCompatActivity() {

    lateinit var ap2DBManager: Ap2DBManager
    lateinit var sqlitedb: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_applypage2)

        // 액션바 제목, 글자 색 변경
        val spannableString = SpannableString("해외 입양 이동 봉사 신청")
        spannableString.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(this, R.color.black)),
            0, spannableString.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        supportActionBar?.title = spannableString

        var nextBtn = findViewById<Button>(R.id.nextBtn)
        var rGroup = findViewById<RadioGroup>(R.id.rGroup)

        // Ap2DBManager 클래스 이용
        ap2DBManager = Ap2DBManager(this, "ap2", null, 1)


        rGroup.setOnCheckedChangeListener { group, checkedId ->
            // 선택된 라디오 버튼 객체 가져오기
            val selectedRBtn = findViewById<RadioButton>(checkedId)

            // 라디오 버튼의 선택 상태가 변경될 때마다 처리할 내용
            // 다음 화면으로 전환, db에 저장
            nextBtn.setOnClickListener({

                // destination에 선택된 객체의 id가 저장되게 함
                val destination = resources.getResourceEntryName(selectedRBtn.id)

                sqlitedb = ap2DBManager.writableDatabase
                sqlitedb.execSQL("INSERT INTO ap2 VALUES ('$destination')")
                sqlitedb.close()

                val intent = Intent(this, ApplyPage3Activity::class.java)
                startActivity(intent)
            })
        }
    }

}