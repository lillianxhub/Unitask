package com.example.unitask.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.unitask.R
import com.example.unitask.splash.SplashActivity

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val btnLogin = findViewById<Button>(R.id.btnlogin)
        val btnSignUp = findViewById<Button>(R.id.btnSignUp)

        btnLogin.setOnClickListener {
            val dialog = LoginBottomSheetDialog()
            dialog.show(supportFragmentManager, "LoginBottomSheet")
        }

        btnSignUp.setOnClickListener {
            val dialog = RegisterBottomSheetDialog()
            dialog.show(supportFragmentManager, "RegisterBottomSheet")
        }
    }
}