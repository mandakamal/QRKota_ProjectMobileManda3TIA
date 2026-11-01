package com.qrkota.pekanbaru

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.qrkota.pekanbaru.data.FacilityRepository

/** Simulasi scan berbasis kode teks, tanpa library kamera di luar modul. */
class ScanFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_scan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<Button>(R.id.btnStartScan).setOnClickListener {
            val input = EditText(requireContext())
            input.hint = "Contoh: taman-kota"
            input.setSingleLine(true)
            AlertDialog.Builder(requireContext())
                .setTitle("Masukkan Kode QR")
                .setMessage("Ketik kode fasilitas yang ada pada QR untuk simulasi scan.")
                .setView(input)
                .setPositiveButton("Cari") { _, _ -> cekKode(input.text.toString()) }
                .setNegativeButton("Batal", null)
                .show()
        }

        view.findViewById<Button>(R.id.btnDemoScan).setOnClickListener {
            val contoh = FacilityRepository.all.first()
            cekKode(contoh.id)
        }
    }

    private fun cekKode(kode: String) {
        val fasilitas = FacilityRepository.all.firstOrNull {
            it.id.equals(kode.trim(), true) || it.name.equals(kode.trim(), true)
        }
        if (fasilitas == null) {
            Toast.makeText(requireContext(), "Kode fasilitas tidak ditemukan", Toast.LENGTH_SHORT).show()
            return
        }
        (activity as? MainActivity)?.saveScanHistory(fasilitas.name)
        AlertDialog.Builder(requireContext())
            .setTitle("Kode ditemukan")
            .setMessage("Fasilitas: ${fasilitas.name}\n\nLihat detail fasilitas?")
            .setPositiveButton("Lihat Detail") { _, _ ->
                (activity as? MainActivity)?.showFacilityDetail(fasilitas.id)
            }
            .setNegativeButton("Tutup", null)
            .show()
    }
}
