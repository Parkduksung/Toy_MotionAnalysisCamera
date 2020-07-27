package com.work.motionanalysiscamera.view.preview

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.Navigation
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

        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin)
        val pagerWidth = resources.getDimensionPixelOffset(R.dimen.pagerWidth)
        val screenWidth = resources.displayMetrics.widthPixels
        val offsetPx = screenWidth - pageMarginPx - pagerWidth

        binding.vpPicture.run {
            adapter = pictureAdapter
            setPageTransformer { page, position ->
                page.translationX = position * -offsetPx
            }
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

        private var CURRENT_POSITION = 0

    }
}