package com.example.unitask.home

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.unitask.R

class MyProjectDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_my_project_detail)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.llTopNav)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(0, systemBars.top, 0, 0)
            insets
        }

        // รับข้อมูลจาก Intent
        val projectName = intent.getStringExtra("PROJECT_NAME") ?: "Mobile project"
        val projectDesc = intent.getStringExtra("PROJECT_DESC") ?: "Describe"
        
        // ผูก View
        val tvTitle = findViewById<TextView>(R.id.tvDetailProjectTitle)
        val ivBack = findViewById<ImageView>(R.id.ivBackDetail)

        tvTitle.text = projectName

        ivBack.setOnClickListener {
            finish()
        }

        // ในอนาคตสามารถเขียน Logic เพิ่มเติม เช่น ดึงข้อมูลคอมเมนต์จาก API
        // หรือจัดการการส่งข้อความคอมเมนต์ได้ที่นี่
    }
}