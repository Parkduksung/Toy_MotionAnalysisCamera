package com.work.motionanalysiscamera.view.camera.presenter

import android.content.Context
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.core.content.ContextCompat
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class CameraPresenter(private val cameraView: CameraContract.View) : CameraContract.Presenter {

    override fun takePhoto(context: Context, imgCapture: ImageCapture, directory: File) {
        val photoFile = File(
            directory,
            SimpleDateFormat(
                FILENAME_FORMAT, Locale.KOREA
            ).format(System.currentTimeMillis()) + FILE_EXTENSION
        )

        val outputOptions =
            ImageCapture.OutputFileOptions.Builder(photoFile).build()

        imgCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(context),
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {

                }

                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    cameraView.showTakePhoto(photoFile)
                }
            })
    }

    companion object {
        private const val FILE_EXTENSION = ".jpg"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
    }
}