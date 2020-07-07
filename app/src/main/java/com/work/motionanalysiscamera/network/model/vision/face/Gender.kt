package com.work.motionanalysiscamera.network.model.vision.face


import com.google.gson.annotations.SerializedName

data class Gender(
    @SerializedName("female")
    val female: Double,
    @SerializedName("male")
    val male: Double
)