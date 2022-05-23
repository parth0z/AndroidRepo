package com.example.experimentwithandroid.bottomsheet

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.experimentwithandroid.R
import com.example.experimentwithandroid.utility.dpToPx
import com.example.experimentwithandroid.utility.getCompactColor
import com.example.experimentwithandroid.utility.showIfViewIsGone
import com.google.android.material.bottomsheet.BottomSheetBehavior


class HostActivity : AppCompatActivity() {

    private lateinit var mBottomSheetBehavior: BottomSheetBehavior<*>
    private var statusBarHeight = 0
    private lateinit var dummyStatusBar: View
    private lateinit var todaysBottomSheet: TodaysBottomSheet
    private lateinit var fragmentContainer: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)
        initView()
        changeStatusBar(true)
        todaysBottomSheet = TodaysBottomSheet()
        fragmentContainer = findViewById(R.id.fragmentContainer)
        getStatusHeight()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, todaysBottomSheet).commit()
        mBottomSheetBehavior = BottomSheetBehavior.from(fragmentContainer)
        mBottomSheetBehavior.isGestureInsetBottomIgnored = true
        mBottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED -> {

                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {

                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                if (slideOffset >= 0.9) {
                    changeStatusBar(false)
                    dummyStatusBar.setBackgroundColor(getCompactColor(R.color.white))
                } else {
                    changeStatusBar(true)
                    dummyStatusBar.setBackgroundColor(getCompactColor(R.color.green))
                }
                todaysBottomSheet.startAnimation(slideOffset)
            }

        })
    }

    private fun initView() {
        dummyStatusBar = findViewById(R.id.dummyStatusBar)
    }


    private fun getStatusHeight() {
        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.containerLayout)
        ) { _: View?, insets: WindowInsetsCompat ->
            statusBarHeight = insets.systemWindowInsetTop
            dummyStatusBar.layoutParams.height = statusBarHeight
            dummyStatusBar.showIfViewIsGone()
            (fragmentContainer.layoutParams as CoordinatorLayout.LayoutParams).topMargin =
                statusBarHeight
            mBottomSheetBehavior.peekHeight = dpToPx(80)+statusBarHeight
            insets.consumeSystemWindowInsets()
        }
    }

    private fun changeStatusBar(isTransparent: Boolean) {

        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        setWindowFlag(false)
        window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)

        window.decorView.systemUiVisibility = if (isTransparent) {
            window.decorView.systemUiVisibility or WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        } else {
            window.decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }

    private fun setWindowFlag(on: Boolean) {
        val winParams = window.attributes
        if (on) {
            winParams.flags = winParams.flags or WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        } else {
            winParams.flags =
                winParams.flags and WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS.inv()
        }
        window.attributes = winParams
    }
}