package com.work.motionanalysiscamera.network.model.vision.face


import com.google.gson.annotations.SerializedName

data class Face(
    @SerializedName("class_idx")
    val classIdx: Int,
    @SerializedName("facial_attributes")
    val facialAttributes: FacialAttributes,
    @SerializedName("facial_points")
    val facialPoints: FacialPoints,
    @SerializedName("h")
    val h: Double,
    @SerializedName("pitch")
    val pitch: Double,
    @SerializedName("roll")
    val roll: Double,
    @SerializedName("score")
    val score: Double,
    @SerializedName("w")
    val w: Double,
    @SerializedName("x")
    val x: Double,
    @SerializedName("y")
    val y: Double,
    @SerializedName("yaw")
    val yaw: Double
)