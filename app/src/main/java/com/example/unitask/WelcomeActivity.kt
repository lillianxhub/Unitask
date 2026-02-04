package com.example.unitask

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
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
            showLoginBottomSheet()
        }

        // เมื่อกดปุ่ม Create Account ให้แสดง Popup Register
        btnSignUp.setOnClickListener {
            showRegisterBottomSheet()
        }
    }

    private fun showLoginBottomSheet() {
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(R.layout.layout_bottom_sheet_login)

        val btnSubmit = dialog.findViewById<Button>(R.id.btnlogin)
        btnSubmit?.setOnClickListener {
            // โค้ดสำหรับเช็ค Login
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun showRegisterBottomSheet() {
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(R.layout.layout_bottom_sheet_register)

        val btnRegister = dialog.findViewById<Button>(R.id.btnRegisterSubmit)
        btnRegister?.setOnClickListener {
            // โค้ดสำหรับลงทะเบียน
            dialog.dismiss()
        }
        dialog.show()
    }
}