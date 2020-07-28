package com.work.motionanalysiscamera.view.preview

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.view.View.OVER_SCROLL_NEVER
import android.widget.Toast
import androidx.navigation.Navigation
import com.work.motionanalysiscamera.R
import com.work.motionanalysiscamera.adapter.PictureAdapter
import com.work.motionanalysiscamera.base.BaseFragment
import com.work.motionanalysiscamera.databinding.FragmentPreviewBinding
import com.work.motionanalysiscamera.ext.scrollMode
import com.work.motionanalysiscamera.ext.setPageToPageTransformer
import com.work.motionanalysiscamera.util.UtilFile
import com.work.motionanalysiscamera.view.preview.presenter.PreviewContract
import org.koin.android.ext.android.get
import org.koin.core.parameter.parametersOf

class PreviewFragment :
    BaseFragment<FragmentPreviewBinding>(FragmentPreviewBinding::bind, R.layout.fragment_preview),
    PreviewContract.View, View.OnClickListener {

    private val pictureAdapter by lazy { PictureAdapter() }

    private lateinit var presenter: PreviewContract.Presenter

    override fun onBackPressed() {
        Navigation.findNavController(requireView())
            .navigate(R.id.action_fragment_preview_to_fragment_camera)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ib_delete -> {
                UtilFile.getDirectoryFile().listFiles()?.toList()?.sortedDescending()?.let {
                    if (it[CURRENT_POSITION].delete()) {
                        Toast.makeText(requireContext(), "지워짐", Toast.LENGTH_SHORT).show()
                        pictureAdapter.deleteItem(it[CURRENT_POSITION])
                        pictureAdapter.notifyItemRemoved(CURRENT_POSITION)
                    }
                }
            }

            R.id.ib_back -> {
                onBackPressed()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = get { parametersOf(this) }

// 원래 기본 방식
//        binding.vpPicture.apply {
//            offscreenPageLimit = 2  // make sure left/right item is rendered
//        }
//        // increase this offset to show more of left/right
//        val offsetPx = 50.dpToPx(resources.displayMetrics)
//        binding.vpPicture.setPadding(offsetPx, 0, offsetPx, 0)
//
//        // increase this offset to increase distance between 2 items
//        val pageMarginPx = 30.dpToPx(resources.displayMetrics)
//        val marginTransformer = MarginPageTransformer(pageMarginPx)
//        binding.vpPicture.setPageTransformer(marginTransformer)

        binding.vpPicture.run {
            adapter = pictureAdapter
            scrollMode(OVER_SCROLL_NEVER)
            setPageToPageTransformer(
                requireContext(),
                VIEWPAGER_OFFSET,
                VIEWPAGER_PAGE_MARGIN
            )
        }

        binding.ibBack.setOnClickListener(this)
        binding.ibDelete.setOnClickListener(this)

        UtilFile.getDirectoryFile().listFiles()?.toList()?.sortedDescending()?.let {
            pictureAdapter.addAll(it)
            presenter.faceDetection(it[0])
        }
    }

    override fun showImage(bitmap: Bitmap) {

    }

    companion object {
        private const val VIEWPAGER_OFFSET = 50
        private const val VIEWPAGER_PAGE_MARGIN = 30
        private var CURRENT_POSITION = 0

    }
}