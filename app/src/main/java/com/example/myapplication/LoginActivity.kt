package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var loginBinding: ActivityLoginBinding
    var DB:DBHelper ?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(loginBinding.root)
        supportActionBar?.hide() // 로그인 화면에서만 액션바 가리기
        DB = DBHelper(this)

        // 로그인 버튼 눌렀을 떄
        loginBinding.btnLogin!!.setOnClickListener {
            val user = loginBinding.enterUsername!!.text.toString()
            val pass = loginBinding.enterPassword!!.text.toString()

            if (user == "" || pass == "") {  // 아이디 or 비밀번호가 입력되지 않았을 때
                Toast.makeText(
                    this@LoginActivity,
                    "회원 정보를 모두 입력해주세요.",
                    Toast.LENGTH_SHORT
                ).show()
            } else { // 아이디, 비밀번호가 모두 입력되었을 때
                val checkUserpass = DB!!.checkUserpass(user, pass)
                if (checkUserpass == true) { // 아이디, 비밀번호가 DB에 존재할 때
                    Toast.makeText(this@LoginActivity,
                        "로그인 되었습니다.",
                        Toast.LENGTH_SHORT).show()
                    val intent = Intent(applicationContext, NaviActivity::class.java)
                    startActivity(intent)
                }
                else { // 아이디, 비밀번호 DB에 존재하지 않을 때
                    Toast.makeText(this@LoginActivity,
                        "회원 정보가 존재하지 않습니다.",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
        // 회원가입 버튼을 눌렀을 때 회원가입 화면으로 이동
        loginBinding.btnRegister.setOnClickListener {
            val loginIntent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(loginIntent)
        }

    }
}