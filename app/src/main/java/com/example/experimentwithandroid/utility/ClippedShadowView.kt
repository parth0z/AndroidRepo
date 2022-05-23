package com.example.experimentwithandroid.utility

import android.content.Context
import android.graphics.Canvas
import android.graphics.Outline
import android.graphics.Path
import android.graphics.Region
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider

/**
 * @author partho
 * @since 15/5/22
 */
class ClippedShadowView(context: Context) : ViewGroup(context) {
    private val outline = Outline().apply { alpha = 1.0F }
    private val clipPath = Path()
    private val shadowView = View(context)

    init {
        addView(shadowView)
        shadowView.outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(view: View, outline: Outline) {
                outline.set(this@ClippedShadowView.outline)
            }
        }
    }

    fun update(left: Int, top: Int, right: Int, bottom: Int, radius: Float, elevation: Float) {
        clipPath.reset()
        clipPath.addRoundRect(
            left.toFloat(),
            top.toFloat(),
            right.toFloat(),
            bottom.toFloat(),
            radius,
            radius,
            Path.Direction.CW
        )

        outline.setRoundRect(0, 0, right - left, bottom - top, radius)

        shadowView.layout(left, top, right, bottom)
        shadowView.elevation = elevation
        shadowView.invalidate()
    }

    override fun dispatchDraw(canvas: Canvas) {
        if (!canvas.isHardwareAccelerated) return

        val count = canvas.save()
        clipOutPath(canvas, clipPath)
        super.dispatchDraw(canvas)
        canvas.restoreToCount(count)
    }

    private fun clipOutPath(canvas: Canvas, path: Path) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            canvas.clipOutPath(path)
        } else {
            @Suppress("DEPRECATION")
            canvas.clipPath(path, Region.Op.DIFFERENCE)
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {}
}