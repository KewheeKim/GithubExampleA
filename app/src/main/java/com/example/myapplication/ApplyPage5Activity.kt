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
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class ApplyPage5Activity : AppCompatActivity() {

    lateinit var ap5DBManager: Ap5DBManager
    lateinit var sqlitedb: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_applypage5)

        // 액션바 제목, 글자 색 변경
        val spannableString = SpannableString("해외 입양 이동 봉사 신청")
        spannableString.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(this, R.color.black)),
            0, spannableString.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        supportActionBar?.title = spannableString


        var nextBtn = findViewById<Button>(R.id.nextBtn)
        var edtFName = findViewById<EditText>(R.id.edtFName)
        var edtRNum = findViewById<EditText>(R.id.edtRNum)
        var rGroup = findViewById<RadioGroup>(R.id.rGroup)

        // 라디오 버튼이 눌릴 때마다 selectedRBtn의 값이 바뀜
        rGroup.setOnCheckedChangeListener { group, checkedId ->
            // 선택된 라디오 객체가 selectedRBtn에 담김
            val selectedRBtn = findViewById<RadioButton>(checkedId)

            // 다음 화면으로 전환, 데베에 저장
            nextBtn.setOnClickListener({
                var valueAirLine : String = ""
                var valueFName : String = ""
                var valueRNum : String = ""

                valueAirLine = selectedRBtn.text.toString()
                valueFName = edtFName.text.toString()
                valueRNum = edtRNum.text.toString()

                // 예약 번호 12자리를 알맞게 입력하면 페이지 이동, 아닐 경우 토스트 문구 출력
                    if(valueRNum.length == 12) {
                        ap5DBManager = Ap5DBManager(this, "ap5", null, 1)

                        sqlitedb = ap5DBManager.writableDatabase
                        sqlitedb.execSQL("INSERT INTO ap5 VALUES ('$valueAirLine','$valueFName', $valueRNum)")
                        sqlitedb.close()

                        // 알맞게 입력시 다음 화면으로 전환
                        val intent = Intent(this, ApplyPage6Activity::class.java)
                        startActivity(intent)
                    } else {

                        // 예약 번호를 잘못 입력했다는 토스트 문구 출력
                        Toast.makeText(this, "예약 번호는 12자리 입니다.", Toast.LENGTH_SHORT).show()
                    }

            })
        }
    }
}