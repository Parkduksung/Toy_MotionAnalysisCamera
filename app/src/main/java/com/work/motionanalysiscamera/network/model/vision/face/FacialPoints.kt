package com.work.motionanalysiscamera.network.model.vision.face


import com.google.gson.annotations.SerializedName

data class FacialPoints(
    @SerializedName("jaw")
    val jaw: List<List<Double>>,
    @SerializedName("left_eye")
    val leftEye: List<List<Double>>,
    @SerializedName("left_eyebrow")
    val leftEyebrow: List<List<Double>>,
    @SerializedName("lip")
    val lip: List<List<Double>>,
    @SerializedName("nose")
    val nose: List<List<Double>>,
    @SerializedName("right_eye")
    val rightEye: List<List<Double>>,
    @SerializedName("right_eyebrow")
    val rightEyebrow: List<List<Double>>
)