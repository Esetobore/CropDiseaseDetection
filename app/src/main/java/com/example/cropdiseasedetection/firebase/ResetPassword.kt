package com.example.cropdiseasedetection.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.cropdiseasedetection.R
import com.example.cropdiseasedetection.utils.Constants.Companion.auth
import kotlinx.android.synthetic.main.activity_reset_password.*

class ResetPassword : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        login_tv_return.setOnClickListener {
            onBackPressed()
        }

        reset_btn.setOnClickListener {
        val emailReset = email_editText_reset.text.toString()
            auth.sendPasswordResetEmail(emailReset)
                .addOnSuccessListener {
                    Toast.makeText(this,"Password Reset Complete", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    val error = it.toString()
                    Toast.makeText(this, error, Toast.LENGTH_LONG).show()
                }
        }
    }
}