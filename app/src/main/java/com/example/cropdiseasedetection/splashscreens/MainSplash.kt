package com.example.cropdiseasedetection.splashscreens

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.cropdiseasedetection.firebaseservices.LoginActivity
import com.example.cropdiseasedetection.R
import kotlinx.android.synthetic.main.activity_main_splash.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainSplash : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_splash)

        lottie_plant.animate().translationY(0F).setDuration(3000).startDelay
        logo_splash.animate().translationY(0F).setDuration(2000).startDelay

        lifecycleScope.launch(Dispatchers.Default){
            intent()
        }


    }

    private suspend fun intent(){
        delay(4000L)
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }


}
