package com.work.motionanalysiscamera.data.source.remote.kakao.pose

import com.work.motionanalysiscamera.network.model.KakaoPoseResponse
import okhttp3.MultipartBody
import java.net.URL

interface KakaoPoseRemoteDataSource {
    fun getImageAnalysis(
        file: MultipartBody.Part,
        onSuccess: (kakaoPoseResultList: List<KakaoPoseResponse>) -> Unit,
        onFailure: () -> Unit
    )
}