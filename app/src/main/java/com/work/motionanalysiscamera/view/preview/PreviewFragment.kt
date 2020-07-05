package com.work.motionanalysiscamera.view.preview

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.recyclerview.widget.PagerSnapHelper
import com.work.motionanalysiscamera.R
import com.work.motionanalysiscamera.adapter.PictureAdapter
import com.work.motionanalysiscamera.base.BaseFragment
import com.work.motionanalysiscamera.databinding.FragmentPreviewBinding
import com.work.motionanalysiscamera.util.UtilFile

class PreviewFragment :
    BaseFragment<FragmentPreviewBinding>(FragmentPreviewBinding::bind, R.layout.fragment_preview) {

    private val pictureAdapter by lazy { PictureAdapter() }

    override fun onBackPressed() {
        Navigation.findNavController(requireView())
            .navigate(R.id.action_fragment_preview_to_fragment_camera)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvPicture.run {
            this.adapter = pictureAdapter
            PagerSnapHelper().attachToRecyclerView(this)
        }

        UtilFile.getDirectoryFile().listFiles()?.toList()?.sortedDescending()?.let {
            pictureAdapter.addAll(it)
        }
    }


}