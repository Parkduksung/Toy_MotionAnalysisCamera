package com.work.motionanalysiscamera.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.work.motionanalysiscamera.R
import java.io.File

class PictureViewHolder(parent: ViewGroup, @LayoutRes private val layoutId: Int) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
    ) {

    private val picture: ImageView = itemView.findViewById(R.id.iv_picture)

    fun bind(file: File) {
        Glide.with(itemView).load(file).into(picture)
    }

}