package com.example.cropdiseasedetection.utils

import android.content.Context
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class Constants {
    companion object{
        val auth : FirebaseAuth = FirebaseAuth.getInstance()
        val databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        val PASSMIN = 9
        const val PREF_PASSWORD_ATTEMPTS = "pref_password_attempts"
    }
    object Utils {
        fun showToast(mContext: Context?, message: String?) {
            Toast.makeText(mContext, message, Toast.LENGTH_LONG).show()
        }
    }
}