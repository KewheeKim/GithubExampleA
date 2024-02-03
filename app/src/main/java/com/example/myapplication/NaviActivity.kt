package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityNaviBinding


class NaviActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNaviBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNaviBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceActivity(HomeActivity::class.java)

        binding.bottomNavigationView.itemIconTintList = null // 아이콘 색 안변함 이슈 해결

        binding.bottomNavigationView.setOnItemSelectedListener {

            when(it.itemId) {

                R.id.action_home -> replaceActivity(HomeActivity::class.java)
                R.id.action_record -> replaceActivity(AccompanyBeforeActivity::class.java)
                R.id.action_board -> replaceActivity(VolunteerView::class.java)
                R.id.action_mypage -> replaceActivity(myPage::class.java)

                else -> {

                }

            }
            true
        }


    }
    
    private fun replaceActivity(activityClass: Class <*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
        finish()
    }
}