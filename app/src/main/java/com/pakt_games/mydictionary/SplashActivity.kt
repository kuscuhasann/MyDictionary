package com.pakt_games.mydictionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pakt_games.mydictionary.util.startActivity
import java.util.*

class SplashActivity : AppCompatActivity() {

    private val DELAY: Long=3*1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Timer Thread
        Timer().schedule(object : TimerTask() {
            override fun run() {
                startActivity<HomeActivity>()
                //destroyed splash activitiy
                finish()
            }
        }, DELAY)
    }
}