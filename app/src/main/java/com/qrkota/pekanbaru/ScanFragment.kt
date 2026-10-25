package com.qrkota.pekanbaru

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import com.qrkota.pekanbaru.data.FacilityRepository

/** Halaman scan. Tombol utama membuka kamera; tombol kedua untuk uji coba tanpa kamera. */
class ScanFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_scan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<Button>(R.id.btnStartScan).setOnClickListener {
            // IntentIntegrator meminta izin kamera ketika scanner dimulai.
            IntentIntegrator.forSupportFragment(this)
                .setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
                .setPrompt("Arahkan kamera ke QR Code fasilitas QRKota")
                .setBeepEnabled(true)
                .setOrientationLocked(true)
                .initiateScan()
        }

        view.findViewById<Button>(R.id.btnDemoScan).setOnClickListener {
            val sample = FacilityRepository.all.first()
            (activity as? MainActivity)?.saveScanHistory(sample.name)
            AlertDialog.Builder(requireContext())
                .setTitle("Simulasi scan berhasil")
                .setMessage("Contoh QR dikenali sebagai ${sample.name}. Riwayat simulasi sudah disimpan.")
                .setPositiveButton("Lihat Detail") { _, _ ->
                    (activity as? MainActivity)?.showFacilityDetail(sample.id)
                }
                .setNegativeButton("Tutup", null)
                .show()
        }
    }

    @Deprecated("ZXing IntentIntegrator returns its scan result through onActivityResult")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result: IntentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data) ?: return
        val contents = result.contents
        if (contents.isNullOrBlank()) {
            Toast.makeText(requireContext(), "Pemindaian dibatalkan", Toast.LENGTH_SHORT).show()
            return
        }

        // QR dapat berisi ID fasilitas atau nama persis yang tersedia di repository.
        val normalized = contents.trim()
        val facilityIdFromUrl = normalized.substringAfter("facilityId=", normalized).substringAfterLast("/")
        val facility = FacilityRepository.all.firstOrNull {
            it.id.equals(normalized, ignoreCase = true) ||
                it.id.equals(facilityIdFromUrl, ignoreCase = true) ||
                it.name.equals(normalized, ignoreCase = true)
        }

        (activity as? MainActivity)?.saveScanHistory(facility?.name ?: normalized)

        if (facility != null) {
            AlertDialog.Builder(requireContext())
                .setTitle("QR Code berhasil dibaca")
                .setMessage("Fasilitas: ${facility.name}\nKategori: ${facility.category}\n\nBuka detail fasilitas?")
                .setPositiveButton("Buka Detail") { _, _ ->
                    (activity as? MainActivity)?.showFacilityDetail(facility.id)
                }
                .setNegativeButton("Tutup", null)
                .show()
        } else {
            AlertDialog.Builder(requireContext())
                .setTitle("QR Code terbaca")
                .setMessage("Isi QR Code:\n$normalized\n\nKode ini belum terhubung ke data fasilitas QRKota.")
                .setPositiveButton("Mengerti", null)
                .show()
        }
    }
}
