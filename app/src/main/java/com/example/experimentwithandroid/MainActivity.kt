package com.example.experimentwithandroid

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.experimentwithandroid.databinding.ActivityMainBinding
import com.example.experimentwithandroid.utility.ClippedShadowView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var testString: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val clippedShadowView = ClippedShadowView(binding.target.context)
//
//        binding.target.outlineProvider = null
//        binding.parent.overlay.add(clippedShadowView)
//        clippedShadowView.layout(0, 0, binding.parent.width, binding.parent.height)
//        clippedShadowView.update(
//            binding.target.left,
//            binding.target.top,
//            binding.target.right,
//            binding.target.bottom,
//            resources.getDimension(R.dimen.default_10dp),
//            binding.target.elevation
//        )

        setSupportActionBar(binding.toolbar)
        testKotlinScope()
    }

    private fun testKotlinScope() {
        testString = "hello"
        testString?.let { string ->
            Handler(mainLooper).postDelayed({
                Log.e("hello 1st", testString+"")
            }, 100)
            testString = null
            Log.e("hello 2nd", string+"")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

}