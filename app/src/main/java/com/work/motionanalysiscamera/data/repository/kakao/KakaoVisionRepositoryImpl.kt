package com.work.motionanalysiscamera.data.repository.kakao

import com.work.motionanalysiscamera.data.source.remote.kakao.vision.KakaoVisionRemoteDataSource
import com.work.motionanalysiscamera.network.model.vision.face.FaceDetectionResponse
import okhttp3.MultipartBody

class KakaoVisionRepositoryImpl(
    private val kakaoVisionRemoteDataSource: KakaoVisionRemoteDataSource
) : KakaoVisionRepository {
    override fun getFaceDetection(
        file: MultipartBody.Part,
        onSuccess: (faceDetectionResponse: FaceDetectionResponse) -> Unit,
        onFailure: () -> Unit
    ) {
        kakaoVisionRemoteDataSource.getFaceDetection(file, onSuccess, onFailure)
    }
}