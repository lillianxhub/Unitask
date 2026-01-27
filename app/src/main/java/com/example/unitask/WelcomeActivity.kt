package com.example.unitask

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomsheet.BottomSheetDialog

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        // ผูกปุ่ม Login จากหน้า Welcome
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        // เมื่อกดปุ่ม Login ให้แสดง Popup
        btnLogin.setOnClickListener {
            showLoginBottomSheet()
        }

        // ส่วนปุ่ม Sign Up (Regis) ก็ทำคล้ายๆ กันครับ
    }

    private fun showLoginBottomSheet() {
        // 1. สร้าง Dialog
        val dialog = BottomSheetDialog(this)

        // 2. ดึง Layout ที่เราเขียนไว้มาใส่
        dialog.setContentView(R.layout.layout_bottom_sheet_login)

        // 3. (Optional) ผูกปุ่มข้างใน Popup เพื่อสั่งงานต่อ
        val btnSubmit = dialog.findViewById<Button>(R.id.btnSubmitLogin)
        btnSubmit?.setOnClickListener {
            // โค้ดสำหรับเช็ค Login (เดี๋ยวค่อยทำ)
            // พอ Login ผ่าน ให้ไปหน้า Home:
            // val intent = Intent(this, HomeActivity::class.java)
            // startActivity(intent)
            dialog.dismiss() // ปิด Popup
        }

        // 4. โชว์เลย!
        dialog.show()
    }
}