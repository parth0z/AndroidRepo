package com.example.experimentwithandroid.utility

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

/**
 * @author partho
 * @since 21/5/22
 */

fun Context.dpToPx(dp: Int): Int {
    return (dp * (resources.displayMetrics.densityDpi / 160f)).toInt()
}

fun View.showIfViewIsGone() {
    if (visibility == View.GONE) {
        visibility = View.VISIBLE
    }
}

fun View.hideIfViewIsVisible() {
    if (visibility == View.VISIBLE) {
        visibility = View.GONE
    }
}

fun Context.getCompactDrawable(@DrawableRes drawableId: Int): Drawable? {
    return ContextCompat.getDrawable(this, drawableId)
}

fun Context.getCompactColor(@ColorRes colorId: Int): Int {
    return ContextCompat.getColor(this, colorId)
}
