package com.qrkota.pekanbaru.data

import com.qrkota.pekanbaru.R

/** Sumber data lokal contoh agar Beranda, Cari, Favorit, dan Detail memakai data yang sama. */
object FacilityRepository {
    val all: List<Facility> = listOf(
        Facility("taman-kota", "Taman Kota", "Taman", "Ruang terbuka hijau untuk rekreasi dan aktivitas masyarakat.", "Pekanbaru, Riau", R.drawable.facility_park, "Setiap hari (jam dapat berubah)", "Area terbuka dan tempat bersantai"),
        Facility("museum-sang-nila", "Museum Sang Nila Utama", "Museum", "Wisata edukasi sejarah dan budaya Riau.", "Pekanbaru, Riau", R.drawable.facility_museum, "Periksa jadwal resmi sebelum berkunjung", "Koleksi sejarah dan budaya"),
        Facility("jembatan-siak", "Jembatan Siak", "Landmark", "Ikon Kota Pekanbaru di kawasan Sungai Siak.", "Kawasan Sungai Siak, Pekanbaru", R.drawable.facility_bridge, "Area publik; akses mengikuti kondisi setempat", "Pemandangan kawasan sungai"),
        Facility("perpustakaan-soeman", "Perpustakaan Soeman H.S.", "Pendidikan", "Tempat membaca, belajar, dan mencari informasi.", "Pekanbaru, Riau", R.drawable.facility_park, "Periksa jadwal operasional resmi", "Koleksi bacaan dan area belajar"),
        Facility("masjid-annur", "Masjid Raya An-Nur", "Tempat Ibadah", "Salah satu landmark dan tempat ibadah terkenal di Pekanbaru.", "Pekanbaru, Riau", R.drawable.facility_museum, "Menyesuaikan jadwal ibadah", "Tempat ibadah dan area sekitar")
    )
    fun find(id: String): Facility? = all.find { it.id == id }
}
