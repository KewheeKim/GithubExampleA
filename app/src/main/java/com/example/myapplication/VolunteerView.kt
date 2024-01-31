package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class VolunteerView : AppCompatActivity() {
    lateinit var btnAdoptionReview: Button
    lateinit var btnWriteReview: Button
    lateinit var btn_VolReview: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_volunteer_view)

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

    }
}




