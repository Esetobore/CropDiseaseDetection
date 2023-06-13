package com.example.cropdiseasedetection.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import com.example.cropdiseasedetection.R
import com.example.cropdiseasedetection.utils.Constants.Companion.REQUESTCODE
import com.example.cropdiseasedetection.utils.Constants.Utils.showToast
import com.example.cropdiseasedetection.utils.VALS
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*

import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment() , LocationListener, OnMapReadyCallback, LocationSource.OnLocationChangedListener{
    private lateinit var currentLocation : Location
    private lateinit var fuseLocationProviderClient : FusedLocationProviderClient
    lateinit var userLocationMarker : Marker



    override fun onMapReady(googleMap: GoogleMap) {
        val lt = VALS.lati
        val lg = VALS.longi
        val cord = LatLng(lt, lg)
        val markerOptions = MarkerOptions().position(cord).title("Current Location")
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location_agro))
        userLocationMarker = googleMap.addMarker(markerOptions)!!
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cord, 17f))
        startCurrentLocationUpdates(googleMap)

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
                Log.e("MAP LOCATION",currentLocation.latitude.toString() + "" + currentLocation.longitude.toString())
                val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
                mapFragment?.getMapAsync(this)
            }
        }
    }
    private fun startCurrentLocationUpdates(googleMap: GoogleMap){
        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext())
        //gets location every 4 minutes
        val locationRequest = com.google.android.gms.location.LocationRequest().setInterval(2000).setFastestInterval(2000)
            .setPriority(com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY)


        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return
        }
        Looper.myLooper()?.let {
            fusedLocationProviderClient.requestLocationUpdates(
                locationRequest,
                object : LocationCallback() {
                    override fun onLocationResult(locationResult: LocationResult) {
                        super.onLocationResult(locationResult)
                        for (location in locationResult.locations) {
                            val loc = locationResult.lastLocation
                            val latlng = LatLng(loc.latitude,loc.longitude)


                            //use previously created marker
                            userLocationMarker.position=latlng
                            userLocationMarker.rotation=location.bearing
                            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng,17f))

                        }
                        // Few more things we can do here:
                        // For example: Update the location of user on server
                    }
                },
                it
            )
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
                REQUESTCODE-> if (grantResults.isNotEmpty() &&  grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    getLocationPermission()
                }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onLocationChanged(location: Location) {
        location.latitude=VALS.lati
        location.longitude= VALS.longi
    }


}