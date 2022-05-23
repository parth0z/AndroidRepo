package com.example.experimentwithandroid.bottomsheet

import android.animation.ObjectAnimator
import android.content.Context
import android.media.Image
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.experimentwithandroid.R
import com.example.experimentwithandroid.utility.dpToPx
import org.w3c.dom.Text

/**
 * @author partho
 * @since 18/5/22
 */
class TodayBottomSheetWidget constructor(context: Context, attrs: AttributeSet?) :
    RelativeLayout(context, attrs) {

    private var amountTv: TextView
    private var viewTv: TextView
    private var eyeCloseIv: ImageView
    private var eyeOpenIv: ImageView
    private var amountTvWidth: Float? = null
    private var earningsRl: RelativeLayout
    private var todayEarningTitle: TextView
    private var moreTv: TextView
    private var questFlagIv: ImageView
    private var quickActionRl: RelativeLayout

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.widget_today_earning, this, true)
        amountTv = view.findViewById(R.id.amountTv)
        viewTv = view.findViewById(R.id.viewTv)
        eyeCloseIv = view.findViewById(R.id.eyeCloseIv)
        eyeOpenIv = view.findViewById(R.id.eyeOpenIv)
        earningsRl = view.findViewById(R.id.earningsRl)
        todayEarningTitle = view.findViewById(R.id.todayEarningTitle)
        moreTv = view.findViewById(R.id.moreTv)
        questFlagIv = view.findViewById(R.id.questFlagIv)
        quickActionRl = view.findViewById(R.id.quickActionRl)

        viewTv.setOnClickListener {
            showAmount()
        }
        eyeCloseIv.setOnClickListener {
            hideAmount()
        }

        eyeOpenIv.setOnClickListener {
            showAmount()
        }

    }

    private fun hideAmount() {
        ObjectAnimator.ofFloat(amountTv, "translationY", 80F).apply {
            duration = 300
            start()
        }
        eyeCloseIv.visibility = GONE
        eyeOpenIv.visibility = VISIBLE
        ObjectAnimator.ofFloat(eyeOpenIv, "translationX", amountTvWidth!!, 0F).apply {
            duration = 300
            start()
        }
        viewTv.visibility = VISIBLE
        ObjectAnimator.ofFloat(viewTv, "translationY", 80F, 0F).apply {
            duration = 300
            start()
        }
    }

    private fun showAmount() {
        if (amountTvWidth == null) {
            amountTvWidth = amountTv.width.toFloat()
        }

        ObjectAnimator.ofFloat(viewTv, "translationY", 80F).apply {
            duration = 300
            start()
        }
        eyeCloseIv.visibility = VISIBLE
        ObjectAnimator.ofFloat(eyeCloseIv, "translationX", -amountTvWidth!!, 0F).apply {
            duration = 300
            start()
        }
        amountTv.visibility = VISIBLE
        ObjectAnimator.ofFloat(amountTv, "translationY", 80F, 0F).apply {
            duration = 300
            start()
        }
        eyeOpenIv.visibility = INVISIBLE
        ObjectAnimator.ofFloat(eyeOpenIv, "translationX", amountTvWidth!!).apply {
            duration = 300
            start()
        }
    }

    fun showAnimation(slideOffset: Float) {
        val factor = (1 - ((slideOffset - 0.3)*2.5)).toFloat()
        earningsRl.alpha = factor
        todayEarningTitle.alpha = (1 - ((slideOffset - 0.3)*3.3)).toFloat()
        questFlagIv.alpha = factor
        moreTv.alpha = factor
        quickActionRl.alpha = (0 + (slideOffset-0.4)*1.67).toFloat()

        //earningsRl.translationY = earningsRl.y+(context.dpToPx(20)*(1 - slideOffset))
    }

}