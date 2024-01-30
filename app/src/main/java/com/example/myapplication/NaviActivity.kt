package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.ActivityNaviBinding


class NaviActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNaviBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNaviBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())

        binding.bottomNavigationView.itemIconTintList = null // 아이콘 색 안변함 이슈 해결

        binding.bottomNavigationView.setOnItemSelectedListener {

            when(it.itemId) {

                R.id.home -> replaceFragment(HomeFragment())
                R.id.record -> replaceFragment(RecordFragment())
                R.id.board -> replaceFragment(BoardFragment())
                R.id.mypage -> replaceFragment(MyPageFragment())

                else -> {

                }

            }
            true
        }


    }

    private fun replaceFragment(fragment: Fragment) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()

    }
}