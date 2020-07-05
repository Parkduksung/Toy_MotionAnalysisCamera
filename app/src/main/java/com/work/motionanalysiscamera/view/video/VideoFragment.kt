package com.work.motionanalysiscamera.view.video

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import com.work.motionanalysiscamera.R
import com.work.motionanalysiscamera.base.BaseFragment
import com.work.motionanalysiscamera.databinding.FragmentVideoBinding

class VideoFragment :
    BaseFragment<FragmentVideoBinding>(FragmentVideoBinding::bind, R.layout.fragment_video) {

    override fun onBackPressed() {
        Navigation.findNavController(requireView())
            .navigate(R.id.action_fragment_video_to_fragment_camera)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}