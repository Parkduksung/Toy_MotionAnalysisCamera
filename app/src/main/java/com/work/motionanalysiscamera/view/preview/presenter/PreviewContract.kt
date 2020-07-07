package com.work.motionanalysiscamera.view.preview.presenter

import android.graphics.Bitmap
import java.io.File

interface PreviewContract {
    interface View {
        fun showImage(bitmap: Bitmap)
    }

    interface Presenter {
        fun imageAnalysis(file: File)
        fun faceDetection(file: File)
    }
}