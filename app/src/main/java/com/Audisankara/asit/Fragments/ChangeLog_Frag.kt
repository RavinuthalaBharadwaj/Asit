package com.Audisankara.asit.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.Audisankara.asit.Adaptors.ChangeLogAdapter
import com.Audisankara.asit.MainActivity
import com.Audisankara.asit.Models.NotificationModel
import com.Audisankara.asit.databinding.FragmentChangeLogBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class ChangeLog_Frag : Fragment() {
    private lateinit var binding: FragmentChangeLogBinding
    private lateinit var changeLogAdapter: ChangeLogAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChangeLogBinding.inflate(layoutInflater,container,false)
        changeLogAdapter = ChangeLogAdapter(data())
        binding.ChangeLogRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.ChangeLogRecycler.adapter = changeLogAdapter
        binding.ChangeLogRecycler
        MainActivity.chipNavigationBar.visibility = View.GONE
        return binding.root
    }

    private fun data() : ArrayList<NotificationModel> {

        val data = arrayListOf<NotificationModel>(

            NotificationModel("Asit_v0.0.7-Patch-2_Phoenix",
                "Features we added\n\n   *Added New MidTwo Papers\n   *Added New ChangeLog\n   *Added Change Username Option\n   *Added Change Password",
                "6",
                "Jan"),
            NotificationModel("Asit_v0.0.7-Patch-3_Phoenix",
                "Fixed Login Issue\n\n   *Added New  Profile\n   *Fixed SomeBugs\n   *Fixed Performance Issues\n ",
                "14",
                "Jan")
        )
        return data
    }
}