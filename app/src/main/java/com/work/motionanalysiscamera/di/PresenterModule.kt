package com.work.motionanalysiscamera.di

import com.work.motionanalysiscamera.view.camera.presenter.CameraContract
import com.work.motionanalysiscamera.view.camera.presenter.CameraPresenter
import com.work.motionanalysiscamera.view.preview.presenter.PreviewContract
import com.work.motionanalysiscamera.view.preview.presenter.PreviewPresenter
import org.koin.dsl.module

val presenterModule = module {
    factory<CameraContract.Presenter> { (view: CameraContract.View) ->
        CameraPresenter(view)
    }

    factory<PreviewContract.Presenter> { (view: PreviewContract.View) ->
        PreviewPresenter(view, get())
    }

}