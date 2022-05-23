package com.example.experimentwithandroid.service

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.experimentwithandroid.R
import java.lang.Exception

class ServiceInitializerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_initializer)
        val startBtn = findViewById<Button>(R.id.startBtn)
        startBtn.setOnClickListener {
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    startForegroundService(
                        Intent(this, TestService::class.java)
                    )
                } else {
                    startService(
                        Intent(this, TestService::class.java)
                    )
                }

            } catch (exception: Exception) {
                Log.e("service initialization", "$exception")
            }
        }
    }
}