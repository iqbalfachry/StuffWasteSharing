package com.example.stuffy.presentation.share


import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.ContentResolver
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.*
import android.location.Geocoder
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.example.stuffy.core.data.Resource

import com.example.stuffy.core.utils.createTempFile
import com.example.stuffy.core.utils.uriToFile
import com.example.stuffy.databinding.FragmentShareBinding
import com.example.stuffy.ml.TfLiteModel1


import com.example.stuffy.presentation.main.MainActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer


import java.io.File
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.util.*


class ShareFragment :  Fragment() {

    private var _binding: FragmentShareBinding? = null
    private val shareViewModel : ShareViewModel by viewModel()
    private var getFile: File? = null
    private var fusedLocationClient: FusedLocationProviderClient?=null
    private val binding get() = _binding
    private var geocoder:Geocoder?=null
    private lateinit var auth: FirebaseAuth
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
        auth = Firebase.auth
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
        binding?.more?.setOnClickListener {

            if (getFile != null) {
                val file = getFile as File
                val name =
                    binding?.textView27?.text.toString().toRequestBody("text/plain".toMediaType())
                val requestImageFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
                val files: MultipartBody.Part = MultipartBody.Part.createFormData(
                    "files",
                    file.name,
                    requestImageFile
                )
                val description = binding?.textView29?.text.toString().toRequestBody("text/plain".toMediaType())

                val location = binding?.textView62?.text.toString().toRequestBody("text/plain".toMediaType())

                    shareViewModel.createProduct(files, description, name, location).observe(viewLifecycleOwner) {
                        if (it != null) {
                            when (it) {
                                is Resource.Loading -> {

                                }
                                is Resource.Success -> {
                                    it.data?.let {
                                        Toast.makeText(
                                            activity,
                                            "file successfully uploaded",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                    it.data?.let {
                                        auth.currentUser?.email?.let { it1 ->
                                            shareViewModel.createTransaction(
                                                it.id,
                                                it1, "Menunggu"
                                            ).observe(viewLifecycleOwner) { it2 ->
                                                if (it2 != null) {
                                                    when (it2) {
                                                        is Resource.Loading -> {

                                                        }
                                                        is Resource.Success -> {

                                                        }
                                                        is Resource.Error -> {


                                                        }
                                                    }
                                                }
                                            }

                                        }

                                    }
                                binding?.textView27?.setText("")
                                    binding?.textView29?.setText("")
                                    binding?.textView62?.setText("")
                                }
                                is Resource.Error -> {


                                }
                            }
                        }
                    }

            }
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
           result.data?.data?.let{
                selectedImg->
                val myFile = activity?.let { uriToFile(selectedImg, it) }
                getFile = myFile
              val cr: ContentResolver? =activity?.contentResolver
                val inputStream =cr?.openInputStream(selectedImg)
                val bitmap = BitmapFactory.decodeStream(inputStream)
                binding?.camera?.setImageURI(selectedImg)
               output(bitmap)
            }

        }
    }
private fun output(bitmap: Bitmap){
    val height: Int = bitmap.height
    val width: Int = bitmap.width
    val bmpGrayscale = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bmpGrayscale)
    val paint = Paint()
    val colorMatrix = ColorMatrix()
    colorMatrix.setSaturation(0f)
    val colorMatrixFilter = ColorMatrixColorFilter(colorMatrix)
    paint.colorFilter = colorMatrixFilter
    canvas.drawBitmap(bitmap, 0f, 0f, paint)
    val bitmap1 = Bitmap.createScaledBitmap(
        bitmap,
        28,
        28,
        true
    )
    val height1: Int = bitmap1.height
    val width1: Int = bitmap1.width
    val mImgData: ByteBuffer = ByteBuffer.allocateDirect(4 * width1 * height1)
    mImgData.order(ByteOrder.nativeOrder())
    val pixels = IntArray(width1 * height1)
    bitmap1.getPixels(pixels, 0, width1, 0, 0, width1, height1)
    for (pixel in pixels) {
        mImgData.putFloat(Color.blue(pixel).toFloat() / 255.0f)
    }
    val model = activity?.let { TfLiteModel1.newInstance(it) }

// Creates inputs for reference.
    val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 28, 28,1), DataType.FLOAT32)

    inputFeature0.loadBuffer(mImgData)

// Runs model inference and gets result.
    val outputs = model?.process(inputFeature0)
    val outputFeature0 = outputs?.outputFeature0AsTensorBuffer
val confidences=outputFeature0?.floatArray
    var maxPos=0
    var maxConfidence= 0f
    if (confidences != null) {
        for(i in confidences.indices){
                if(confidences[i]>maxConfidence){
                    maxConfidence = confidences[i]
                    maxPos =i
                }
        }
    }
    val classes = arrayOf("T-shirt/top", "Trouser", "Pullover", "Dress", "Coat",
        "Sandal", "Shirt", "Sneaker", "Bag", "Ankle boot")
    binding?.textView63?.text =classes[maxPos]
// Releases model resources if no longer used.
    model?.close()
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
output(result)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}