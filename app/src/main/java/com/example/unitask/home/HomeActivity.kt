package com.example.unitask.home

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.unitask.R
import com.example.unitask.data.Project
import com.example.unitask.data.ProjectManager
import com.example.unitask.project.ProjectDetailActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.Calendar

class HomeActivity : AppCompatActivity() {

    private lateinit var projectContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        projectContainer = findViewById(R.id.llMyProjectsListHome)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.bottomNavigation)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(0, 0, 0, systemBars.bottom)
            insets
        }

        // โหลดโปรเจกต์ทั้งหมดมาแสดงที่หน้า Home
        displayProjects()

        findViewById<View>(R.id.fabContainer).setOnClickListener {
            showAddProjectBottomSheet()
        }

        // เชื่อมต่อเมนูด้านล่าง
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        findViewById<LinearLayout>(R.id.navProject).setOnClickListener {
            val intent = Intent(this, MyProjectsActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
    }

    private fun displayProjects() {
        val allProjects = ProjectManager.getAllProjects()
        projectContainer.removeAllViews()

        val inflater = LayoutInflater.from(this)
        for (project in allProjects) {
            val projectView = inflater.inflate(R.layout.item_my_project, projectContainer, false)
            
            projectView.findViewById<TextView>(R.id.tvMyProjectTitle).text = project.name
            projectView.findViewById<TextView>(R.id.tvMyProjectDesc).text = project.description
            projectView.findViewById<TextView>(R.id.tvMyProjectDueDate).text = "Due Date : ${project.dueDate}"
            projectView.findViewById<TextView>(R.id.tvMyProjectStatus).text = project.status

            projectView.setOnClickListener {
                val intent = Intent(this, ProjectDetailActivity::class.java)
                intent.putExtra("PROJECT_NAME", project.name)
                startActivity(intent)
            }

            projectContainer.addView(projectView)
        }
    }

    private fun showAddProjectBottomSheet() {
        val dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.layout_bottom_sheet_add_project, null)
        dialog.setContentView(view)

        val btnSave = view.findViewById<Button>(R.id.btnSaveProject)
        val etName = view.findViewById<EditText>(R.id.etProjectName)
        val etDueDate = view.findViewById<EditText>(R.id.etProjectDueDate)
        val rlDueDate = view.findViewById<View>(R.id.rlDueDateContainer)

        rlDueDate?.setOnClickListener {
            showDatePicker(etDueDate!!)
        }

        btnSave?.setOnClickListener {
            val name = etName.text.toString()
            val dueDate = etDueDate.text.toString()
            if (name.isNotEmpty()) {
                dialog.dismiss()
                showInviteMemberBottomSheet(name, dueDate)
            }
        }
        dialog.show()
    }

    private fun showInviteMemberBottomSheet(projectName: String, dueDate: String) {
        val dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.layout_bottom_sheet_invite_member, null)
        dialog.setContentView(view)

        val btnInvite = view.findViewById<Button>(R.id.btnInviteSubmit)
        val etEmail = view.findViewById<EditText>(R.id.etInviteEmail)

        btnInvite?.setOnClickListener {
            val email = etEmail.text.toString()
            // บันทึกข้อมูลจริงลง Manager
            ProjectManager.addProject(Project(projectName, "Describe", dueDate, memberEmail = email))
            dialog.dismiss()
            
            // รีเฟรชหน้าจอเพื่อโชว์ข้อมูลใหม่
            displayProjects()
        }
        dialog.show()
    }

    private fun showDatePicker(editText: EditText) {
        val calendar = Calendar.getInstance()
        DatePickerDialog(this, { _, year, month, day ->
            editText.setText("$day/${month + 1}/$year")
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
    }
}