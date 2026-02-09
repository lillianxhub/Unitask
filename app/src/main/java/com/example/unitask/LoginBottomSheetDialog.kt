package com.example.unitask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class LoginBottomSheetDialog : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.layout_bottom_sheet_login, container, false)

        val btnSubmit = view.findViewById<Button>(R.id.btnlogin)
        btnSubmit.setOnClickListener {
            // โค้ดสำหรับเช็ค Login
            dismiss()
        }

//        val initialPadding = view.paddingBottom
//        // จัดการแถบขาวด้านล่าง
//        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.updatePadding(
//                left = systemBars.left + v.paddingLeft,
//                top = systemBars.top,
//                right = systemBars.right + v.paddingRight,
//                bottom = initialPadding
//            )
//            insets
//        }

        return view
    }
}

