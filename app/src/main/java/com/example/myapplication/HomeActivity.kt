package com.example.myapplication

import android.animation.LayoutTransition
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView

class HomeActivity : AppCompatActivity() {
    private lateinit var detailsText: TextView
    private lateinit var layout: LinearLayout
    private lateinit var expand: CardView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        detailsText = findViewById(R.id.detailsText)
        layout = findViewById(R.id.layouts)
        expand = findViewById(R.id.expandable)

        layout.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        expand.setOnClickListener {
            val v = if (detailsText.visibility == View.GONE) View.VISIBLE else View.GONE
            detailsText.visibility = v
        }

        var plus = findViewById<ImageButton>(R.id.plus)

        plus.setOnClickListener({

            //다음 화면으로 넘어감
            val intent = Intent(this, ApplyPage1Activity::class.java)
            startActivity(intent)
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottom_navi, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId) {
            R.id.action_home -> { // 메인 홈 화면으로 이동
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                return true
            }

            R.id.action_record -> { // 신청 전 나의 동행으로 이동
                val intent = Intent(this, AccompanyBeforeActivity::class.java)
                startActivity(intent)
                return true
            }

            R.id.action_board -> { // 입양 후기 목록으로 이동
                val intent = Intent(this, AdoptionView::class.java)
                startActivity(intent)
                return true
            }

            R.id.action_mypage -> { // 마이페이지로 이동
                val intent = Intent(this, myPage::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}