package com.realcrap.bravo.ui.splash

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.realcrap.bravo.R

class Splash : AppCompatActivity() {

     lateinit var progress : LottieAnimationView
    lateinit var handler : Handler


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initViews()
        startHandler();

    }

    private fun startHandler() {
      
    }

    private fun initViews() {

        progress = findViewById(R.id.lav_actionBar)
        progress.playAnimation()
    }
}


