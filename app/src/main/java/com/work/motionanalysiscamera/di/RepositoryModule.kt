package com.work.motionanalysiscamera.di

import com.work.motionanalysiscamera.data.repository.kakao.KakaoPoseRepository
import com.work.motionanalysiscamera.data.repository.kakao.KakaoPoseRepositoryImpl
import com.work.motionanalysiscamera.data.repository.kakao.KakaoVisionRepository
import com.work.motionanalysiscamera.data.repository.kakao.KakaoVisionRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<KakaoPoseRepository> { KakaoPoseRepositoryImpl(get()) }
    single<KakaoVisionRepository> { KakaoVisionRepositoryImpl(get()) }
}