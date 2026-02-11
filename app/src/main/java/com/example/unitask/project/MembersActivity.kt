package com.example.unitask.project

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.unitask.R

class MembersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_members)

        val memberEmail = intent.getStringExtra("MEMBER_EMAIL") ?: "member@gmail.com"
        findViewById<TextView>(R.id.tvInvitedEmail).text = memberEmail

        findViewById<ImageView>(R.id.ivBackMembers).setOnClickListener {
            finish()
        }
        
        findViewById<android.view.View>(R.id.btnInviteMore).setOnClickListener {
            // โค้ดสำหรับเชิญสมาชิกเพิ่ม (ถ้าต้องการ)
            finish()
        }
    }
}