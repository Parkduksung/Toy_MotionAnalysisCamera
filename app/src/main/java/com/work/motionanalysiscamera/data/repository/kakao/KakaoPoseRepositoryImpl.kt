package com.work.motionanalysiscamera.data.repository.kakao

import com.work.motionanalysiscamera.data.source.remote.kakao.pose.KakaoPoseRemoteDataSource
import com.work.motionanalysiscamera.network.model.KakaoPoseResponse
import okhttp3.MultipartBody

class KakaoPoseRepositoryImpl(
    private val kakaoPoseRemoteDataSource: KakaoPoseRemoteDataSource
) : KakaoPoseRepository {
    override fun getImageAnalysis(
        file: MultipartBody.Part,
        onSuccess: (kakaoPoseResultList: List<KakaoPoseResponse>) -> Unit,
        onFailure: () -> Unit
    ) {
        kakaoPoseRemoteDataSource.getImageAnalysis(file, onSuccess, onFailure)
    }
}