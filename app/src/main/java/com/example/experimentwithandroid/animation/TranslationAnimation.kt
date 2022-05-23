package com.example.experimentwithandroid.animation

import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.experimentwithandroid.databinding.ActivityTranslationAnimationBinding

class TranslationAnimation : AppCompatActivity() {

    private lateinit var binding: ActivityTranslationAnimationBinding
    private val viewTvWidth: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTranslationAnimationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.helloWorld.alpha = 0f
        binding.helloWorld.visibility = VISIBLE
        binding.helloWorld.animate().alpha(1f).setDuration(5000).start()

//        ObjectAnimator.ofFloat(binding.viewTv, "translationY", 80F).apply {
//            duration = 500
//            start()
//        }
//        ObjectAnimator.ofFloat(binding.eyeOpenIv, "translationX", 0f,1f).apply {
//            duration = 600
//            start()
//        }

//        Handler(Looper.getMainLooper()).postDelayed({
//            ObjectAnimator.ofFloat(binding.viewTv, "translationY", 0F).apply {
//                duration = 500
//                start()
//            }
//            ObjectAnimator.ofFloat(binding.eyeOpenIv, "translationX", 0f).apply {
//                duration = 500
//                start()
//            }
//        }, 1000)
    }


}