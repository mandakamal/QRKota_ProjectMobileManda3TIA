package com.qrkota.pekanbaru

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.qrkota.pekanbaru.databinding.ActivityLoginBinding

/** Login sederhana sesuai praktikum pertemuan 6. */
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val demoUsername = "manda"
    private val demoPassword = "12345"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val preferences = getSharedPreferences("QRKotaPrefs", MODE_PRIVATE)

        binding.btnMasuk.setOnClickListener {
            val usernameOrEmail = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString()

            // Setiap kondisi gagal menampilkan dialog agar pengguna tahu penyebabnya.
            when {
                usernameOrEmail.isBlank() && password.isBlank() -> {
                    showMessage("Data belum diisi", "Masukkan username/email dan password terlebih dahulu.")
                }
                usernameOrEmail.isBlank() -> {
                    showMessage("Username kosong", "Kolom username atau email belum diisi.")
                }
                password.isBlank() -> {
                    showMessage("Password kosong", "Kolom password belum diisi.")
                }
                else -> {
                    // Login sederhana untuk praktik lokal, tanpa penyimpanan password.
                    if (usernameOrEmail.equals(demoUsername, true) && password == demoPassword) {
                        preferences.edit()
                            .putBoolean("isLogin", true)
                            .putString("username", demoUsername)
                            .apply()
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        showMessage("Login gagal", "Username atau password salah.")
                    }
                }
            }
        }
    }

    private fun showMessage(title: String, message: String) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Coba lagi", null)
            .show()
    }
}
