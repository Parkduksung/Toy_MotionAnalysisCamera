package com.work.motionanalysiscamera.network.model.vision.face


import com.google.gson.annotations.SerializedName
import com.work.motionanalysiscamera.network.model.vision.face.Face

data class Result(
    @SerializedName("faces")
    val faces: List<Face>,
    @SerializedName("height")
    val height: Int,
    @SerializedName("width")
    val width: Int
)