package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class AccompanyBeforeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_record_1)

        getSupportActionBar()?.setTitle("나의 동행")

        var addBtn = findViewById<ImageButton>(R.id.addBtn)

        // 추가한 내용========================================
        /*ap2DBManager = Ap2DBManager(this, "ap2", null, 1)
        var tvDestination = findViewById<TextView>(R.id.tvDestination)
        var cursor: Cursor
        cursor = sqlitedb.rawQuery("SELECT * FROM ap2", null)

        if(cursor.moveToLast()) {
            tvDestination.text = cursor.getString(cursor.getColumnIndex("destination")).toString()
        }
        else {
            tvDestination.text = "no destination"
        }
        cursor.close()
        sqlitedb.close()
        ap2DBManager.close()

*/
        //==================================================

        // 다음 화면으로 전환
        addBtn.setOnClickListener({
            val intent = Intent(this, ApplyPage1Activity::class.java)
            startActivity(intent)
        })
    }

    
}