package com.work.motionanalysiscamera.view.camera

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.camera.core.Camera
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.work.motionanalysiscamera.R
import com.work.motionanalysiscamera.base.BaseFragment
import com.work.motionanalysiscamera.databinding.FragmentCameraBinding
import com.work.motionanalysiscamera.util.UtilFile
import com.work.motionanalysiscamera.view.camera.presenter.CameraContract
import org.koin.android.ext.android.get
import org.koin.core.parameter.parametersOf
import java.io.File
import java.util.*

class CameraFragment : BaseFragment<FragmentCameraBinding>(
    FragmentCameraBinding::bind,
    R.layout.fragment_camera
), CameraContract.View, View.OnClickListener {

    private lateinit var preview: Preview
    private lateinit var imageCapture: ImageCapture
    private lateinit var camera: Camera

    private lateinit var presenter: CameraContract.Presenter
    private lateinit var outputDirectory: File

    private val permissionListener: PermissionListener by lazy {
        object : PermissionListener {
            override fun onPermissionGranted() {
                startCamera()
            }

            override fun onPermissionDenied(deniedPermissions: ArrayList<String>?) {
                activity?.finish()
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.camera_capture_button -> {
                presenter.takePhoto(requireContext(), imageCapture, outputDirectory)
            }
            R.id.ib_preview -> {
                Navigation.findNavController(v)
                    .navigate(R.id.action_fragment_camera_to_fragment_preview)
            }
        }
    }

    override fun onBackPressed() {
        activity?.finish()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermission()
        presenter = get { parametersOf(this) }
        binding.cameraCaptureButton.setOnClickListener(this)
        binding.ibPreview.setOnClickListener(this)
        outputDirectory = UtilFile.getDirectoryFile()
    }

    override fun showTakePhoto(saveFile: File) {
        binding.ibPreview.let {
            it.post {
                Glide.with(requireContext())
                    .load(saveFile)
                    .error(R.drawable.ic_no_picture)
                    .apply(RequestOptions.circleCropTransform())
                    .into(it)
            }
        }


    }

    private fun startCamera() {

        val cameraProviderFuture =
            ProcessCameraProvider.getInstance(requireContext())

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
                preview.setSurfaceProvider(binding.viewFinder.createSurfaceProvider(camera.cameraInfo))
            } catch (exc: Exception) {
                Log.e(TAG, "Use case binding failed", exc)
            }
        }, ContextCompat.getMainExecutor(requireContext()))
    }

    private fun checkPermission() {
        TedPermission.with(requireContext())
            .setPermissionListener(permissionListener)
            .setRationaleMessage("")
            .setDeniedMessage("")
            .setPermissions(REQUIRED_PERMISSIONS)
            .check()
    }

    companion object {
        private const val TAG = "CameraFragment"
        private const val REQUIRED_PERMISSIONS = Manifest.permission.CAMERA
    }

}