# Petunjuk dan penjelasan proyek QRKota Pekanbaru

## Struktur program

- `SplashScreenActivity.kt`: halaman pembuka dengan `lifecycleScope`, lalu memeriksa `isLogin` dari SharedPreferences.
- `LoginActivity.kt`: login latihan sederhana dengan `AlertDialog`, hanya menyimpan status dan username.
- `MainActivity.kt`: toolbar, Option Menu, BottomNavigationView dan pergantian Fragment.
- `HomeFragment.kt`: daftar fasilitas populer menggunakan Custom Adapter dan 4 kartu fitur di dalam GridLayout.
- `SearchFragment.kt`: mencari fasilitas berdasarkan teks dan ChipGroup.
- `ScanFragment.kt`: input kode fasilitas melalui AlertDialog, tanpa kamera/library di luar modul.
- `HistoryFragment.kt`: riwayat kode yang diproses, memakai ArrayAdapter.
- `FavoritesFragment.kt`: fasilitas yang ditandai dan disimpan di SharedPreferences.
- `FacilityDetailActivity.kt`: detail fasilitas, favorit, serta tombol Lihat Lokasi.
- `MapWebViewActivity.kt`: WebView dan Toolbar untuk lokasi.
- `TutorialActivity.kt`, `Tutorial1Fragment.kt` s.d. `Tutorial3Fragment.kt`, `TutorialFragmentAdapter.kt`: tutorial 3 halaman dan DotsIndicator.
- `data/Facility.kt`, `FacilityRepository.kt`, `FacilityAdapter.kt`: model data lokal, data fasilitas, dan Custom Adapter dengan Glide.

## Langkah uji

1. Jalankan aplikasi, periksa splash dan login (`manda` / `12345`).
2. Pada Beranda, periksa empat kartu **Scan QR Code, Lihat Lokasi, Riwayat Scan, Favorit** tetap pada 2 baris/2 kolom (sekarang GridLayout).
3. Lihat efek vector `bg_header_wave` di balik sapaan beranda.
4. Pilih tab **Cari**, ganti chip antara Semua, Taman, Pendidikan, Museum; ListView harus mengikuti kategori.
5. Tekan item fasilitas, tambahkan Favorit, lalu cek tab Favorit.
6. Tekan **Lihat Lokasi** pada detail; WebView memerlukan internet.
7. Pada Scan, masukkan `taman-kota`; hasil bisa dibuka di Detail dan tercatat di Riwayat.
8. Tekan menu titik tiga di Toolbar > **Tutorial QRKota**, geser sampai halaman ke-3, perhatikan dots.
9. Logout dari Profil atau menu Toolbar, buka ulang aplikasi dan pastikan diminta login.

## Penting

Tampilan asli dipertahankan sejauh mungkin; perubahan teks tombol scan diperlukan agar fitur tidak mengaku menggunakan kamera. Bila diperlukan scan kamera sungguhan, itu memerlukan materi atau library tambahan yang belum diizinkan pada tugas ini.
