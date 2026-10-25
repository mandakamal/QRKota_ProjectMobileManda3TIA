package com.qrkota.pekanbaru

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.qrkota.pekanbaru.databinding.ActivityLoginBinding

/** Login demo: akun lokal, bukan autentikasi server untuk aplikasi produksi. */
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val demoUsername = "manda"
    private val demoPassword = "12345"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val preferences = getSharedPreferences("QRKotaPrefs", MODE_PRIVATE)

        // Membuka layar pendaftaran saat tautan Daftar ditekan.
        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

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
                    val demoAccountMatches = usernameOrEmail.equals(demoUsername, ignoreCase = true) && password == demoPassword
                    val registeredEmail = preferences.getString("registeredEmail", null)
                    val registeredPassword = preferences.getString("registeredPassword", null)
                    val registeredAccountMatches = registeredEmail != null &&
                        usernameOrEmail.equals(registeredEmail, ignoreCase = true) &&
                        password == registeredPassword

                    if (demoAccountMatches || registeredAccountMatches) {
                        val displayName = if (demoAccountMatches) {
                            demoUsername
                        } else {
                            preferences.getString("registeredName", "Pengguna") ?: "Pengguna"
                        }

                        preferences.edit()
                            .putBoolean("isLogin", true)
                            .putString("username", displayName)
                            .apply()

                        // Hapus layar login dari back stack setelah login berhasil.
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        showMessage(
                            "Login gagal",
                            "Username/email atau password yang kamu masukkan salah. Periksa kembali, lalu coba lagi."
                        )
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
