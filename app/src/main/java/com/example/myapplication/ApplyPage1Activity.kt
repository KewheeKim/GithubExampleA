package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class ApplyPage1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_applypage1)

        // 액션바 제목, 글자 색 변경
        val spannableString = SpannableString("해외 입양 이동 봉사 신청")
        spannableString.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(this, R.color.black)),
            0, spannableString.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        supportActionBar?.title = spannableString

        var nextBtn = findViewById<Button>(R.id.nextBtn)
        var check1 = findViewById<CheckBox>(R.id.check1)
        var check2 = findViewById<CheckBox>(R.id.check2)


            // 다음 화면으로 전환
            nextBtn.setOnClickListener({

                // 체크 박스 두 개가 모두 눌렸을 경우
                if(check1.isChecked == true && check2.isChecked == true) {
                    val intent = Intent(this, ApplyPage2Activity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "모든 숙지 사항을 확인해 주세요.", Toast.LENGTH_SHORT).show()
                }

            })

    }
}