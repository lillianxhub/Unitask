package com.example.unitask.project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.unitask.R

class ProjectDetailActivity : AppCompatActivity(), AddTaskBottomSheetDialog.AddTaskListener {

    private lateinit var layoutTasks: LinearLayout
    private lateinit var layoutMembers: LinearLayout
    private lateinit var tvTabTask: TextView
    private lateinit var tvTabMembers: TextView
    private lateinit var indicatorTask: View
    private lateinit var indicatorMembers: View
    private lateinit var llTasksContainer: LinearLayout
    private lateinit var llEmptyTasks: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_detail)

        // Initialize Views
        layoutTasks = findViewById(R.id.layoutTasks)
        layoutMembers = findViewById(R.id.layoutMembers)
        tvTabTask = findViewById(R.id.tvTabTask)
        tvTabMembers = findViewById(R.id.tvTabMembers)
        indicatorTask = findViewById(R.id.indicatorTask)
        indicatorMembers = findViewById(R.id.indicatorMembers)
        llTasksContainer = findViewById(R.id.llTasksContainer)
        llEmptyTasks = findViewById(R.id.llEmptyTasks)
        val fabAddTask = findViewById<View>(R.id.fabContainer)

        val projectName = intent.getStringExtra("PROJECT_NAME") ?: "Mobile Project"
        val memberEmail = intent.getStringExtra("MEMBER_EMAIL")
        
        findViewById<TextView>(R.id.tvProjectTitle).text = projectName

        // Logic: ถ้าเป็นโปรเจกต์ที่เพิ่งสร้างใหม่ (ไม่มี Task) ให้ซ่อน Task เก่า
        if (projectName != "Mobile Project" && projectName != "Nahi Clinic") {
            findViewById<View>(R.id.task1).visibility = View.GONE
            findViewById<View>(R.id.task2).visibility = View.GONE
            findViewById<View>(R.id.task3).visibility = View.GONE
            
            llEmptyTasks.visibility = View.VISIBLE
            
            if (memberEmail == null) {
                findViewById<View>(R.id.member1).visibility = View.GONE
                findViewById<View>(R.id.member2).visibility = View.GONE
            } else {
                findViewById<View>(R.id.member2).visibility = View.GONE
            }
        }

        findViewById<ImageView>(R.id.ivBack).setOnClickListener {
            finish()
        }

        findViewById<LinearLayout>(R.id.tabTask).setOnClickListener {
            switchTab(true)
        }

        findViewById<LinearLayout>(R.id.tabMembers).setOnClickListener {
            switchTab(false)
        }

        // เชื่อมต่อปุ่ม + เพื่อเปิดหน้า Add New Task
        fabAddTask.setOnClickListener {
            val dialog = AddTaskBottomSheetDialog()
            dialog.listener = this // ตั้งค่า Listener ให้กับหน้าต่างเพิ่มงาน
            dialog.show(supportFragmentManager, "AddTaskBottomSheet")
        }
    }

    // ฟังก์ชันที่จะทำงานเมื่อกดปุ่ม Save ในหน้า Add Task
    override fun onTaskAdded(name: String, description: String, assignedTo: String, dueDate: String, priority: String) {
        // ซ่อนหน้าว่าง
        llEmptyTasks.visibility = View.GONE
        
        // สร้าง View ใหม่สำหรับงานที่แอดเข้ามา
        val inflater = LayoutInflater.from(this)
        val taskView = inflater.inflate(R.layout.item_task_mock, llTasksContainer, false) as CardView
        
        // เซ็ตข้อมูลตามที่กรอกมา
        taskView.findViewById<TextView>(R.id.tvTaskTitle)?.text = name // สมมติว่ามี id นี้ใน item_task_mock
        // (ในอนาคตคุณสามารถปรับแต่งฟิลด์อื่นๆ ใน item_task_mock ให้ตรงตามข้อมูลที่รับมาได้ครับ)
        
        // เพิ่มเข้าไปในรายการ
        llTasksContainer.addView(taskView, 0) // เพิ่มไว้บนสุด
    }

    private fun switchTab(isTask: Boolean) {
        if (isTask) {
            layoutTasks.visibility = View.VISIBLE
            layoutMembers.visibility = View.GONE
            tvTabTask.setTextColor(resources.getColor(R.color.uni_purple))
            indicatorTask.visibility = View.VISIBLE
            tvTabMembers.setTextColor(resources.getColor(R.color.text_gray))
            indicatorMembers.visibility = View.INVISIBLE
        } else {
            layoutTasks.visibility = View.GONE
            layoutMembers.visibility = View.VISIBLE
            tvTabTask.setTextColor(resources.getColor(R.color.text_gray))
            indicatorTask.visibility = View.INVISIBLE
            tvTabMembers.setTextColor(resources.getColor(R.color.uni_purple))
            indicatorMembers.visibility = View.VISIBLE
        }
    }
}