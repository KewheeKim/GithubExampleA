package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ApplyPage1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_applypage1)

        getSupportActionBar()?.setTitle("해외 입양 이동 봉사 신청")

        var nextBtn = findViewById<Button>(R.id.nextBtn)

        // 다음 화면으로 전환
        nextBtn.setOnClickListener({
            val intent = Intent(this, ApplyPage2Activity::class.java)
            startActivity(intent)
        })
    }
}