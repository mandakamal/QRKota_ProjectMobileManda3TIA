package com.qrkota.pekanbaru.data

/** Model data satu fasilitas publik; ID dipakai sebagai identitas stabil di seluruh layar. */
data class Facility(
    val id: String,
    val name: String,
    val category: String,
    val shortDescription: String,
    val address: String,
    val imageRes: Int,
    val operationalHours: String = "Periksa informasi pengelola",
    val facilities: String = "Informasi fasilitas tersedia di lokasi"
)
