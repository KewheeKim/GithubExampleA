package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class ApplyPage7Activity : AppCompatActivity() {

    companion object {
        var VISIBILITY: Boolean = false
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_applypage7)



        // 액션바 제목, 글자 색 변경
        val spannableString = SpannableString("해외 입양 이동 봉사 신청")
        spannableString.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(this, R.color.black)),
            0, spannableString.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        supportActionBar?.title = spannableString

        var nextBtn = findViewById<Button>(R.id.nextBtn)
        var btnClose = findViewById<ImageButton>(R.id.btnClose)
        var lastPage = findViewById<RelativeLayout>(R.id.lastPage)

        // 신청하기 버튼을 누르면 팝업이 뜨고 3초뒤에 AccompanyAfterActivity 화면으로 전환
        nextBtn.setOnClickListener({
            lastPage.visibility = View.VISIBLE
            Handler().postDelayed({

                // AccompanyBefore 액티비티에 정보를 넘김
                val accompanyBefore_intent = Intent(this, AccompanyBeforeActivity::class.java)

                // 전체 클래스에서 접근 가능한 변수 VISIBILITY를 true로 바꿈
                VISIBILITY = true

                // acccompanyBefore 화면에 afterApply_visibility에 true를 저장하여 AccompanyBeforeActivity에 넘김
                accompanyBefore_intent.putExtra("afterApply_visibility", VISIBILITY)

                // accompanyBefore 화면으로 전환
                startActivity(accompanyBefore_intent)
            }, 3000)
        })

        // 닫기 버튼을 누르면 이전 화면으로 전환
        btnClose.setOnClickListener({
            val intent = Intent(this, ApplyPage6Activity::class.java)
            startActivity(intent)
        })

    }


}