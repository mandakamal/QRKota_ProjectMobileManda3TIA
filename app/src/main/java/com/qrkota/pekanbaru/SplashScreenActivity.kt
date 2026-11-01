package com.qrkota.pekanbaru

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.qrkota.pekanbaru.databinding.ActivitySplashScreenBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/** Splash menunggu sebentar lalu membaca status login dari SharedPreferences. */
class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setBackgroundDrawableResource(R.color.ice_blue)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            delay(1800)
            val pref = getSharedPreferences("QRKotaPrefs", MODE_PRIVATE)
            val tujuan = if (pref.getBoolean("isLogin", false)) {
                MainActivity::class.java
            } else {
                LoginActivity::class.java
            }
            startActivity(Intent(this@SplashScreenActivity, tujuan))
            finish()
        }
    }
}
