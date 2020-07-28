package com.work.motionanalysiscamera.ext

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.*

fun ViewPager2.setPageToPageTransformer(context: Context, offset: Int, pageMargin: Int) {

    val toPxOffset = offset.dpToPx(context.resources.displayMetrics)
    val toPxPageMargin = pageMargin.dpToPx(context.resources.displayMetrics)
    offscreenPageLimit = 2

    when (orientation) {
        ORIENTATION_HORIZONTAL -> {
            setPadding(
                toPxOffset,
                0,
                toPxOffset,
                0
            )
            setPageTransformer(MarginPageTransformer(toPxPageMargin))
        }
        ORIENTATION_VERTICAL -> {
            setPadding(
                0,
                toPxOffset,
                0,
                toPxOffset
            )
            setPageTransformer(MarginPageTransformer(toPxPageMargin))
        }
    }
}

fun ViewPager2.scrollMode(type: Int) {
    when (type) {
        OVER_SCROLL_NEVER -> {
            val childView = this.getChildAt(0)
            if (childView is RecyclerView) {
                childView.setOverScrollMode(OVER_SCROLL_NEVER)
            }
        }
        OVER_SCROLL_ALWAYS -> {
            overScrollMode = View.OVER_SCROLL_ALWAYS
        }
        OVER_SCROLL_IF_CONTENT_SCROLLS -> {
            overScrollMode = View.OVER_SCROLL_IF_CONTENT_SCROLLS
        }
    }
}
