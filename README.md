# QRKota Pekanbaru — Proyek Pemrograman Mobile (sampai Pertemuan 9)

Proyek ini berasal dari **MobileManda.zip** dan tetap mempertahankan tampilan utama Soft Pink + Baby Blue.

## Jalankan
1. Ekstrak ZIP; buka folder **MobileManda** di Android Studio.
2. Pastikan Android Studio memakai **JDK 17** dan Android SDK 35 terpasang.
3. Lakukan **Sync Project with Gradle Files**; tunggu pengunduhan dependency.
4. Pilih **Build > Rebuild Project**, lalu **Run**.
5. Login latihan: username `manda`, password `12345`. Teks petunjuk akun sengaja tidak ditampilkan di layar login.

## Bagian pertemuan 9 yang diwajibkan dosen

- **ChipGroup**: tab *Cari*, chip kategori Semua/Taman/Pendidikan/Museum pada `fragment_search.xml`, listener di `SearchFragment.kt`.
- **bg_header_wave**: `res/drawable/bg_header_wave.xml`, dipasang sebagai latar transparan di area sapaan `fragment_home.xml`.
- **GridLayout**: susunan empat kartu fitur Beranda 2 kolom tanpa mengubah posisi kartu, file `fragment_home.xml`.
- **Tutorial Screen**: menu titik tiga (kanan atas) > **Tutorial QRKota**; 3 halaman ViewPager2, indikator titik DotsIndicator.

## Batas materi dan perubahan

- P3 Toast; P4 CardView; P5 Toolbar, Option Menu, CoordinatorLayout, WebView; P6 Splash, Login, SharedPreferences, Fragment; P7 BottomNavigation, ListView, Custom Adapter, Glide; P9 ChipGroup, vector wave, GridLayout, ViewPager2, DotsIndicator.
- **Dihapus:** library kamera ZXing dan permission CAMERA; scanner kamera diganti **input kode fasilitas** agar tidak memakai materi di luar modul. Tombol pada halaman Scan tetap dengan warna dan ukuran yang sama, tetapi keterangannya disesuaikan.
- **Dihapus:** registrasi lokal dan penyimpanan password yang tidak diperlukan untuk praktikum Login pertemuan 6; sesuai permintaan sebelumnya, teks `Belum punya akun? Daftar` dan `Akun demo` tidak muncul.
- **Ditambah:** WebView untuk tombol **Lihat Lokasi** sesuai materi pertemuan 5 dan Glide untuk Custom Adapter pertemuan 7.
- **Diperbaiki:** struktur `app/build.gradle.kts` dan distribusi Gradle menjadi 8.9 untuk AGP 8.7.3.
- Tidak ada Firebase, Compose, Retrofit, Room, atau library pemindai kamera.

## Pengujian
Ikuti daftar di `LAPORAN_PERTEMUAN_9.md`. Proyek telah diperiksa secara statis pada file XML dan referensi kode, tetapi **belum di-build/di-run pada Android SDK di lingkungan penyusunan**. Jalankan pada Android Studio lokal untuk verifikasi akhir.
