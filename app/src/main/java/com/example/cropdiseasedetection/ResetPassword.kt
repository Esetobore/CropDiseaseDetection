package com.example.cropdiseasedetection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_reset_password.*

class ResetPassword : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        auth = FirebaseAuth.getInstance()
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