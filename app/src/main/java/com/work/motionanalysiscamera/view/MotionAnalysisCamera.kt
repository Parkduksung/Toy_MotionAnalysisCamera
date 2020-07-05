package com.work.motionanalysiscamera.view

import android.Manifest
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.PagerSnapHelper
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.work.motionanalysiscamera.R
import com.work.motionanalysiscamera.adapter.PictureAdapter
import com.work.motionanalysiscamera.base.BaseActivity
import com.work.motionanalysiscamera.databinding.ActivityMainBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class MotionAnalysisCamera :
    BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate, R.layout.activity_main),
    View.OnClickListener {

    private var preview: Preview? = null
    private var imageCapture: ImageCapture? = null
    private var camera: Camera? = null
    private val pictureAdapter by lazy { PictureAdapter() }

    private lateinit var outputDirectory: File

    private val permissionListener: PermissionListener by lazy {
        object : PermissionListener {
            override fun onPermissionGranted() {
                startCamera()
            }

            override fun onPermissionDenied(deniedPermissions: ArrayList<String>?) {
                finish()
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.camera_capture_button -> {
                takePhoto()
            }

            R.id.ib_preview -> {
                togglePicture(true)
                outputDirectory.listFiles()?.let {
                    pictureAdapter.addAll(it.toList().sortedDescending())
                }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkPermission()

        binding.cameraCaptureButton.setOnClickListener(this)
        binding.ibPreview.setOnClickListener(this)

        binding.rvPicture.run {
            this.adapter = pictureAdapter
            PagerSnapHelper().attachToRecyclerView(this)
        }

        outputDirectory = getOutputDirectory()

        if (outputDirectory.listFiles()?.size != 0) {
            outputDirectory.listFiles()?.toList()?.sortedDescending()?.get(0).let {
                Glide.with(this@MotionAnalysisCamera)
                    .load(it)
                    .error(R.drawable.ic_no_picture)
                    .apply(RequestOptions.circleCropTransform())
                    .into(binding.ibPreview)
            }
        }
    }


    fun togglePicture(isVisible: Boolean) {
        binding.apply {
            rvPicture.visibility = if (isVisible) View.VISIBLE else View.GONE
            viewFinder.visibility = if (isVisible) View.GONE else View.VISIBLE
        }
    }


    //미디어에 파일 만드는곳 그리고 거기에 저장된 파일 리턴.
    fun getOutputDirectory(): File {
        val mediaDir = externalMediaDirs.firstOrNull()?.let {
            File(it, resources.getString(R.string.app_name)).apply { mkdir() }
        }
        return if (mediaDir != null && mediaDir.exists()) mediaDir else filesDir
    }

    private fun startCamera() {

        val cameraProviderFuture =
            ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener(Runnable {
            // Used to bind the lifecycle of cameras to the lifecycle owner
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            // Preview
            preview = Preview.Builder()
                .build()

            imageCapture = ImageCapture.Builder()
                .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                .build()


            // Select back camera
            val cameraSelector =
                CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build()

            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                camera = cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageCapture
                )
                preview?.setSurfaceProvider(binding.viewFinder.createSurfaceProvider(camera?.cameraInfo))
            } catch (exc: Exception) {
                Log.e(TAG, "Use case binding failed", exc)
            }

        }, ContextCompat.getMainExecutor(this))
    }

    private fun takePhoto() {
        val imageCapture = imageCapture ?: return

        // Create timestamped output file to hold the image
        val photoFile = File(
            outputDirectory,
            SimpleDateFormat(
                FILENAME_FORMAT, Locale.KOREA
            ).format(System.currentTimeMillis()) + ".jpg"
        )


        // Create output options object which contains file + metadata
        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

        // Setup image capture listener which is triggered after photo has
        // been taken
        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(this),
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
                    Log.e(TAG, "Photo capture failed: ${exc.message}", exc)
                }

                override fun onImageSaved(output: ImageCapture.OutputFileResults) {

                    Glide.with(this@MotionAnalysisCamera)
                        .load(photoFile)
                        .error(R.drawable.ic_no_picture)
                        .apply(RequestOptions.circleCropTransform())
                        .into(binding.ibPreview)


                    val savedUri = Uri.fromFile(photoFile)
                    val msg = "Photo capture succeeded: $savedUri"

                    Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                    Log.d(TAG, msg)
                }
            })
    }

    private fun checkPermission() {
        TedPermission.with(this)
            .setPermissionListener(permissionListener)
            .setRationaleMessage("")
            .setDeniedMessage("")
            .setPermissions(Manifest.permission.CAMERA)
            .check()
    }

    companion object {
        private const val TAG = "CameraXBasic"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    }
}
