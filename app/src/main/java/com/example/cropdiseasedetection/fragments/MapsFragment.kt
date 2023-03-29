package com.example.cropdiseasedetection.fragments

import android.content.pm.PackageManager
import android.location.Location
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import com.example.cropdiseasedetection.R
import com.example.cropdiseasedetection.utils.Constants.Companion.REQUESTCODE
import com.example.cropdiseasedetection.utils.Constants.Utils.showToast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment() {
    private lateinit var currentLocation : Location
    private lateinit var fuseLocationProviderClient : FusedLocationProviderClient


    private val callback = OnMapReadyCallback { googleMap ->

        val latLng = LatLng(currentLocation.latitude, currentLocation.longitude)
        val markerOptions = MarkerOptions().position(latLng).title("Current Location")

        googleMap!!.animateCamera(CameraUpdateFactory.newLatLng(latLng))
        googleMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,7f))
        googleMap!!.addMarker(markerOptions)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fuseLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        getLocationPermission()


    }
    private fun getLocationPermission(){
        val checkPermission = ActivityCompat.checkSelfPermission(requireActivity(),android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            requireActivity(),android.Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED
        if (checkPermission){
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),REQUESTCODE)
        }

        val task = fuseLocationProviderClient.lastLocation.addOnSuccessListener { location ->
            if (location != null){
                currentLocation = location
                showToast(activity,currentLocation.latitude.toString() + "" + currentLocation.longitude.toString())
                val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
                mapFragment?.getMapAsync(callback)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
                REQUESTCODE-> if (grantResults.isNotEmpty() &&  grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    getLocationPermission()
                }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }



}