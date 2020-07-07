package com.work.motionanalysiscamera.data.repository.kakao

import com.work.motionanalysiscamera.network.model.vision.face.FaceDetectionResponse
import okhttp3.MultipartBody

interface KakaoVisionRepository {
    fun getFaceDetection(
        file: MultipartBody.Part,
        onSuccess: (faceDetectionResponse: FaceDetectionResponse) -> Unit,
        onFailure: () -> Unit
    )
}