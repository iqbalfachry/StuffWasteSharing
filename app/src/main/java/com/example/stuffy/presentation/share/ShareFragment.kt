package com.example.stuffy.presentation.share


import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.location.Geocoder
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment

import com.example.stuffy.core.utils.createTempFile
import com.example.stuffy.core.utils.uriToFile
import com.example.stuffy.databinding.FragmentShareBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import org.koin.androidx.viewmodel.ext.android.viewModel

import java.io.File
import java.util.*


class ShareFragment :  Fragment() {

    private var _binding: FragmentShareBinding? = null
    private val shareViewModel : ShareViewModel by viewModel()
    private var getFile: File? = null
    private var fusedLocationClient: FusedLocationProviderClient?=null
    private val binding get() = _binding
    private var geocoder:Geocoder?=null
    private lateinit var location: Location
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ConstraintLayout? {

        _binding = FragmentShareBinding.inflate(inflater, container, false)

        fusedLocationClient = activity?.let { LocationServices.getFusedLocationProviderClient(it) }
        geocoder = Geocoder(activity, Locale.getDefault())
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMyLastLocation()
        binding?.count?.text = shareViewModel.count.value.toString()
        binding?.inc?.setOnClickListener{
            shareViewModel.inc()
            binding?.count?.text = shareViewModel.count.value.toString()
        }
        binding?.dec?.setOnClickListener{
            shareViewModel.dec()
            binding?.count?.text = shareViewModel.count.value.toString()
        }
        binding?.camera?.setOnClickListener{
            startGallery()
        }
        binding?.camera?.setOnLongClickListener{
            startTakePhoto()
            true
        }

    }
    private fun checkPermission(permission: String): Boolean {
        return activity?.let {
            ContextCompat.checkSelfPermission(
                it,
                permission
            )
        } == PackageManager.PERMISSION_GRANTED
    }
    private fun setLocation(location: Location){
        this.location = location

    }
    private fun getMyLastLocation() {
        if (checkPermission(Manifest.permission.ACCESS_FINE_LOCATION) &&
            checkPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
        ) {
            fusedLocationClient?.lastLocation?.addOnSuccessListener { location: Location? ->
                if (location != null) {
                    setLocation(location)
               binding?.textView62?.setText( geocoder?.getFromLocation(location.latitude, location.longitude, 1)?.get(0)?.getAddressLine(0).toString())

                } else {
                    Toast.makeText(
                        activity,
                        "Location is not found. Try Again",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }
        } else {
            requestPermissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )

        }

    }
    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions[Manifest.permission.ACCESS_FINE_LOCATION] ?: false -> {
                    // Precise location access granted.
                    getMyLastLocation()
                }
                permissions[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false -> {
                    // Only approximate location access granted.
                    getMyLastLocation()
                }
                else -> {
                    // No location access granted.
                }
            }
        }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedImg: Uri = result.data?.data as Uri
            val myFile = activity?.let { uriToFile(selectedImg, it) }
            getFile = myFile

            binding?.camera?.setImageURI(selectedImg)
        }
    }

    private fun startGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a Picture")
        launcherIntentGallery.launch(chooser)
    }


    private fun startTakePhoto() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        activity?.let { intent.resolveActivity(it.packageManager) }
        activity?.application?.let { it ->
            createTempFile(it).also {
                val photoURI: Uri? = activity?.let { it1 ->
                    FileProvider.getUriForFile(
                        it1,
                        "com.example.stuffy",
                        it
                    )
                }
                currentPhotoPath = it.absolutePath
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                launcherIntentCamera.launch(intent)
            }
        }
    }
    private lateinit var currentPhotoPath: String
    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            val myFile = File(currentPhotoPath)
            getFile = myFile
            val result = BitmapFactory.decodeFile(myFile.path)
            binding?.camera?.setImageBitmap(result)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}