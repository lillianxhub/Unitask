package com.example.unitask

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.google.android.material.bottomsheet.BottomSheetDialog

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        // ผูกปุ่ม Login จากหน้า Welcome
        val btnLogin = findViewById<Button>(R.id.btnlogin)
        val btnSignUp = findViewById<Button>(R.id.btnSignUp)

        // เมื่อกดปุ่ม Login ให้แสดง Popup
        btnLogin.setOnClickListener {
            LoginBottomSheetDialog().show(supportFragmentManager, "LoginBottomSheetDialog")
        }

        // เมื่อกดปุ่ม Create Account ให้แสดง Popup Register
        btnSignUp.setOnClickListener {
            RegisterBottomSheetDialog().show(supportFragmentManager, "RegisterBottomSheetDialog")
        }
    }
}