package com.work.motionanalysiscamera.view.preview

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.work.motionanalysiscamera.R
import com.work.motionanalysiscamera.adapter.PictureAdapter
import com.work.motionanalysiscamera.base.BaseFragment
import com.work.motionanalysiscamera.databinding.FragmentPreviewBinding
import com.work.motionanalysiscamera.util.UtilFile
import com.work.motionanalysiscamera.view.preview.presenter.PreviewContract
import org.koin.android.ext.android.get
import org.koin.core.parameter.parametersOf

class PreviewFragment :
    BaseFragment<FragmentPreviewBinding>(FragmentPreviewBinding::bind, R.layout.fragment_preview),
    PreviewContract.View {

    private val pictureAdapter by lazy { PictureAdapter() }

    private lateinit var presenter: PreviewContract.Presenter

    override fun onBackPressed() {
        Navigation.findNavController(requireView())
            .navigate(R.id.action_fragment_preview_to_fragment_camera)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = get { parametersOf(this) }

        binding.rvPicture.run {
            this.adapter = pictureAdapter
            PagerSnapHelper().attachToRecyclerView(this)

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    val currentAdapterPosition =
                        (recyclerView.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()

                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        //to-do
                    }
                }
            })
        }

        UtilFile.getDirectoryFile().listFiles()?.toList()?.sortedDescending()?.let {
            pictureAdapter.addAll(it)
            presenter.faceDetection(it[0])
        }
    }

    override fun showImage(bitmap: Bitmap) {

    }
}