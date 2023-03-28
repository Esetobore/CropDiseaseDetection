package com.example.cropdiseasedetection


import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.cropdiseasedetection.databinding.ActivityMainBinding
import com.example.cropdiseasedetection.fragments.HomeFragment
import com.example.cropdiseasedetection.fragments.ProfileFragment
import com.example.cropdiseasedetection.utils.Constants.Companion.REQUESTCODE
import com.example.cropdiseasedetection.utils.Constants.Utils.showToast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>
    private var isReadPermissionGranted = false
    private var isLocationPermissionGranted = false
    private var isRecordPermissionGranted = false
    private var isCameraPermissionGranted = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){ permission ->
            isReadPermissionGranted = permission[Manifest.permission.READ_EXTERNAL_STORAGE] ?: isReadPermissionGranted
            isRecordPermissionGranted = permission[Manifest.permission.RECORD_AUDIO] ?: isRecordPermissionGranted
            isLocationPermissionGranted = permission[Manifest.permission.ACCESS_FINE_LOCATION] ?: isLocationPermissionGranted
            isCameraPermissionGranted = permission[Manifest.permission.CAMERA] ?: isCameraPermissionGranted }
            requestPermission()

        chip_app_nav_bar.menu.getItem(1).isEnabled = false
        chip_app_nav_bar.menu.getItem(2).isEnabled = false
        chip_app_nav_bar.background = null
        replaceFragment(HomeFragment())
        chip_app_nav_bar.setOnClickListener {
            when(it.id){
                R.id.bmHome -> replaceFragment(HomeFragment())
                R.id.bmUser -> replaceFragment(ProfileFragment())

                else ->{
                    showToast(this,"Error Exception")
                }
            }
           // true
        }
        floating_button_camera.setOnClickListener {
            cameraView()
        }

    }
    private fun requestPermission(){
        isReadPermissionGranted = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED

        isLocationPermissionGranted = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        isRecordPermissionGranted = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.RECORD_AUDIO
        ) == PackageManager.PERMISSION_GRANTED

        isCameraPermissionGranted = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED

        val permissionRequest : MutableList<String> = ArrayList()

        if (!isReadPermissionGranted){
            permissionRequest.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        if (!isRecordPermissionGranted){
            permissionRequest.add(Manifest.permission.RECORD_AUDIO)
        }
        if (!isLocationPermissionGranted){
            permissionRequest.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }
        if (!isCameraPermissionGranted){
            permissionRequest.add(Manifest.permission.CAMERA)
        }
        if (permissionRequest.isNotEmpty()){
            permissionLauncher.launch(permissionRequest.toTypedArray())
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.root_layout,fragment)
        fragmentTransaction.commit()
    }
    fun cameraView(){
        // Check camera permission if we have it.
        val checkSelfPermission = ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)==
                PackageManager.PERMISSION_GRANTED
        if (checkSelfPermission) {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, 1)
        } else {
            //Request camera permission if we don't have it.
            requestPermissions(arrayOf(android.Manifest.permission.CAMERA), REQUESTCODE)
        }
    }

}