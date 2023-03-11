package com.example.cropdiseasedetection

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        signup_tv.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }
        reset_txt.setOnClickListener {
            startActivity(Intent(this, ResetPassword::class.java))
        }
    }
}