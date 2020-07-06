package com.work.motionanalysiscamera.network.model


import com.google.gson.annotations.SerializedName

data class KakaoPoseResponse(
    @SerializedName("area")
    val area: Double,
    @SerializedName("bbox")
    val bbox: List<Double>,
    @SerializedName("category_id")
    val categoryId: Int,
    @SerializedName("keypoints")
    val keypoints: List<Double>,
    @SerializedName("score")
    val score: Double
)