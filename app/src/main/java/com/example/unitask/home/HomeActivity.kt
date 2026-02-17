package com.example.unitask.home

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
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
import com.example.unitask.project.ProjectDetailActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.Calendar

class HomeActivity : AppCompatActivity() {

    private var isMobileFav = false
    private var isClinicFav = false
    private lateinit var projectContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        projectContainer = findViewById(R.id.llProjectContainer)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.bottomNavigation)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(0, 0, 0, systemBars.bottom)
            insets
        }

        val cardMobile = findViewById<CardView>(R.id.cardProjectMobile)
        val cardClinic = findViewById<CardView>(R.id.cardProjectClinic)
        val ivStarMobile = findViewById<ImageView>(R.id.ivStarMobile)
        val ivStarClinic = findViewById<ImageView>(R.id.ivStarClinic)
        val fabAdd = findViewById<View>(R.id.fabContainer)

        cardMobile.setOnClickListener {
            val intent = Intent(this, ProjectDetailActivity::class.java)
            intent.putExtra("PROJECT_NAME", "Mobile Project")
            startActivity(intent)
        }

        cardClinic.setOnClickListener {
            val intent = Intent(this, ProjectDetailActivity::class.java)
            intent.putExtra("PROJECT_NAME", "Nahi Clinic")
            startActivity(intent)
        }

        ivStarMobile.setOnClickListener {
            isMobileFav = !isMobileFav
            updateStarUI(ivStarMobile, isMobileFav)
            reorderProjects(projectContainer, cardMobile, cardClinic)
        }

        ivStarClinic.setOnClickListener {
            isClinicFav = !isClinicFav
            updateStarUI(ivStarClinic, isClinicFav)
            reorderProjects(projectContainer, cardMobile, cardClinic)
        }

        fabAdd.setOnClickListener {
            showAddProjectBottomSheet()
        }
    }

    private fun showAddProjectBottomSheet() {
        val dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.layout_bottom_sheet_add_project, null)
        dialog.setContentView(view)

        val btnSave = view.findViewById<Button>(R.id.btnSaveProject)
        val ivClose = view.findViewById<ImageView>(R.id.ivCloseAddProject)
        val etName = view.findViewById<EditText>(R.id.etProjectName)
        val etDueDate = view.findViewById<EditText>(R.id.etProjectDueDate)
        val rlDueDateContainer = view.findViewById<View>(R.id.rlDueDateContainer)

        val openDatePicker = View.OnClickListener {
            showDatePicker(etDueDate!!)
        }
        rlDueDateContainer?.setOnClickListener(openDatePicker)
        etDueDate?.setOnClickListener(openDatePicker)

        ivClose?.setOnClickListener { dialog.dismiss() }

        btnSave?.setOnClickListener {
            val name = etName.text.toString()
            val dueDate = etDueDate.text.toString()
            dialog.dismiss()
            showInviteMemberBottomSheet(name, dueDate)
        }
        dialog.show()
    }

    private fun showDatePicker(editText: EditText) {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(this, { _, year, month, day ->
            val date = "$day/${month + 1}/$year"
            editText.setText(date)
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
        datePickerDialog.show()
    }

    private fun showInviteMemberBottomSheet(projectName: String, dueDate: String) {
        val dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.layout_bottom_sheet_invite_member, null)
        dialog.setContentView(view)

        val btnInvite = view.findViewById<Button>(R.id.btnInviteSubmit)
        val etEmail = view.findViewById<EditText>(R.id.etInviteEmail)

        btnInvite?.setOnClickListener {
            val email = etEmail.text.toString()
            dialog.dismiss()
            addNewProjectCard(projectName, dueDate, email)
        }
        dialog.show()
    }

    private fun addNewProjectCard(name: String, date: String, memberEmail: String) {
        val cardView = CardView(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(0, 32, 0, 0) }
            radius = 60f
            cardElevation = 6f
            setCardBackgroundColor(resources.getColor(R.color.white))
        }

        val innerLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(60, 60, 60, 60)
            setBackgroundColor(resources.getColor(R.color.white))
        }

        val title = TextView(this).apply {
            text = name
            textSize = 18f
            setTypeface(null, android.graphics.Typeface.BOLD)
            setTextColor(resources.getColor(R.color.black))
        }

        val infoText = TextView(this).apply {
            text = "0 Task  1 members"
            setTextColor(resources.getColor(R.color.text_gray))
            setPadding(0, 10, 0, 10)
        }

        innerLayout.addView(title)
        innerLayout.addView(infoText)
        cardView.addView(innerLayout)

        cardView.setOnClickListener {
            val intent = Intent(this, ProjectDetailActivity::class.java)
            intent.putExtra("PROJECT_NAME", name)
            intent.putExtra("MEMBER_EMAIL", memberEmail)
            startActivity(intent)
        }

        projectContainer.addView(cardView)
    }

    private fun updateStarUI(imageView: ImageView, isFav: Boolean) {
        if (isFav) {
            imageView.setImageResource(android.R.drawable.btn_star_big_on)
            imageView.setColorFilter(resources.getColor(android.R.color.holo_orange_light))
        } else {
            imageView.setImageResource(android.R.drawable.btn_star_big_off)
            imageView.clearColorFilter()
        }
    }

    private fun reorderProjects(container: LinearLayout, cardMobile: CardView, cardClinic: CardView) {
        container.removeView(cardMobile)
        container.removeView(cardClinic)
        if (isMobileFav && !isClinicFav) {
            container.addView(cardMobile, 2)
            container.addView(cardClinic, 3)
        } else if (!isMobileFav && isClinicFav) {
            container.addView(cardClinic, 2)
            container.addView(cardMobile, 3)
        } else {
            container.addView(cardMobile, 2)
            container.addView(cardClinic, 3)
        }
    }
}