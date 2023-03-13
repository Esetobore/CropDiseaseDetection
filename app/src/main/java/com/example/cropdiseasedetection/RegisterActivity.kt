package com.example.cropdiseasedetection

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.cropdiseasedetection.utils.Constants.Companion.auth
import com.example.cropdiseasedetection.utils.Constants.Companion.databaseReference
import com.example.cropdiseasedetection.utils.Constants.Companion.passMin
import com.example.cropdiseasedetection.utils.Constants.Utils.showToast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    private val firstname = firstname_et_register.text.toString()
    private val lastname = lastname_et_register.text.toString()
    private val email = email_editText_register.text.toString()
    private val password = register_pass.text.toString()
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

        progressBar_register.visibility = View.VISIBLE
        if (email.isEmpty()){

        }
        if (password.isEmpty()){

        }
        if (firstname.isEmpty()){

        }
        if(lastname.isEmpty()){

        }
        if (password <= passMin.toString()){

        }

        auth.createUserWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                progressBar_register.visibility = View.GONE
                uploadProfile()
                showToast(this, "Registration Successful")
                startActivity(Intent(this, MainActivity::class.java))
                finish()

            }.addOnFailureListener {
                progressBar_register.visibility = View.GONE
                showToast(this, "Error: $it")
            }
    }

    private fun uploadProfile(){
        val user = Users(firstname,lastname,email)
        databaseReference.child(lastname).setValue(user)
    }


}