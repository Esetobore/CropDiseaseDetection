package com.example.cropdiseasedetection.fragments

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.cropdiseasedetection.MainActivity
import com.example.cropdiseasedetection.R
import com.example.cropdiseasedetection.utils.Constants.Utils.showToastShort
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.identified_dialog.*
import kotlinx.android.synthetic.main.unidentified_dialog.*


class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? { return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homefrag_identified.setOnClickListener {
                val identifiedDialog = Dialog(requireContext(), android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen)
                identifiedDialog.setContentView(R.layout.identified_dialog)


            identifiedDialog.identified_okay_btn.setOnClickListener {
                startActivity(Intent(activity, MainActivity::class.java))
            }
            identifiedDialog.identified_askExpert_btn.setOnClickListener {
                showToastShort(activity,"This feature is not available yet")
            }
            identifiedDialog.identified_goBack.setOnClickListener {
                startActivity(Intent(activity, MainActivity::class.java))
            }
            identifiedDialog.show()

        }
        homefrag_unidentified.setOnClickListener {

            val unIdentifiedDialog = Dialog(requireContext(), android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen)
            unIdentifiedDialog.setContentView(R.layout.unidentified_dialog)

            unIdentifiedDialog.unIdentified_okay_btn.setOnClickListener {
                startActivity(Intent(activity, MainActivity::class.java))
            }
            unIdentifiedDialog.unIdentified_askExpert_btn.setOnClickListener {
                showToastShort(activity,"This feature is not available yet")
            }
            unIdentifiedDialog.unIdentified_goBack.setOnClickListener {
                startActivity(Intent(activity, MainActivity::class.java))
            }
            unIdentifiedDialog.show()
        }

    }



}