package com.work.motionanalysiscamera.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.work.motionanalysiscamera.R
import java.io.File

class PictureAdapter : RecyclerView.Adapter<PictureViewHolder>() {

    private val pictureList = mutableListOf<File>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder =
        PictureViewHolder(parent, R.layout.item_picture)

    override fun getItemCount(): Int =
        pictureList.size

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        holder.bind(pictureList[position])
    }

    fun addAll(picList: List<File>) {
        pictureList.addAll(picList)
        notifyDataSetChanged()
    }


}