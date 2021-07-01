package com.ak_applications.astrosolution

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

class splash_screen: AppCompatActivity()
{
    private lateinit var  SplashHandler: Handler
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.splash_screen_activity)

        SplashHandler = Handler(Looper.getMainLooper())
        SplashHandler.postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
        },3000)
    }

}