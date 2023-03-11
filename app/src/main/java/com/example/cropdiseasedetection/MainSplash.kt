package com.example.cropdiseasedetection

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
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
