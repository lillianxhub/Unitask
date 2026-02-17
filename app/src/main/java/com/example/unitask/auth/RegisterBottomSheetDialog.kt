package com.example.unitask.auth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.unitask.R
import com.example.unitask.splash.SplashActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class RegisterBottomSheetDialog : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.layout_bottom_sheet_register, container, false)

        val btnRegister = view.findViewById<Button>(R.id.btnRegisterSubmit)
        btnRegister.setOnClickListener {
            dismiss()
            val intent = Intent(requireContext(), SplashActivity::class.java)
            intent.putExtra("NEXT_SCREEN", "HOME")
            startActivity(intent)
            requireActivity().finish()
        }

        return view
    }
}