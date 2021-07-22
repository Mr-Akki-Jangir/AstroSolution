package com.ak_applications.astrosolution


import android.content.Intent
import android.media.MediaPlayer.OnPreparedListener
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity


class splash_screen: AppCompatActivity()
{
    private lateinit var  SplashHandler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen_activity)

        val path =
            "android.resource://" + packageName + "/" + R.raw.splash_vbg

        val VideoBg: VideoView  = findViewById(R.id.splash_bg)

        val uri: Uri = Uri.parse(path)
        VideoBg.setVideoURI(uri)

        VideoBg.setOnPreparedListener(OnPreparedListener { mp ->
            mp.start()
        })
        VideoBg.setOnCompletionListener {
            it.start()
        }


        SplashHandler = Handler()
        SplashHandler.postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
        },5000)
    }

}