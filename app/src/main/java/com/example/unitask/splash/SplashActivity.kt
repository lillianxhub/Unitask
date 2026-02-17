package com.example.unitask.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.unitask.R
import com.example.unitask.auth.WelcomeActivity
import com.example.unitask.home.HomeActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        
        val mainView = findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.main_splash)
        ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nextScreen = intent.getStringExtra("NEXT_SCREEN")

        if (nextScreen != null) {
            startLoadingTask(nextScreen)
        } else {
            startLoadingTask("WELCOME")
        }
    }

    private fun startLoadingTask(target: String) {
        lifecycleScope.launch {
            delay(1500) 

            val intent = when (target) {
                "HOME" -> Intent(this@SplashActivity, HomeActivity::class.java)
                else -> Intent(this@SplashActivity, WelcomeActivity::class.java)
            }
            startActivity(intent)
            finish()
        }
    }
}