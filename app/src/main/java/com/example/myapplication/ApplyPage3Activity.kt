package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class ApplyPage3Activity : AppCompatActivity() {

    lateinit var myHelper: myDBHelper
    lateinit var edtName: EditText
    lateinit var edtNumber: EditText
    lateinit var edtEmail1: EditText
    lateinit var edtEmail2: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_applypage3)

        // 액션바 제목, 글자 색 변경
        val spannableString = SpannableString("해외 입양 이동 봉사 신청")
        spannableString.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(this, R.color.black)),
            0, spannableString.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        supportActionBar?.title = spannableString

        edtName = findViewById<EditText>(R.id.edtName)
        edtNumber = findViewById<EditText>(R.id.edtNumber)
        edtEmail1 = findViewById<EditText>(R.id.edtEmail1)
        edtEmail2 = findViewById<EditText>(R.id.edtEmail2)

        myHelper = myDBHelper(this)


        // 다음 화면으로 전환
        var nextBtn = findViewById<Button>(R.id.nextBtn)

        nextBtn.setOnClickListener({

            //다음 화면으로 넘어감
            val intent = Intent(this, ApplyPage4Activity::class.java)
            startActivity(intent)
        })
    }

    inner class myDBHelper(context: Context) : SQLiteOpenHelper ( context, "applyDB", null, 1) {
        override fun onCreate(db: SQLiteDatabase?) {
            db!!.execSQL("CREATE TABLE applyTBL ( name char(20), number integer(10), email1 char(15), email2 char(15));")
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db!!.execSQL("DROP TABLE IF EXISTS applyTBL")
            onCreate(db)
        }
    }
}