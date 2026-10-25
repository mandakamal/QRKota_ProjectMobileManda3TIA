package com.qrkota.pekanbaru

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.qrkota.pekanbaru.data.FacilityRepository

/** Menampilkan detail satu fasilitas berdasarkan ID dari Intent. */
class FacilityDetailActivity : AppCompatActivity() {

    private lateinit var facilityId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val toolbar = findViewById<MaterialToolbar>(R.id.detailToolbar)
        toolbar.setNavigationOnClickListener { finish() }

        facilityId = intent.getStringExtra(EXTRA_FACILITY_ID).orEmpty()
        val facility = FacilityRepository.find(facilityId)
        if (facility == null) {
            Toast.makeText(this, "Data fasilitas tidak ditemukan", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        findViewById<ImageView>(R.id.imgDetail).setImageResource(facility.imageRes)
        findViewById<TextView>(R.id.tvDetailName).text = facility.name
        findViewById<TextView>(R.id.tvDetailCategory).text = facility.category
        findViewById<TextView>(R.id.tvDetailDescription).text = facility.shortDescription
        findViewById<TextView>(R.id.tvDetailAddress).text = facility.address
        findViewById<TextView>(R.id.tvDetailHours).text = facility.operationalHours
        findViewById<TextView>(R.id.tvDetailFacilities).text = facility.facilities

        val favoriteButton = findViewById<Button>(R.id.btnDetailFavorite)
        updateFavoriteButton(favoriteButton)
        favoriteButton.setOnClickListener {
            val isNowFavorite = toggleFavorite()
            updateFavoriteButton(favoriteButton)
            Toast.makeText(
                this,
                if (isNowFavorite) "Ditambahkan ke favorit" else "Dihapus dari favorit",
                Toast.LENGTH_SHORT
            ).show()
        }

        findViewById<Button>(R.id.btnDetailMap).setOnClickListener {
            val query = Uri.encode("${facility.name}, ${facility.address}")
            val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=$query"))
            try {
                startActivity(mapIntent)
            } catch (error: android.content.ActivityNotFoundException) {
                Toast.makeText(this, "Aplikasi peta tidak tersedia", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun toggleFavorite(): Boolean {
        val preferences = getSharedPreferences("QRKotaPrefs", MODE_PRIVATE)
        val ids = (preferences.getStringSet("favoriteIds", emptySet()) ?: emptySet()).toMutableSet()
        val added = if (ids.contains(facilityId)) {
            ids.remove(facilityId)
            false
        } else {
            ids.add(facilityId)
            true
        }
        preferences.edit().putStringSet("favoriteIds", ids).apply()
        return added
    }

    private fun updateFavoriteButton(button: Button) {
        val ids = getSharedPreferences("QRKotaPrefs", MODE_PRIVATE)
            .getStringSet("favoriteIds", emptySet()) ?: emptySet()
        button.text = if (ids.contains(facilityId)) "Hapus Favorit" else "Simpan Favorit"
    }

    companion object {
        const val EXTRA_FACILITY_ID = "facilityId"
    }
}
