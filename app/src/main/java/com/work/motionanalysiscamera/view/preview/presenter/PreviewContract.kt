package com.work.motionanalysiscamera.view.preview.presenter

import java.io.File

interface PreviewContract {
    interface View {

    }

    interface Presenter {
        fun imageAnalysis(file: File)
    }
}