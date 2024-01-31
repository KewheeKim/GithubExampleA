package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class VolunteerDetailedpage: AppCompatActivity() {
    lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voluteer_detailedpage)

        btnBack.setOnClickListener {
            // 뒤로 가기 버튼 클릭 시 VolunteerView로 이동하는 코드
            val intent = Intent(this, VolunteerView::class.java)
            startActivity(intent)
        }

    }
}
