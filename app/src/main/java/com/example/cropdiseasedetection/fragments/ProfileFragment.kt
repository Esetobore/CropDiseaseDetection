package com.example.cropdiseasedetection.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cropdiseasedetection.R
import com.example.cropdiseasedetection.utils.Constants.Utils.showToast
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        replaceFragment(MapsFragment())
        edit_profile_view.setOnClickListener {
            showToast(activity,"Edit Profile Data View")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)


    }


    private fun replaceFragment(fragment: Fragment){
        // val mapFragment = MapsFragment()
        val fragmentManager = childFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout_profile_map,fragment)
        fragmentTransaction.commit()
    }


}