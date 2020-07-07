package com.work.motionanalysiscamera.network.model.vision.face


import com.google.gson.annotations.SerializedName

data class FacialAttributes(
    @SerializedName("age")
    val age: Double,
    @SerializedName("gender")
    val gender: Gender
)