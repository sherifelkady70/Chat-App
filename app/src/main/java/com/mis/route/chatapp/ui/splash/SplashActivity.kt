package com.mis.route.chatapp.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.mis.route.chatapp.ui.auth.AuthActivity
import com.mis.route.chatapp.R


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        navigateHome()
    }

    private fun navigateHome() {
        Handler(mainLooper).postDelayed({
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
        }, 2000)
    }
}