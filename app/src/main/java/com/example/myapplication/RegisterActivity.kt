package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.example.myapplication.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    lateinit var registerBinding: ActivityRegisterBinding
    var DB:DBHelper ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(registerBinding.root)
        DB = DBHelper(this)

        registerBinding.btnToRegister.setOnClickListener {
            val user = registerBinding.createUsername.text.toString()
            val pass = registerBinding.createPassword.text.toString()
            val repass = registerBinding.checkPassword.text.toString()

            // 아이디, 비밀번호, 재확인 비밀번호 중 하나라도 입력이 되지 않았을 때
            if (user == "" || pass == "" || repass == "") Toast.makeText(
                this@RegisterActivity,
                "회원 정보를 전부 입력해주세요.",
                Toast.LENGTH_SHORT
            ).show() else {
                if (pass == repass) { // 비밀번호, 재확인 비밀번호가 모두 동일할 때
                    val checkUsername = DB!!.checkUsername(user)
                    if (checkUsername == false) {   // 아이디가 DB에 존재하지 않을 때
                        val insert = DB!!.insertData(user, pass)
                        if(insert == true) {
                            Toast.makeText(
                                this@RegisterActivity,
                                "가입되었습니다.",
                                Toast.LENGTH_SHORT
                            ).show()
                            val intent = Intent(applicationContext, HomeActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(
                                this@RegisterActivity,
                                "비밀번호가 일치하지 않습니다.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else { // 아이디가 이미 DB에 존재할 때
                        Toast.makeText(
                            this@RegisterActivity,
                            "이미 가입된 회원입니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else { // 비밀번호, 재확인 비밀번호가 서로 다를 때
                    Toast.makeText(this@RegisterActivity, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

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