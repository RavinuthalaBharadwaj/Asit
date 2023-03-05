package com.Audisankara.asit.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.Audisankara.asit.MainActivity
import com.Audisankara.asit.R
import com.Audisankara.asit.databinding.FragmentAccountSettingBinding
import com.Audisankara.asit.helper.Constant
import com.Audisankara.asit.helper.Session
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.database.*


class AccountSettingFrag : Fragment() {

    private lateinit var binding: FragmentAccountSettingBinding
    private lateinit var bottomSheetDialog: BottomSheetDialog
    private lateinit var bottomSheetDialogOne: BottomSheetDialog
    private lateinit var bottomSheetDialogThree: BottomSheetDialog
    private lateinit var firebaseDatabase : FirebaseDatabase
    private lateinit var dbReference: DatabaseReference
    private lateinit var userReference: DatabaseReference
    private lateinit var session: Session
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentAccountSettingBinding.inflate(layoutInflater,container,false)

        session = Session(requireActivity())

        MainActivity.chipNavigationBar.visibility = View.GONE
        firebaseDatabase =
            FirebaseDatabase.getInstance("https://audisankara-institute-default-rtdb.asia-southeast1.firebasedatabase.app/")

        dbReference =
            firebaseDatabase.reference.child("cse")

        val animation = AnimationUtils.makeInAnimation(requireContext(), true)
        animation.duration = 500
        binding.LytAccountSettings.startAnimation(animation)

        userReference =
            dbReference.child(session.getData(Constant.ROLLNUMBER))

        bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.apply {
            this.setContentView(R.layout.change_username)
            val etNewName = this.findViewById<EditText>(R.id.etNewName)
            val cvProceed = this.findViewById<CardView>(R.id.proceed)

            cvProceed!!.setOnClickListener {
                when {
                    etNewName!!.text.toString().isEmpty() -> {
                        etNewName.error = "Enter New name"
                        etNewName.requestFocus()
                    }
                    else -> {
                        changeUserName(etNewName.text.toString())
                    }
                }
            }
        }

        binding.LytChangeUsername.setOnClickListener {
            bottomSheetDialog.show()
        }

        bottomSheetDialogOne = BottomSheetDialog(requireContext())

        bottomSheetDialog.apply {
            this.setContentView(R.layout.change_password_bottom_sheet)
            val tvMsg = this.findViewById<TextView>(R.id.TvResetText)
            val etNewPassword = this.findViewById<EditText>(R.id.etNewPassword)
            val proceed = this.findViewById<CardView>(R.id.Cvproceed)

            proceed!!.setOnClickListener {
                if(etNewPassword!!.text.toString().isEmpty()) {
                    Toast.makeText(requireContext(),"Enter New Password",Toast.LENGTH_LONG).show()
                }else{
                    userReference.child(Constant.PASSWORD).setValue(etNewPassword!!.text.toString()).addOnCompleteListener {
                        if(it.isSuccessful) {
                            tvMsg!!.visibility = View.VISIBLE
                        }else{
                            Toast.makeText(requireContext(),"Something went Wrong",Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
        binding.LytChangePassword.setOnClickListener {
            bottomSheetDialogOne.show()
        }

        bottomSheetDialogThree = BottomSheetDialog(requireContext())
        bottomSheetDialogThree.apply {
            this.setContentView(R.layout.all_profile_bottom_sheet)
        }

        binding.cvProfileSelect.setOnClickListener {
            bottomSheetDialogThree.show()
        }
        return binding.root
    }

    private fun changeUserName(name : String?) {
        userReference.child(Constant.UPDATENAME).setValue(name).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(requireContext(),"Changed Successfully",Toast.LENGTH_LONG).show()
                bottomSheetDialog.dismiss()
                session.setData(Constant.NAME,name)
            }else{
                Toast.makeText(requireContext(),"Something Went wrong! Try Again",Toast.LENGTH_LONG).show()
            }
        }
    }

}