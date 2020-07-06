package com.work.motionanalysiscamera.di

import com.work.motionanalysiscamera.data.repository.kakao.KakaoPoseRepository
import com.work.motionanalysiscamera.data.repository.kakao.KakaoPoseRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<KakaoPoseRepository> { KakaoPoseRepositoryImpl(get()) }
}