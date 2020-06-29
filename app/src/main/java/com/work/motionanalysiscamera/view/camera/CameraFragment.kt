package com.work.motionanalysiscamera.view.camera

import android.os.Bundle
import android.view.View
import com.work.motionanalysiscamera.R
import com.work.motionanalysiscamera.base.BaseFragment
import com.work.motionanalysiscamera.databinding.FragmentCameraBinding

class CameraFragment : BaseFragment<FragmentCameraBinding>(
    FragmentCameraBinding::bind,
    R.layout.fragment_camera
) {
    override fun onBackPressed() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}