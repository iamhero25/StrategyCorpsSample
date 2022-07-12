package com.strategycorps.medlite.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import com.strategycorps.medlite.R
import com.strategycorps.medlite.databinding.ActivityLoginBinding
import com.strategycorps.medlite.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initActions()

        navigateToActivity()
    }

    private fun navigateToActivity(){
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, WalkThroughActivity::class.java))
            finish()
        }, 2000)
    }

    private fun initActions(){
        binding.ivAppLogo.startAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_increase_anim))
        binding.ivAppLogoBg.startAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_decrease_anim))

    }
}