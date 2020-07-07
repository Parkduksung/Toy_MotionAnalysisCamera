package com.work.motionanalysiscamera.di

import com.work.motionanalysiscamera.data.source.remote.kakao.pose.KakaoPoseRemoteDataSource
import com.work.motionanalysiscamera.data.source.remote.kakao.pose.KakaoPoseRemoteDataSourceImpl
import com.work.motionanalysiscamera.data.source.remote.kakao.vision.KakaoVisionRemoteDataSource
import com.work.motionanalysiscamera.data.source.remote.kakao.vision.KakaoVisionRemoteDataSourceImpl
import org.koin.dsl.module

val sourceModule = module {
    single<KakaoPoseRemoteDataSource> { KakaoPoseRemoteDataSourceImpl(get()) }
    single<KakaoVisionRemoteDataSource> { KakaoVisionRemoteDataSourceImpl(get()) }
}