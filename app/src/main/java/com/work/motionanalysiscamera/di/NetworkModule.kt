package com.work.motionanalysiscamera.di

import com.work.motionanalysiscamera.network.api.KakaoApi
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val KAKAO_POSE_URL = "https://cv-api.kakaobrain.com/"
private const val KAKAO_VISION_URL = "https://kapi.kakao.com/"

val networkModule = module {

    single<KakaoApi.Pose> {
        get<Retrofit> { parametersOf(KAKAO_POSE_URL) }
            .create(KakaoApi.Pose::class.java)
    }

    single<KakaoApi.Vision> {
        get<Retrofit> { parametersOf(KAKAO_VISION_URL) }
            .create(KakaoApi.Vision::class.java)
    }

    factory<Retrofit> { (url: String) ->
        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(get())
            .build()
    }
    single<Converter.Factory> { GsonConverterFactory.create() }
}