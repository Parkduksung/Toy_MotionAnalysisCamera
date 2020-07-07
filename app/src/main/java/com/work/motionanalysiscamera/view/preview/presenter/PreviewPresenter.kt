package com.work.motionanalysiscamera.view.preview.presenter

import android.os.Build
import androidx.annotation.RequiresApi
import com.work.motionanalysiscamera.data.repository.kakao.KakaoPoseRepository
import com.work.motionanalysiscamera.data.repository.kakao.KakaoVisionRepository
import com.work.motionanalysiscamera.util.UtilFile
import java.io.File


class PreviewPresenter(
    private val previewView: PreviewContract.View,
    private val poseRepository: KakaoPoseRepository,
    private val visionRepository: KakaoVisionRepository
) : PreviewContract.Presenter {

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun imageAnalysis(file: File) {
        poseRepository.getImageAnalysis(
            UtilFile.getConvertMultiPartBodyPart(file),
            onSuccess = {

            },
            onFailure = {

            }
        )
    }

    override fun faceDetection(file: File) {
        visionRepository.getFaceDetection(
            UtilFile.getConvertMultiPartBodyPart(file),
            onSuccess = {

            },
            onFailure = {

            }

        )


    }
}