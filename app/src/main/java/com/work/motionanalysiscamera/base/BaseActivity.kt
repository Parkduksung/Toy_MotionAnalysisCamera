package com.work.motionanalysiscamera.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<B : ViewBinding>(
    private val inflate: (LayoutInflater) -> B,
    @LayoutRes val layoutId: Int
) :
    AppCompatActivity() {

    protected lateinit var binding: B
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)
    }
}