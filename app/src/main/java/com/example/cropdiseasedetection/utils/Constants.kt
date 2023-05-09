package com.example.cropdiseasedetection.utils

import android.content.Context
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.StorageReference

class Constants {
    companion object{
        val auth : FirebaseAuth = FirebaseAuth.getInstance()
        val databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        var uid : String = ""
        const val PASSMIN = 9
        const val PREF_PASSWORD_ATTEMPTS = "pref_password_attempts"
        val MY_API_KEY = "AIzaSyBgefUB3ma_DGEHzSGkFnKUBdEHOM2HmY8"
        const val REQUESTCODE = 101
    }
    object Utils {
        fun showToast(mContext: Context?, message: String?) {
            Toast.makeText(mContext, message, Toast.LENGTH_LONG).show()
        }
        fun showToastShort(mContext: Context?, message: String?) {
            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
        }
    }
}