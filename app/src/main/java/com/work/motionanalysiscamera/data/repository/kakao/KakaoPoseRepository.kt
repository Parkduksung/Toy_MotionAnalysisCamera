package com.work.motionanalysiscamera.data.repository.kakao

import com.work.motionanalysiscamera.network.model.KakaoPoseResponse
import okhttp3.MultipartBody
import java.net.URL

interface KakaoPoseRepository {

    fun getImageAnalysis(
        file: MultipartBody.Part,
        onSuccess: (kakaoPoseResultList: List<KakaoPoseResponse>) -> Unit,
        onFailure: () -> Unit
    )
}