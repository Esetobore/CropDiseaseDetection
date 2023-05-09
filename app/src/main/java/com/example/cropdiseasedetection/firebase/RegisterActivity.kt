package com.example.cropdiseasedetection.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.cropdiseasedetection.MainActivity
import com.example.cropdiseasedetection.R
import com.example.cropdiseasedetection.Users
import com.example.cropdiseasedetection.utils.Constants.Companion.PASSMIN
import com.example.cropdiseasedetection.utils.Constants.Companion.auth
import com.example.cropdiseasedetection.utils.Constants.Companion.databaseReference
import com.example.cropdiseasedetection.utils.Constants.Utils.showToast
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        register_btn.setOnClickListener {
            registerUser()
        }
        signIn_tv.setOnClickListener {
            onBackPressed()
        }
    }
    private fun registerUser(){
         val firstname = firstname_et_register.text.toString()
         val lastname = lastname_et_register.text.toString()
         val email = email_editText_register.text.toString()
         val password = register_pass.text.toString()
        progressBar_register.visibility = View.VISIBLE
        if (email.isEmpty()){
            val m1 = "Please Enter Email"
            toast_register.text = m1
        }
        if (password.isEmpty()){
            val m2 = "Please Enter Password"
            toast_register.text = m2
        }
        if (firstname.isEmpty()){
            val m3 = "Please Enter Firstname"
            toast_register.text = m3
        }
        if(lastname.isEmpty()){
            val m4 = "Please Enter lastname"
            toast_register.text = m4
        }
        if (password > PASSMIN.toString()){
            val m5 = "Password is Too Short"
            toast_register.text = m5
        }
        if(email.isEmpty() && password.isEmpty() && firstname.isEmpty() && lastname.isEmpty()){
            val m6 = "Fill in the empty space "
            toast_register.text = m6
        }

        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener {task->
                if (task.isSuccessful){
                    progressBar_register.visibility = View.GONE
                    val user = Users(firstname,lastname,email)
                    databaseReference.child(lastname).setValue(user)
                    showToast(this, "Registration Successful")
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
                else {
                    showToast(this, "Error")
                }
            }.addOnFailureListener {error->
                progressBar_register.visibility = View.GONE
                showToast(this, "Error")
                Log.i("FIREBASE", "error:$error")
            }
    }


}