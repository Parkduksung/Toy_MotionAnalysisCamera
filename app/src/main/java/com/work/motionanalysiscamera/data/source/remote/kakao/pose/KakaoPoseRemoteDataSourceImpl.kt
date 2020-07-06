package com.work.motionanalysiscamera.data.source.remote.kakao.pose

import com.work.motionanalysiscamera.network.api.KakaoApi
import com.work.motionanalysiscamera.network.model.KakaoPoseResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KakaoPoseRemoteDataSourceImpl(private val kakaoPoseApi: KakaoApi.Pose) :
    KakaoPoseRemoteDataSource {

    override fun getImageAnalysis(
        file: MultipartBody.Part,
        onSuccess: (kakaoPoseResultList: List<KakaoPoseResponse>) -> Unit,
        onFailure: () -> Unit
    ) {

        kakaoPoseApi.imageAnalysis(file)
            .enqueue(object : Callback<List<KakaoPoseResponse>> {
                override fun onFailure(call: Call<List<KakaoPoseResponse>>?, t: Throwable?) {
                    onFailure()
                }

                override fun onResponse(
                    call: Call<List<KakaoPoseResponse>>?,
                    response: Response<List<KakaoPoseResponse>>?
                ) {
                    response?.body()?.let(onSuccess)
                }
            })


    }
}