package com.work.motionanalysiscamera.network.model.vision.face


import com.google.gson.annotations.SerializedName

data class FaceDetectionResponse(
    @SerializedName("result")
    val result: Result
)