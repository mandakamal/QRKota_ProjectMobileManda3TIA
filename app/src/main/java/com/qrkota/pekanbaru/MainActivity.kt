package com.qrkota.pekanbaru

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.qrkota.pekanbaru.data.FacilityRepository

/** Activity utama yang menjadi wadah lima tab Bottom Navigation. */
class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: MaterialToolbar
    private lateinit var bottomNavigation: BottomNavigationView
    private var pendingSearchQuery = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        bottomNavigation = findViewById(R.id.bottomNavigation)
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.app_name)

        // Ketika tab dipilih, ganti Fragment yang ditampilkan di container.
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> showFragment(HomeFragment(), "Beranda")
                R.id.nav_search -> {
                    val fragment = SearchFragment().apply { setInitialQuery(pendingSearchQuery) }
                    showFragment(fragment, "Cari Fasilitas")
                    pendingSearchQuery = ""
                }
                R.id.nav_scan -> showFragment(ScanFragment(), "Scan QR Code")
                R.id.nav_favorites -> showFragment(FavoritesFragment(), "Favorit")
                R.id.nav_profile -> showFragment(ProfileFragment(), "Profil")
                else -> return@setOnItemSelectedListener false
            }
            true
        }

        // Pilih Beranda hanya ketika Activity dibuat pertama kali.
        if (savedInstanceState == null) {
            bottomNavigation.selectedItemId = R.id.nav_home
        }
    }

    private fun showFragment(fragment: Fragment, title: String) {
        supportActionBar?.title = title
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    fun selectTab(menuId: Int) {
        bottomNavigation.selectedItemId = menuId
    }

    fun openSearch(query: String) {
        pendingSearchQuery = query
        selectTab(R.id.nav_search)
    }

    fun openHistory() {
        supportActionBar?.title = "Riwayat Scan"
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, HistoryFragment())
            .addToBackStack("history")
            .commit()
    }

    /** Membuka Activity detail dengan ID fasilitas yang dipilih dari daftar atau hasil QR. */
    fun showFacilityDetail(id: String) {
        if (FacilityRepository.find(id) == null) {
            Toast.makeText(this, "Informasi fasilitas tidak ditemukan", Toast.LENGTH_SHORT).show()
            return
        }
        startActivity(Intent(this, FacilityDetailActivity::class.java).putExtra("facilityId", id))
    }

    /** Menambah atau menghapus ID dari set favorit yang tersimpan secara lokal. */
    fun toggleFavorite(id: String): Boolean {
        val preferences = getSharedPreferences("QRKotaPrefs", MODE_PRIVATE)
        val favoriteIds = (preferences.getStringSet("favoriteIds", emptySet()) ?: emptySet()).toMutableSet()
        val nowFavorite = if (favoriteIds.contains(id)) {
            favoriteIds.remove(id)
            false
        } else {
            favoriteIds.add(id)
            true
        }
        preferences.edit().putStringSet("favoriteIds", favoriteIds).apply()
        return nowFavorite
    }

    /** Menyimpan riwayat scan dengan waktu agar tampil di halaman Riwayat. */
    fun saveScanHistory(label: String) {
        val preferences = getSharedPreferences("QRKotaPrefs", MODE_PRIVATE)
        val records = (preferences.getStringSet("scanHistory", emptySet()) ?: emptySet()).toMutableSet()
        records.add("${System.currentTimeMillis()}|$label")
        preferences.edit().putStringSet("scanHistory", records).apply()
    }

    private fun confirmLogout() {
        AlertDialog.Builder(this)
            .setTitle("Konfirmasi Logout")
            .setMessage("Apakah kamu yakin ingin keluar dari akun QRKota Pekanbaru?")
            .setPositiveButton("Ya, keluar") { _, _ -> logout() }
            .setNegativeButton("Tidak", null)
            .show()
    }

    fun logout() {
        getSharedPreferences("QRKotaPrefs", MODE_PRIVATE).edit()
            .putBoolean("isLogin", false)
            .remove("username")
            .apply()

        // Membersihkan back stack agar tombol Back tidak kembali ke MainActivity.
        val intent = Intent(this, LoginActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menu.add(0, MENU_LOGOUT, 0, "Logout")
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == MENU_LOGOUT) {
            confirmLogout()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    @Deprecated("Deprecated in Android API; retained for predictable tab/back behavior in this course project")
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
            supportActionBar?.title = getString(R.string.app_name)
        } else if (bottomNavigation.selectedItemId != R.id.nav_home) {
            bottomNavigation.selectedItemId = R.id.nav_home
        } else {
            super.onBackPressed()
        }
    }

    companion object {
        private const val MENU_LOGOUT = 101
    }
}
