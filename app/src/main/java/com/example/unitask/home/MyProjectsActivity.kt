package com.example.unitask.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.unitask.R
import com.example.unitask.data.ProjectManager

class MyProjectsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_my_projects)

        val projectListContainer = findViewById<LinearLayout>(R.id.llMyProjectsList)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.bottomNavigation)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(0, 0, 0, systemBars.bottom)
            insets
        }

        // 1. ดึงข้อมูล Project ทั้งหมดจาก ProjectManager
        val allProjects = ProjectManager.getAllProjects()

        // 2. เคลียร์ List เก่าก่อน (เผื่อมีการเรียกซ้ำ)
        projectListContainer.removeAllViews()

        // 3. วนลูปสร้างกล่อง Project ตามข้อมูลจริง
        val inflater = LayoutInflater.from(this)
        for (project in allProjects) {
            val projectView = inflater.inflate(R.layout.item_my_project, projectListContainer, false)
            
            // ผูกข้อมูลให้ตรงกับโปรเจกต์นั้นๆ
            projectView.findViewById<TextView>(R.id.tvMyProjectTitle).text = project.name
            projectView.findViewById<TextView>(R.id.tvMyProjectDesc).text = project.description
            projectView.findViewById<TextView>(R.id.tvMyProjectDueDate).text = "Due Date : ${project.dueDate}"
            projectView.findViewById<TextView>(R.id.tvMyProjectStatus).text = project.status

            // เมื่อกดที่กล่อง ให้ไปหน้า Detail ของโปรเจกต์นั้น
            projectView.setOnClickListener {
                val intent = Intent(this, MyProjectDetailActivity::class.java)
                intent.putExtra("PROJECT_NAME", project.name)
                startActivity(intent)
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            }

            projectListContainer.addView(projectView)
        }

        // เชื่อมต่อปุ่ม Home ด้านล่าง
        findViewById<LinearLayout>(R.id.navHome).setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            finish()
        }
    }
}