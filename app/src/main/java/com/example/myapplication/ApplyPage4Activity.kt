package com.example.myapplication

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.util.Calendar
import java.util.Timer

class ApplyPage4Activity : AppCompatActivity() {


    // 모든 클래스에서 접근 가능한 strDday 설정
    companion object {
        var strDday : String = ""
    }

    lateinit var ap4DBManager: Ap4DBManager
    lateinit var sqlitedb: SQLiteDatabase

    fun getDday(dDay_year: Int, dDay_month: Int, dDay_day: Int) : Long {

        // ddayCalender와 todayCalender 선언
        var ddayCalendar: Calendar = Calendar.getInstance()
        var todayCalender: Calendar = Calendar.getInstance()

         // 매개변수로 받은 값으로 날짜 지정
        ddayCalendar.set(dDay_year, dDay_month, dDay_day)

        var ddayMilliseconds = ddayCalendar.timeInMillis
        var todayMilliseconds = todayCalender.timeInMillis

        // D-day를 구하기 위해 지정된 날짜와 오늘 날짜를 뺀 후 밀리초로 나누어 계산
        var Dday = (ddayMilliseconds - todayMilliseconds) / (24 * 60 * 60 * 1000)

        return Dday
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_applypage4)

        // 액션바 제목, 글자 색 변경
        val spannableString = SpannableString("해외 입양 이동 봉사 신청")
        spannableString.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(this, R.color.black)),
            0, spannableString.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        supportActionBar?.title = spannableString


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
                { view: DatePicker, selectedYear: Int, selectedMonth: Int, dayOfMonth: Int ->
                    // 날짜가 설정되면 실행되는 콜백 함수
                    val selectedDate = "${selectedYear}년 ${selectedMonth + 1}월 ${dayOfMonth}일"

                    // 버튼 텍스트를 선택한 날짜로 설정
                    edtDate1.text = selectedDate


                    // strDday에 선택된 날짜로부터 디데이 설정
                    strDday = "D-${getDday(selectedYear, selectedMonth, dayOfMonth)}"
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
                { view: DatePicker, selectedYear: Int, selectedMonth: Int, dayOfMonth: Int ->
                    // 날짜가 설정되면 실행되는 콜백 함수
                    val selectedDate = "${selectedYear}년 ${selectedMonth + 1}월 ${dayOfMonth}일"

                    // 버튼 텍스트를 선택한 날짜로 설정
                    edtDate2.text = selectedDate
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
                { view: TimePicker, selectedHour: Int, minuteOfDay: Int ->
                    // 시간이 설정되면 실행되는 콜백 함수
                    val selectedTime = "$selectedHour:$minuteOfDay"

                    // 버튼 텍스트를 선택한 날짜로 설정
                    edtTime1.text = selectedTime
                },
                hour,
                minute,
                true
            )

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
                { view: TimePicker, selectedHour: Int, minuteOfDay: Int ->
                    // 시간이 설정되면 실행되는 콜백 함수
                    val selectedTime = "$selectedHour:$minuteOfDay"

                    // 버튼 텍스트를 선택한 날짜로 설정
                    edtTime2.text = selectedTime
                },
                hour,
                minute,
                true
            )
            timePickerDialog.show()
        }

        ap4DBManager = Ap4DBManager(this, "ap4", null, 1)

        // 다음 화면으로 전환, 데베에 저장
        nextBtn.setOnClickListener({

            val valueDate1 = edtDate1.text.toString()
            val valueDate2 = edtDate2.text.toString()
            val valueTime1 = edtTime1.text.toString()
            val valueTime2 = edtTime2.text.toString()

            sqlitedb = ap4DBManager.writableDatabase
            sqlitedb.execSQL("INSERT INTO ap4 VALUES ('$valueDate1', '$valueDate2', '$valueTime1', '$valueTime2')")
            sqlitedb.close()

            // 다음 화면으로 전환
            val intent = Intent(this, ApplyPage5Activity::class.java)
            startActivity(intent)
        })
    }

}