package com.work.motionanalysiscamera.di

import com.work.motionanalysiscamera.view.camera.presenter.CameraContract
import com.work.motionanalysiscamera.view.camera.presenter.CameraPresenter
import org.koin.dsl.module

val presenterModule = module {
    factory<CameraContract.Presenter> { (view: CameraContract.View) -> CameraPresenter(view) }
}