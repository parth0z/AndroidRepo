package com.example.experimentwithandroid.bottomsheet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.experimentwithandroid.R
import com.example.experimentwithandroid.utility.dpToPx

class TodaysBottomSheet : Fragment() {

    private lateinit var todayWidgetCv: CardView
    private lateinit var todayWidget: TodayBottomSheetWidget

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_todays_bottom_sheet, container, false)
        todayWidget = view.findViewById(R.id.todayWidget)
        todayWidgetCv = view.findViewById(R.id.todayWidgetCv)
        Log.e("current radius", "${todayWidgetCv.radius} ")
        return view
    }

    fun startAnimation(slideOffset: Float) {
        todayWidget.showAnimation(slideOffset)
        if (slideOffset in 0.4..0.5) {
            todayWidgetCv.radius =
                requireContext().dpToPx((16 - ((slideOffset - 0.4) * 100) * 1.78).toInt()).toFloat()
        } else if (slideOffset < 0.4) {
            todayWidgetCv.radius = resources.getDimensionPixelOffset(R.dimen.default_16dp).toFloat()
        } else if (slideOffset > 0.5) {
            todayWidgetCv.radius = 0f
        }

        Log.e("test slide", "$slideOffset    ${todayWidgetCv.radius} ")
    }
}