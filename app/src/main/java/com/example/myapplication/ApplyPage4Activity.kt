package com.example.myapplication

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar
import java.util.Timer

class ApplyPage4Activity : AppCompatActivity() {

    lateinit var myHelper : myDBHelper
    lateinit var sqlDB: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_applypage4)

        //액션바 이름 변경
        getSupportActionBar()?.setTitle("해외 입양 이동 봉사 신청")

        var nextBtn = findViewById<Button>(R.id.nextBtn)
        var arrowDownBtn1 = findViewById<ImageButton>(R.id.arrowDownBtn1)
        var arrowDownBtn2 = findViewById<ImageButton>(R.id.arrowDownBtn2)
        var arrowDownBtn3 = findViewById<ImageButton>(R.id.arrowDownBtn3)
        var arrowDownBtn4 = findViewById<ImageButton>(R.id.arrowDownBtn4)
        var edtDate1 = findViewById<TextView>(R.id.edtDate1)
        var edtDate2 = findViewById<TextView>(R.id.edtDate2)
        var edtTime1 = findViewById<TextView>(R.id.edtTime1)
        var edtTime2 = findViewById<TextView>(R.id.edtTime2)


        arrowDownBtn1.setOnClickListener {
            // 현재 날짜 가져오기
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

            // DatePickerDialog 표시
            val datePickerDialog = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view: DatePicker, selectedYear: Int, selectedMonth: Int, dayOfMonth: Int ->
                    // 날짜가 설정되면 실행되는 콜백 함수
                    val selectedDate = "       $selectedYear/${selectedMonth + 1}/$dayOfMonth"
                    edtDate1.text = selectedDate // 버튼 텍스트를 선택한 날짜로 설정
                },
                year,
                month,
                dayOfMonth
            )
            datePickerDialog.show()
        }

        arrowDownBtn2.setOnClickListener {
            // 현재 날짜 가져오기
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

            // DatePickerDialog 표시
            val datePickerDialog = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view: DatePicker, selectedYear: Int, selectedMonth: Int, dayOfMonth: Int ->
                    // 날짜가 설정되면 실행되는 콜백 함수
                    val selectedDate = "       $selectedYear/${selectedMonth + 1}/$dayOfMonth"
                    edtDate2.text = selectedDate // 버튼 텍스트를 선택한 날짜로 설정
                },
                year,
                month,
                dayOfMonth
            )
            datePickerDialog.show()
        }

        arrowDownBtn3.setOnClickListener {
            // 현재 시간 가져오기
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR)
            val minute = calendar.get(Calendar.MINUTE)

            // DatePickerDialog 표시
            val timePickerDialog = TimePickerDialog (
                this,
                android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                TimePickerDialog.OnTimeSetListener { view: TimePicker, selectedHour: Int, minuteOfDay: Int ->
                    // 시간이 설정되면 실행되는 콜백 함수
                    val selectedTime = "       $selectedHour:$minuteOfDay"
                    edtTime1.text = selectedTime // 버튼 텍스트를 선택한 날짜로 설정
                },
                hour,
                minute,
                true
            )
            //timePickerDialog.getWindow()?.setBackgroundDrawableResource(android.R.color.transparent)
            timePickerDialog.show()
        }

        arrowDownBtn4.setOnClickListener {
            // 현재 시간 가져오기
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR)
            val minute = calendar.get(Calendar.MINUTE)

            // DatePickerDialog 표시
            val timePickerDialog = TimePickerDialog (
                this,
                android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                TimePickerDialog.OnTimeSetListener { view: TimePicker, selectedHour: Int, minuteOfDay: Int ->
                    // 시간이 설정되면 실행되는 콜백 함수
                    val selectedTime = "       $selectedHour:$minuteOfDay"
                    edtTime2.text = selectedTime // 버튼 텍스트를 선택한 날짜로 설정
                },
                hour,
                minute,
                true
            )
            timePickerDialog.show()
        }

        myHelper = myDBHelper(this)

        // 다음 화면으로 전환, 데베에 저장
        nextBtn.setOnClickListener({

            val valueDate1 = edtDate1.text.toString()
            val valueDate2 = edtDate2.text.toString()
            val valueTime1 = edtTime1.text.toString()
            val valueTime2 = edtTime2.text.toString()

            sqlDB = myHelper.writableDatabase
            sqlDB.execSQL("INSERT INTO ap4TBL VALUES ('$valueDate1', '$valueDate2', '$valueTime1', '$valueTime2')")
            sqlDB.close()

            val intent = Intent(this, ApplyPage5Activity::class.java)
            startActivity(intent)
        })
    }

    inner class myDBHelper(context: Context) : SQLiteOpenHelper(context, "ap4DB", null, 1) {
        override fun onCreate(db: SQLiteDatabase?) {
            db!!.execSQL("CREATE TABLE ap4TBL (Date1 text, Date2 text, Time1 text, Time2 text);")
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db!!.execSQL("DROP TABLE IF EXISTS ap4DB")
            onCreate(db)
        }
    }


}