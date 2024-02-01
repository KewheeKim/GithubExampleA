package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ApplyPage5Activity : AppCompatActivity() {
//        lateinit var sqlDB: SQLiteDatabase
//        lateinit var myHelper: myDBHelper

    lateinit var ap5DBManager: Ap5DBManager
    lateinit var sqlitedb: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_applypage5)

        //액션바 이름 변경
        getSupportActionBar()?.setTitle("해외 입양 이동 봉사 신청")

        var nextBtn = findViewById<Button>(R.id.nextBtn)
        var edtFName = findViewById<EditText>(R.id.edtFName)
        var edtRNum = findViewById<EditText>(R.id.edtRNum)
        var rGroup = findViewById<RadioGroup>(R.id.rGroup)

        // 라디오 버튼이 눌릴 때마다 selectedRBtn의 값이 바뀜
        rGroup.setOnCheckedChangeListener { group, checkedId ->
            val selectedRBtn = findViewById<RadioButton>(checkedId)

            // 다음 화면으로 전환, 데베에 저장
            nextBtn.setOnClickListener({
                val valueFName = edtFName.text.toString()
                val valueRNum = edtRNum.text.toString()
                val vlaueAirLine = selectedRBtn.text.toString()

//                myHelper = myDBHelper(this)
//
//                sqlDB = myHelper.writableDatabase
//                sqlDB.execSQL("INSERT INTO ap5TBL VALUES ('$vlaueAirLine','$valueFName', '$valueRNum')")
//                sqlDB.close()

                ap5DBManager = Ap5DBManager(this, "ap5", null, 1)

                sqlitedb = ap5DBManager.writableDatabase
                sqlitedb.execSQL("INSERT INTO ap5 VALUES ('$vlaueAirLine','$valueFName', '$valueRNum')")
                sqlitedb.close()

                val intent = Intent(this, ApplyPage6Activity::class.java)
                startActivity(intent)
            })
        }

    }
    /*
    inner class myDBHelper(context: Context) : SQLiteOpenHelper (context, "ap5DB", null, 1) {
        override fun onCreate(db: SQLiteDatabase?) {
            db!!.execSQL("CREATE TABLE ap5TBL (airLine text, FName text, RNum text);")
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db!!.execSQL("DROP TABLE IF EXISTS ap5DB")
            onCreate(db)
        }
    }
    */


}