# QRKota Pekanbaru — Android Kotlin Project

Aplikasi prototipe Smart City untuk menemukan informasi fasilitas publik di Pekanbaru.

## Mulai cepat
1. Buka folder ini dari Android Studio melalui **Open**.
2. Tunggu Gradle Sync selesai (koneksi internet diperlukan untuk dependency scanner QR).
3. Jalankan pada emulator atau HP Android.
4. Login demo: `manda` / `12345`.
5. Untuk penjelasan setiap file dan langkah pengujian, buka `PETUNJUK_DAN_PENJELASAN.md`.

## Fitur
- Splash screen dan pemeriksaan status login.
- Login/registrasi demonstrasi dengan pesan validasi melalui dialog.
- Bottom Navigation: Beranda, Cari, Scan QR, Favorit, Profil.
- ListView + SimpleAdapter di halaman Cari.
- Custom Adapter pada daftar fasilitas.
- Detail fasilitas, pencarian/filter, favorit, riwayat scan, dan tombol lokasi.
- Scanner QR kamera dengan tombol simulasi sebagai alternatif pengujian.
- Palet warna Soft Pink + Baby Blue sesuai identitas desain QRKota.

## Catatan
Data fasilitas dan akun bersifat lokal untuk tugas kuliah, bukan layanan produksi. QR yang diuji sebaiknya berisi ID fasilitas seperti `taman-kota`. Periksa dokumen panduan untuk batasan dan troubleshooting.
