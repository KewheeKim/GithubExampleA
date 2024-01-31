package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class AdoptionDetailedpage: AppCompatActivity() {
    lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adoption_detailedpage)

        btnBack.setOnClickListener {
            // 뒤로 가기 버튼 클릭 시 AdoptionView로 이동하는 코드
            val intent = Intent(this, AdoptionView::class.java)
            startActivity(intent)
        }

    }
}