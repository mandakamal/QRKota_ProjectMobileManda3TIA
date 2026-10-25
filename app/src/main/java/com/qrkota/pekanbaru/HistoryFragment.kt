package com.qrkota.pekanbaru

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/** Menampilkan riwayat scan lokal, termasuk hasil simulasi dan QR yang dibaca kamera. */
class HistoryFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = render(view)

    override fun onResume() {
        super.onResume()
        view?.let { render(it) }
    }

    private fun render(view: View) {
        val preferences = requireContext().getSharedPreferences("QRKotaPrefs", android.content.Context.MODE_PRIVATE)
        val entries = (preferences.getStringSet("scanHistory", emptySet()) ?: emptySet()).toList()
            .sortedByDescending { it.substringBefore('|').toLongOrNull() ?: 0L }

        val formatter = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale("id", "ID"))
        val rows = entries.map { entry ->
            val parts = entry.split("|", limit = 2)
            val timestamp = parts.firstOrNull()?.toLongOrNull()
            val facilityName = parts.getOrNull(1) ?: "QR Code"
            val timeText = timestamp?.let { formatter.format(Date(it)) } ?: "Waktu tidak tersedia"
            "$facilityName\n$timeText"
        }

        val list = view.findViewById<ListView>(R.id.listHistory)
        val displayRows = if (rows.isEmpty()) {
            listOf("Belum ada riwayat scan.\nGunakan fitur Scan QR untuk memulai.")
        } else rows
        list.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, displayRows)
    }
}
