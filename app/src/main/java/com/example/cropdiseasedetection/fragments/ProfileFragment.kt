package com.example.cropdiseasedetection.fragments

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cropdiseasedetection.R
import com.example.cropdiseasedetection.Users
import com.example.cropdiseasedetection.utils.Constants.Companion.auth
import com.example.cropdiseasedetection.utils.Constants.Companion.databaseReference
import com.example.cropdiseasedetection.utils.Constants.Companion.uid
import com.example.cropdiseasedetection.utils.Constants.Utils.showToast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.fragment_profile.*
import java.io.File

class ProfileFragment : Fragment() {
    private lateinit var storageReference: StorageReference
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

    private fun loadProfileDetails() {
       uid = auth.currentUser?.uid.toString()
        databaseReference
        if (uid.isNotEmpty()){
            databaseReference.child(uid).addValueEventListener(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val user = snapshot.getValue(Users::class.java)
                    if (user != null) {
                        firstname_Profile.text = user.firstname
                        Lastname_profile.text = user.lastname
                        profile_email.text = user.email
                    }
                  //  getUserProfile()
                }

                override fun onCancelled(error: DatabaseError) {
                    showToast(activity, "Failed to retrieve Profile Data")
                    Log.i("FIREBASE", "error:$error")
                }

            })
        }

    }
    private fun getUserProfile(){
        storageReference = FirebaseStorage.getInstance().reference.child("Users/$uid.jpg")
        val localFile = File.createTempFile("tempImage", "jpg")
        storageReference.getFile(localFile).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
            profileImg.setImageBitmap(bitmap)
        }.addOnFailureListener{
            showToast(activity,"Failed to load profile Image")
        }
    }

}