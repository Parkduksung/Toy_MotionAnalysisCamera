package com.work.motionanalysiscamera.data.source.remote.kakao.vision

import com.work.motionanalysiscamera.network.model.vision.face.FaceDetectionResponse
import okhttp3.MultipartBody

interface KakaoVisionRemoteDataSource {
    fun getFaceDetection(
        file: MultipartBody.Part,
        onSuccess: (faceDetectionResponse: FaceDetectionResponse) -> Unit,
        onFailure: () -> Unit
    )
}