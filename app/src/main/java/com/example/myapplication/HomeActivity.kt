package com.example.myapplication

import android.animation.LayoutTransition
import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.viewpager2.widget.ViewPager2

class HomeActivity : AppCompatActivity() {
    private lateinit var detailsText: TextView
    private lateinit var layout: LinearLayout
    private lateinit var expand: CardView
    private lateinit var plus:ImageButton
    private lateinit var sqlitedb: SQLiteDatabase
    private lateinit var afterApplyTicket: RelativeLayout
    private lateinit var tvDestination: TextView
    private lateinit var tvDate1: TextView
    private lateinit var tvTime1: TextView
    private lateinit var tvTime2: TextView
    private lateinit var btnToAccompanyBefore: Button
    private lateinit var text_entertitle: TextView
    private lateinit var tvAfterApply:TextView
    private lateinit var tvDday: TextView

    private lateinit var ap2DBManager: Ap2DBManager
    private lateinit var ap4DBManager: Ap4DBManager
    private lateinit var cursor: Cursor

    @SuppressLint("MissingInflatedId", "Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        detailsText = findViewById(R.id.detailsText)
        layout = findViewById(R.id.layouts)
        expand = findViewById(R.id.expandable)
        plus = findViewById(R.id.plus)
        afterApplyTicket = findViewById(R.id.afterApplyTicket)
        tvDestination = findViewById(R.id.tvDestination)
        tvDate1 = findViewById(R.id.tvDate1)
        tvTime1 = findViewById(R.id.tvTime1)
        tvTime2 = findViewById(R.id.tvTime2)
        btnToAccompanyBefore = findViewById(R.id.btnToAccompanyBefore)
        text_entertitle = findViewById(R.id.text_entertitle)
        tvAfterApply = findViewById(R.id.tvAfterApply)
        tvDday = findViewById(R.id.tvDday)


        // 강아지 뷰 페이저
        val viewPager2_1: ViewPager2 = findViewById(R.id.dogViewPager)
        val dogs = listOf(R.drawable.main_dog_1, R.drawable.main_dog_2, R.drawable.main_dog_3,
            R.drawable.main_dog_4, R.drawable.main_dog_5)

        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin)
        val pagerWidth = resources.getDimensionPixelOffset(R.dimen.pageWidth)
        val screenWidth = resources.displayMetrics.widthPixels
        val offsetPx = screenWidth - pageMarginPx - pagerWidth

        viewPager2_1.setPageTransformer { page, position ->
            page.translationX = position * -offsetPx
        }

        viewPager2_1.adapter = ViewPagerAdapter(dogs)
        viewPager2_1.offscreenPageLimit = 3 // 옆에 미리 로드 할 페이지의 개수
        viewPager2_1.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        // 게시글 뷰 페이저
        val viewPager2_2: ViewPager2 = findViewById(R.id.boardViewPager)
        val images = listOf(R.drawable.main_board_1, R.drawable.main_board_2, R.drawable.main_board_3)

        viewPager2_2.adapter = ViewPagerAdapter(images)


        ap2DBManager = Ap2DBManager(this, "ap2", null, 1)
        ap4DBManager = Ap4DBManager(this,"ap4", null, 1)

        // db ap2상의 마지막 행의 정보가 화면에 뜸
        sqlitedb = ap2DBManager.readableDatabase
        cursor = sqlitedb.rawQuery("SELECT * FROM ap2", null)

        if(cursor.moveToLast()) {
            tvDestination.text = cursor.getString(cursor.getColumnIndex("destination")).toString()
        }


        // db ap4상의 마지막 행의 정보가 화면에 뜸
        sqlitedb = ap4DBManager.readableDatabase
        cursor = sqlitedb.rawQuery("SELECT * FROM ap4", null)

        if(cursor.moveToLast()) {
            tvDate1.text = cursor.getString(cursor.getColumnIndex("Date1")).toString()
            tvTime1.text = cursor.getString(cursor.getColumnIndex("Time1")).toString()
            tvTime2.text = cursor.getString(cursor.getColumnIndex("Time2")).toString()
        }

        // 신청 완료 후 정보 티켓창이 뜸
        if(ApplyPage7Activity.VISIBILITY == true) {

            // 전역 변수 strDday로 tvDay의 텍스트를 설정함
            tvDday.text = ApplyPage4Activity.strDday

            // 디데이 문구 출력
            tvAfterApply.visibility = View.VISIBLE
            tvDday.visibility = View.VISIBLE
            text_entertitle.visibility = View.INVISIBLE

            // afterApplyTicket 레이아웃을 보이게 만듦
            afterApplyTicket.visibility = View.VISIBLE

            // 티켓을 누르면 나의 동행 페이지로 전환
            btnToAccompanyBefore.setOnClickListener {
                val AccompanyBefore_intent = Intent(this, AccompanyBeforeActivity::class.java)
                startActivity(AccompanyBefore_intent)
            }

        } else {
            afterApplyTicket.visibility = View.GONE
        }

        cursor.close()
        sqlitedb.close()
        ap2DBManager.close()
        ap4DBManager.close()


        layout.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        expand.setOnClickListener {
            val v = if (detailsText.visibility == View.GONE) View.VISIBLE else View.GONE
            detailsText.visibility = v
        }

        // 다음 화면(신청서)으로 넘어감
        plus.setOnClickListener({

            val applyPage1_intent = Intent(this, ApplyPage1Activity::class.java)
            startActivity(applyPage1_intent)
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