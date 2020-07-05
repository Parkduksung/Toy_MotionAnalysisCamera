package com.work.motionanalysiscamera.util

import com.work.motionanalysiscamera.App
import com.work.motionanalysiscamera.R
import java.io.File

object UtilFile {
    fun getDirectoryFile(): File {
        val mediaDir = App.instance.externalMediaDirs.firstOrNull()?.let {
            File(it, App.instance.resources.getString(R.string.app_name)).apply { mkdir() }
        }
        return if (mediaDir != null && mediaDir.exists()) mediaDir else App.instance.filesDir
    }
}