package com.work.motionanalysiscamera.network.api

import com.work.motionanalysiscamera.network.model.KakaoPoseResponse
import com.work.motionanalysiscamera.network.model.vision.face.FaceDetectionResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface KakaoApi {

    interface Pose {
        @Multipart
        @Headers(HEADERS_AUTH)
        @POST("pose")
        fun imageAnalysis(
            @Part file: MultipartBody.Part
        ): Call<List<KakaoPoseResponse>>
    }

    interface Vision {
        @Multipart
        @Headers(HEADERS_AUTH)
        @POST("v1/vision/face/detect")
        fun faceDetection(
            @Part file: MultipartBody.Part
        ): Call<FaceDetectionResponse>
    }

    companion object {
        const val HEADERS_AUTH = "Authorization: KakaoAK c1237e4617758c1a19d947651a71b145"
    }
}