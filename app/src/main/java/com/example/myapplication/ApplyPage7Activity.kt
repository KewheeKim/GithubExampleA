package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity

class ApplyPage7Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_applypage7)

        //액션바 이름 변경
        getSupportActionBar()?.setTitle("해외 입양 이동 봉사 신청")

        var nextBtn = findViewById<Button>(R.id.nextBtn)
        var btnClose = findViewById<ImageButton>(R.id.btnClose)
        var lastPage = findViewById<RelativeLayout>(R.id.lastPage)

        // 신청하기 버튼을 누르면 팝업이 뜨고 3초뒤에 AccompanyAfterActivity 화면으로 전환
        nextBtn.setOnClickListener({
            lastPage.visibility = View.VISIBLE
            Handler().postDelayed({
                val intent = Intent(this, AccompanyAfterActivity::class.java)
                startActivity(intent)
            }, 3000)
        })

        // 닫기 버튼을 누르면 이전 화면으로 전환
        btnClose.setOnClickListener({
            val intent = Intent(this, ApplyPage6Activity::class.java)
            startActivity(intent)
        })


    }


}