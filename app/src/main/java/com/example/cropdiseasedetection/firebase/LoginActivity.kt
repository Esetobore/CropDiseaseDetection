package com.example.cropdiseasedetection.firebase
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.cropdiseasedetection.MainActivity
import com.example.cropdiseasedetection.R
import com.example.cropdiseasedetection.utils.Constants.Companion.PREF_PASSWORD_ATTEMPTS
import com.example.cropdiseasedetection.utils.Constants.Companion.auth
import com.example.cropdiseasedetection.utils.Constants.Utils.showToast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_btn.setOnClickListener {
           loginUser()
        }
        signup_tv.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        reset_txt.setOnClickListener {
            startActivity(Intent(this, ResetPassword::class.java))
        }

    }
    private fun loginUser(){
        val email = email_editText_login.text.toString().trim()
        val password = password_edt_login.text.toString().trim()
        progressBar_login.visibility = View.VISIBLE
        // get the shared preference instance
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        // retrieve the current password attempt count from the shared preference
        var passwordAttempts = sharedPref.getInt(PREF_PASSWORD_ATTEMPTS, 0)
        // increment the password attempt count and save it to the shared preference
        passwordAttempts++
        with(sharedPref.edit()) {
            putInt(PREF_PASSWORD_ATTEMPTS, passwordAttempts)
            apply() }
        // check if the password attempt count exceeds the maximum limit
        val MAX_PASSWORD_ATTEMPTS = 20

        if (email.isEmpty()){
            Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show()
            val m1 = "Please Enter email"
            toast_login.text = m1
            progressBar_login.visibility = View.GONE
        }
        if (password.isEmpty()){
            val m2 = "Wrong password"
            toast_login.text = m2
            progressBar_login.visibility = View.GONE

        }
        if (password.isEmpty() && email.isEmpty()){
            val m3 = "Please Fill in the Empty Spaces"
            toast_login.text = m3
            progressBar_login.visibility = View.GONE
        }
        if (passwordAttempts >= MAX_PASSWORD_ATTEMPTS) {
            val m4 = "Check if Info Entered is Correct"
            toast_login.text = m4
            reset_txt.visibility = View.VISIBLE
            progressBar_login.visibility = View.GONE
            TODO("Find a way to reset the counter for the password attempt after they leave this activity")
        }
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener { task->
                if (task.isSuccessful){
                    Log.i("FIREBASE", "Login Successful")
                    startActivity(Intent(this, MainActivity::class.java))
                    progressBar_login.visibility = View.GONE
                }
            }.addOnFailureListener {error->
                progressBar_login.visibility = View.GONE
                showToast(this,"Failed to Login")
                Log.i("FIREBASE", "Error:$error")
            }
    }
}