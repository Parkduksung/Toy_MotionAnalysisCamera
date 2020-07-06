package com.work.motionanalysiscamera.di

import com.work.motionanalysiscamera.network.api.KakaoApi
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val KAKAO_POSE_URL = "https://cv-api.kakaobrain.com/"

val networkModule = module {

    single<KakaoApi.Pose> {
        get<Retrofit> { parametersOf(KAKAO_POSE_URL) }
            .create(KakaoApi.Pose::class.java)
    }

    factory<Retrofit> { (url: String) ->
        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(get())
            .build()
    }
    single<Converter.Factory> { GsonConverterFactory.create() }
}