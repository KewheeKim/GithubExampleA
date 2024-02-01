package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class AccompanyBeforeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accompanybefore)

        getSupportActionBar()?.setTitle("나의 동행")

        var addBtn = findViewById<ImageButton>(R.id.addBtn)

        // 다음 화면으로 전환
        addBtn.setOnClickListener({
            val intent = Intent(this, ApplyPage1Activity::class.java)
            startActivity(intent)
        })
    }
}