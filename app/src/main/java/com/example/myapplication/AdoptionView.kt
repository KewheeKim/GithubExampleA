package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class AdoptionView : AppCompatActivity() {
    lateinit var btnVolReview: Button
    lateinit var AdoptReview: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adoption_view)

        btnVolReview.setOnClickListener {
            // 봉사 후기 버튼 클릭 시 VoluteerView로 이동하는 코드
            val intent = Intent(this, VolunteerView::class.java)
            startActivity(intent)
        }

        AdoptReview.setOnClickListener {
            // 작성된 입양 후기글 제목 클릭 시 AdoptionDetailedpage로 이동하는 코드
            val intent = Intent(this, AdoptionDetailedpage::class.java)
            startActivity(intent)
        }

    }
}