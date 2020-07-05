package com.work.motionanalysiscamera.view.camera.presenter

import android.content.Context
import androidx.camera.core.ImageCapture
import java.io.File

interface CameraContract {

    interface View {
        fun showTakePhoto(saveFile: File)
    }

    interface Presenter {
        fun takePhoto(context: Context, imgCapture: ImageCapture, directory: File)
    }
}