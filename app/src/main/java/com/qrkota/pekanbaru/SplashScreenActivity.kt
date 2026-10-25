package com.qrkota.pekanbaru

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.qrkota.pekanbaru.databinding.ActivitySplashScreenBinding

/** Layar pembuka: menampilkan identitas aplikasi lalu mengecek status login. */
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    private val handler = Handler(Looper.getMainLooper())

    // Runnable disimpan agar bisa dibatalkan jika Activity ditutup lebih awal.
    private val openNextScreen = Runnable {
        val preferences = getSharedPreferences("QRKotaPrefs", MODE_PRIVATE)
        val alreadyLogin = preferences.getBoolean("isLogin", false)
        val nextActivity = if (alreadyLogin) {
            MainActivity::class.java
        } else {
            LoginActivity::class.java
        }

        startActivity(Intent(this, nextActivity))
        finish() // Mencegah tombol Back kembali ke splash.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Menghindari latar hitam sebelum layout pertama digambar.
        window.setBackgroundDrawableResource(R.color.ice_blue)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Beri waktu agar logo dan nama aplikasi terlihat.
        handler.postDelayed(openNextScreen, 1800L)
    }

    override fun onDestroy() {
        handler.removeCallbacks(openNextScreen)
        super.onDestroy()
    }
}
