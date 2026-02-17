package com.example.unitask.project

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.unitask.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Calendar

class AddTaskBottomSheetDialog : BottomSheetDialogFragment() {

    interface AddTaskListener {
        fun onTaskAdded(name: String, description: String, assignedTo: String, dueDate: String, priority: String)
    }

    var listener: AddTaskListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.layout_bottom_sheet_add_task, container, false)

        val etTaskName = view.findViewById<EditText>(R.id.etTaskName)
        val etTaskDesc = view.findViewById<EditText>(R.id.etTaskDescription)
        val etDueDate = view.findViewById<EditText>(R.id.etTaskDueDate)
        val rlDueDate = view.findViewById<View>(R.id.rlTaskDueDateContainer)
        val tvAssignedTo = view.findViewById<TextView>(R.id.tvAssignedTo)
        val tvPriority = view.findViewById<TextView>(R.id.tvPriority)
        val btnSave = view.findViewById<Button>(R.id.btnSaveTask)
        val ivClose = view.findViewById<View>(R.id.ivCloseAddTask)

        ivClose.setOnClickListener { dismiss() }

        // Date Picker logic
        val dateClickListener = View.OnClickListener {
            val calendar = Calendar.getInstance()
            DatePickerDialog(requireContext(), { _, year, month, day ->
                etDueDate.setText("$day/${month + 1}/$year")
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }
        rlDueDate.setOnClickListener(dateClickListener)
        etDueDate.setOnClickListener(dateClickListener)

        btnSave.setOnClickListener {
            val name = etTaskName.text.toString()
            val desc = etTaskDesc.text.toString()
            val date = etDueDate.text.toString()
            val assigned = tvAssignedTo.text.toString()
            val priority = tvPriority.text.toString()

            if (name.isNotEmpty()) {
                listener?.onTaskAdded(name, desc, assigned, date, priority)
                dismiss()
            }
        }

        return view
    }
}