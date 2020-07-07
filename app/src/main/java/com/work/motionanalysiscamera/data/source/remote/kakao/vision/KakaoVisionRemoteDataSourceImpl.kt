package com.work.motionanalysiscamera.data.source.remote.kakao.vision

import com.work.motionanalysiscamera.network.api.KakaoApi
import com.work.motionanalysiscamera.network.model.vision.face.FaceDetectionResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KakaoVisionRemoteDataSourceImpl(private val visionApi: KakaoApi.Vision) :
    KakaoVisionRemoteDataSource {
    override fun getFaceDetection(
        file: MultipartBody.Part,
        onSuccess: (faceDetectionResponse: FaceDetectionResponse) -> Unit,
        onFailure: () -> Unit
    ) {

        visionApi.faceDetection(file).enqueue(object : Callback<FaceDetectionResponse> {
            override fun onFailure(call: Call<FaceDetectionResponse>?, t: Throwable?) {
                onFailure()
            }

            override fun onResponse(
                call: Call<FaceDetectionResponse>?,
                response: Response<FaceDetectionResponse>?
            ) {
                response?.body()?.let(onSuccess)
            }
        })

    }
}