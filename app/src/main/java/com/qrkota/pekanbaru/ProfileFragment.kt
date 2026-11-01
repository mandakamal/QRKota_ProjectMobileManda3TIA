package com.qrkota.pekanbaru

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment

/** Profil pengguna serta pintasan Riwayat, Favorit, dan Logout. */
class ProfileFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_profile, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val prefs = requireContext().getSharedPreferences("QRKotaPrefs", android.content.Context.MODE_PRIVATE)
        val username = prefs.getString("username", "Manda") ?: "Manda"
        view.findViewById<TextView>(R.id.tvProfileName).text = username.replaceFirstChar { it.uppercase() }
        val email = if (username.equals("manda", true)) "manda3tia@gmail.com" else (prefs.getString("registeredEmail", "Pengguna QRKota") ?: "Pengguna QRKota")
        view.findViewById<TextView>(R.id.tvProfileEmail).text = email
        view.findViewById<TextView>(R.id.btnProfileHistory).setOnClickListener { (activity as? MainActivity)?.openHistory() }
        view.findViewById<TextView>(R.id.btnProfileFavorites).setOnClickListener { (activity as? MainActivity)?.selectTab(R.id.nav_favorites) }
        view.findViewById<Button>(R.id.btnLogout).setOnClickListener {
            AlertDialog.Builder(requireContext()).setTitle("Keluar dari QRKota?").setMessage("Kamu akan kembali ke halaman login.")
                .setPositiveButton("Keluar") { _, _ -> (activity as? MainActivity)?.logout() }
                .setNegativeButton("Batal", null).show()
        }
    }
}
