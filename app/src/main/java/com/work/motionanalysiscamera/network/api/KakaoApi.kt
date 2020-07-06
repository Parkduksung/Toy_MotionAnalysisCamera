package com.work.motionanalysiscamera.network.api

import com.work.motionanalysiscamera.network.model.KakaoPoseResponse
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

    companion object {
        const val HEADERS_AUTH = "Authorization: KakaoAK c1237e4617758c1a19d947651a71b145"
    }
}