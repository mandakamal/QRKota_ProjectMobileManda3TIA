package com.qrkota.pekanbaru

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

/** Registrasi lokal untuk demonstrasi kelas. Data belum dikirim ke server. */
class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val nameInput = findViewById<EditText>(R.id.etFullName)
        val emailInput = findViewById<EditText>(R.id.etRegisterEmail)
        val passwordInput = findViewById<EditText>(R.id.etRegisterPassword)

        findViewById<Button>(R.id.btnDaftar).setOnClickListener {
            val fullName = nameInput.text.toString().trim()
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString()

            when {
                fullName.isBlank() || email.isBlank() || password.isBlank() -> {
                    showMessage("Data belum lengkap", "Semua kolom wajib diisi.")
                }
                !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                    showMessage("Email tidak valid", "Periksa format alamat email yang kamu masukkan.")
                }
                password.length < 5 -> {
                    showMessage("Password terlalu pendek", "Gunakan password minimal 5 karakter untuk demonstrasi ini.")
                }
                else -> {
                    getSharedPreferences("QRKotaPrefs", MODE_PRIVATE).edit()
                        .putString("registeredName", fullName)
                        .putString("registeredEmail", email)
                        .putString("registeredPassword", password)
                        .apply()

                    AlertDialog.Builder(this)
                        .setTitle("Pendaftaran berhasil")
                        .setMessage("Akun tersimpan di perangkat ini. Silakan masuk menggunakan email dan password yang baru dibuat.")
                        .setPositiveButton("Ke halaman login") { _, _ ->
                            startActivity(Intent(this, LoginActivity::class.java))
                            finish()
                        }
                        .setCancelable(false)
                        .show()
                }
            }
        }

        findViewById<TextView>(R.id.tvBackToLogin).setOnClickListener { finish() }
    }

    private fun showMessage(title: String, message: String) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Perbaiki", null)
            .show()
    }
}
