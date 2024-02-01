package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem

class myPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)

        // 액션바 이름 변경
        getSupportActionBar()?.setTitle("마이페이지");
        // 액션바 뒤로가기
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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