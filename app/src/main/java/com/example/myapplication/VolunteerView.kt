package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class VolunteerView : AppCompatActivity() {
    private lateinit var btnAdoptionReview: Button
    private lateinit var btnWriteReview: Button
    private lateinit var btn_VolReview: Button
    private lateinit var btnVolReview: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_volunteer_view)

        //버튼 초기화
        btnVolReview = findViewById(R.id.btnVolReview)
        btnAdoptionReview = findViewById(R.id.btnAdoptReview)
        btnWriteReview = findViewById(R.id.btnWriteReview)
        btn_VolReview = findViewById(R.id.btn_VolReview)

        btnAdoptionReview.setOnClickListener {
            // 입양 후기 버튼 클릭 시 AdoptionView로 이동하는 코드
            val intent = Intent(this, AdoptionView::class.java)
            startActivity(intent)
        }

        btnWriteReview.setOnClickListener {
            // 후기 작성 버튼 클릭 시 WritingReview로 이동하는 코드
            val intent = Intent(this, WritingReview::class.java)
            startActivity(intent)
        }

        btn_VolReview.setOnClickListener {
            // 작성된 봉사 후기 게시글 제목 클릭시 VoluteerDetailedpage로 이동하는 코드
            val intent = Intent(this, VolunteerDetailedpage::class.java)
            startActivity(intent)
        }

        // 액션바 뒤로가기
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // 액션바 글자 색 변경
        val spannableString = SpannableString("우리의 동행")
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






