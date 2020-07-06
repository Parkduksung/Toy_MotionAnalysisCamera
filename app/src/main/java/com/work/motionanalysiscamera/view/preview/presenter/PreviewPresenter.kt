package com.work.motionanalysiscamera.view.preview.presenter

import com.work.motionanalysiscamera.data.repository.kakao.KakaoPoseRepository
import com.work.motionanalysiscamera.util.UtilFile
import java.io.File


class PreviewPresenter(
    private val previewView: PreviewContract.View,
    private val poseRepository: KakaoPoseRepository
) : PreviewContract.Presenter {

    override fun imageAnalysis(file: File) {

        poseRepository.getImageAnalysis(
            UtilFile.getConvertMultiPartBodyPart(file),
            onSuccess = {

            },
            onFailure = {

            }
        )
    }
}