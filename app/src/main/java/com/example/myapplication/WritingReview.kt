package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.view.MenuItem
import androidx.core.content.ContextCompat
import android.text.Spannable
import android.text.style.ForegroundColorSpan
import android.widget.Button

class WritingReview : AppCompatActivity() {
    private lateinit var btnFinish: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_writing_review)

        btnFinish = findViewById(R.id.btnFinish)

        btnFinish.setOnClickListener {
            // 완료 버튼 클릭 시 VolunteerView로 이동하는 코드
            val intent = Intent(this, VolunteerView::class.java)
            startActivity(intent)
        }

        // 액션바 뒤로가기
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // 액션바 글자 색 변경
        val spannableString = SpannableString("봉사 후기 작성")
        spannableString.setSpan(
        ForegroundColorSpan(ContextCompat.getColor(this, R.color.black)),
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