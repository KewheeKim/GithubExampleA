package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

import android.text.Spannable
import android.text.style.ForegroundColorSpan
import androidx.core.content.ContextCompat

class AdoptionView : AppCompatActivity() {
    private lateinit var btnVolReview: Button
    private lateinit var btn_AdoptReview: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adoption_view)

        // 버튼 초기화
        btnVolReview = findViewById(R.id.btnVolReview)
        btn_AdoptReview = findViewById(R.id.btn_AdoptReview)

        btnVolReview.setOnClickListener {
            // 봉사 후기 버튼 클릭 시 VolunteerView로 이동하는 코드
            val intent = Intent(this, VolunteerView::class.java)
            startActivity(intent)
        }

        btn_AdoptReview.setOnClickListener {
            // 작성된 입양 후기글 제목 클릭 시 AdoptionDetailedpage로 이동하는 코드
            val intent = Intent(this, AdoptionDetailedpage::class.java)
            startActivity(intent)
        }

        // 액션바 뒤로가기
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // 액션바 글자 색 변경
        val spannableString = SpannableString("우리의 동행")
        spannableString.setSpan(ForegroundColorSpan(ContextCompat.getColor(this, R.color.black)),
        0, spannableString.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        supportActionBar?.title = spannableString
    }

        // 액션바 뒤로가기 기능 구현
        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            when (item?.itemId) {
                android.R.id.home -> {
                    finish()
                    return true
                }
                else -> {
                    return super.onOptionsItemSelected(item)
                }
            }
        }

}