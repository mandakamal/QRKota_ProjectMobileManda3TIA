# QRKota Pekanbaru — Panduan Projek dan Penjelasan Kode

## 1. Tujuan projek
QRKota Pekanbaru adalah prototipe aplikasi Android bertema Smart City untuk membantu pengguna mengenali fasilitas publik melalui pencarian, detail informasi, daftar favorit, riwayat, dan QR Code.

## 2. Identitas desain dari Figma
- Soft Pink: `#F4B8D0`
- Baby Blue: `#A9D4FF`
- Ice Blue: `#EAF4FF`
- Blush White: `#FFF0F5`
- White: `#FFFFFF`
- Navy: `#2C3E50`
- Font isi: Inter yang dibundel pada `res/font`.
- Figma mengarahkan Poppins untuk judul. Jika file font Poppins resmi tersedia dari aset desain, letakkan pada `res/font` dan gunakan untuk judul; projek saat ini memakai Inter untuk menjaga konsistensi dan tidak bergantung pada unduhan font.
- Lima menu utama: Beranda, Cari, Scan QR, Favorit, Profil. Riwayat diakses dari Beranda atau Profil.

## 3. Akun demonstrasi
- Username: `manda`
- Password: `12345`
- Pengguna juga dapat membuat akun lokal dari halaman Daftar.
- Autentikasi dan penyimpanan akun hanya untuk demonstrasi perkuliahan. Password disimpan lokal untuk contoh alur, bukan praktik keamanan untuk aplikasi produksi.

## 4. Cara membuka
1. Ekstrak ZIP ke folder baru.
2. Buka Android Studio → **Open** → pilih folder root yang berisi `settings.gradle.kts`.
3. Pastikan komputer terhubung internet ketika Gradle pertama kali mengunduh plugin/dependency.
4. Tunggu Gradle Sync selesai.
5. Pilih emulator atau HP Android, lalu klik **Run**.
6. Jika build gagal, buka panel Build dan perbaiki error pertama yang muncul. ZIP ini tidak menyertakan Gradle Wrapper JAR; gunakan Android Studio untuk mengimpor project dan membuat/menyediakan wrapper bila dibutuhkan.

## 5. Peta file dan fungsi

### Activity
- `SplashScreenActivity.kt`: memasang tampilan splash, menunggu sekitar 1,8 detik, membaca `isLogin` dari `QRKotaPrefs`, lalu membuka Login atau Main. Handler dibatalkan ketika Activity dihancurkan.
- `LoginActivity.kt`: membaca input, membedakan kolom kosong dari kredensial salah, menampilkan `AlertDialog` yang jelas, memvalidasi akun demo atau akun lokal, menyimpan status login, dan membuka Main.
- `RegisterActivity.kt`: memvalidasi nama, email, dan panjang password, lalu menyimpan data demonstrasi lokal.
- `MainActivity.kt`: mengelola Toolbar, Bottom Navigation, pergantian Fragment, pencarian dari shortcut, detail fasilitas, favorit, riwayat scan, serta dialog konfirmasi logout.
- `FacilityDetailActivity.kt`: menerima `facilityId` dari Intent dan menampilkan nama, kategori, deskripsi, alamat, jam, fasilitas, tombol peta, dan tombol favorit.

### Fragment
- `HomeFragment.kt`: sapaan pengguna, kolom pencarian, empat shortcut, dan fasilitas populer.
- `SearchFragment.kt`: ListView menggunakan `SimpleAdapter` dan `android.R.layout.simple_list_item_2`; pencarian berdasarkan nama/kategori/alamat; filter kategori; ketuk untuk detail; tekan lama untuk mengubah favorit.
- `ScanFragment.kt`: scanner kamera menggunakan JourneyApps ZXing; tombol simulasi tetap disediakan untuk demo tanpa kamera. QR yang dibaca perlu berisi ID fasilitas (contoh `taman-kota`) atau nama fasilitas persis agar bisa dipetakan ke data aplikasi.
- `FavoritesFragment.kt`: daftar favorit menggunakan `FacilityAdapter` (custom adapter); ketuk item untuk detail, tekan lama untuk menghapus favorit.
- `HistoryFragment.kt`: daftar waktu dan nama hasil scan yang disimpan lokal.
- `ProfileFragment.kt`: nama pengguna, shortcut Riwayat/Favorit, dan logout dengan konfirmasi.

### Data dan adapter
- `data/Facility.kt`: model data satu fasilitas, berisi ID, nama, kategori, deskripsi, alamat, gambar, jam operasional, dan fasilitas.
- `data/FacilityRepository.kt`: sumber data contoh yang dipakai bersama oleh semua halaman. Ini data statis, belum dari API/database.
- `data/FacilityAdapter.kt`: custom adapter yang mengisi `item_facility.xml` dengan gambar, nama, kategori, dan deskripsi. `getView()` adalah bagian yang memetakan data ke tampilan.
- `res/layout/item_facility.xml`: tampilan satu baris fasilitas untuk custom adapter.

### Resource
- `activity_main.xml`: Toolbar, FrameLayout container untuk Fragment, dan BottomNavigationView.
- `bottom_nav_menu.xml`: lima item Bottom Navigation.
- `activity_splash_screen.xml`, `activity_login.xml`, `activity_register.xml`: tampilan Splash/Login/Daftar.
- `fragment_*.xml`: layout masing-masing Fragment.
- `activity_detail.xml`: tampilan detail fasilitas.
- `colors.xml` dan `themes.xml`: palet dan tema aplikasi.
- `AndroidManifest.xml`: deklarasi Activity dan permission kamera. Splash adalah satu-satunya launcher.
- `app/build.gradle.kts`: konfigurasi Android, View Binding, Material Components, ConstraintLayout, dan dependency QR scanner.

## 6. Materi kuliah yang diterapkan
| Materi | Implementasi |
|---|---|
| Activity dan lifecycle | Splash, Login, Register, Main, Detail |
| XML Layout dan View Binding | Login dan Splash menggunakan View Binding |
| Intent | Perpindahan Activity dan pengiriman `facilityId` |
| Toast dan AlertDialog | Pesan singkat, validasi, konfirmasi logout, hasil scan |
| SharedPreferences | Status login, username, akun demo lokal, favorit, riwayat |
| Toolbar | Judul halaman dan menu Logout |
| Fragment | Beranda, Cari, Scan, Favorit, Profil, Riwayat |
| BottomNavigationView | Lima menu utama |
| ListView + SimpleAdapter | Daftar Cari dengan item dua baris Android |
| Custom Adapter | Item fasilitas dengan gambar dan beberapa TextView |
| QR/barcode scanning | JourneyApps ZXing untuk membaca QR menggunakan kamera |

Materi yang tidak termasuk dalam modul yang tersedia tidak diklaim sebagai kewajiban dosen.

## 7. Cara menguji fitur
1. Buka aplikasi; splash harus terlihat, lalu Login jika belum masuk.
2. Login `manda` / `12345`. Input kosong dan kredensial salah harus menampilkan dialog yang menjelaskan masalah.
3. Dari Beranda, coba shortcut Cari, Scan QR, Riwayat Scan, Favorit.
4. Di Cari, ketik nama/kategori/alamat, coba filter, tekan item untuk detail, dan tekan lama untuk mengubah favorit.
5. Di Detail, coba Simpan/Hapus Favorit dan Lihat Lokasi (memerlukan aplikasi peta).
6. Di Scan QR, izinkan kamera dan coba QR yang berisi `taman-kota` atau nama fasilitas persis. Bila kamera tidak tersedia, coba Simulasi Scan.
7. Buka Riwayat; hasil scan/simulasi harus muncul.
8. Di Profil, buka Riwayat/Favorit. Tekan Keluar; pilih Tidak untuk tetap berada di aplikasi atau Ya untuk kembali ke Login.
9. Tutup dan buka aplikasi lagi. Jika belum logout, Splash akan membawa pengguna ke Main setelah jeda singkat.

## 8. Format QR untuk data fasilitas
Untuk demo, isi QR Code dengan salah satu ID berikut atau nama fasilitas yang persis:
- `taman-kota`
- `museum-sang-nila`
- `jembatan-siak`
- `perpustakaan-soeman`
- `masjid-annur`

QR yang berisi data lain tetap terbaca, tetapi aplikasi akan memberi tahu bahwa kodenya belum terhubung dengan data fasilitas.

## 9. Batasan yang harus diketahui
- Data fasilitas dan alamat/jam operasional adalah contoh statis untuk demonstrasi; verifikasi sebelum penggunaan nyata.
- Akun demo dan registrasi disimpan lokal, tanpa server, dan tidak aman untuk produksi.
- QR scanner memakai kamera dan dependency eksternal, sehingga Gradle perlu mengunduh library saat sync pertama kali. Jika dependency tidak terunduh, periksa internet dan panel Build.
- Foto fasilitas pada project adalah aset placeholder desain; ganti dengan foto resmi yang sesuai jika tersedia.
- Figma menyebut Poppins untuk heading dan Inter untuk body. Inter sudah dibundel; Poppins perlu ditambahkan dari aset font resmi jika ingin sama persis.
- Kode dan resource telah diperiksa secara statis; build Android sesungguhnya tetap perlu diverifikasi melalui Android Studio pada komputer pengguna.

## 10. Troubleshooting
- **Splash hitam:** pastikan launcher di Manifest hanya `.SplashScreenActivity`, kemudian Build → Clean Project, Build → Rebuild Project, dan Run. Jika masih hitam, periksa Logcat untuk crash/resource error.
- **Dialog login tidak muncul:** pastikan `LoginActivity.kt` tersimpan, ID XML adalah `btnMasuk`, `etUsername`, dan `etPassword`, lalu Rebuild Project.
- **Binding merah:** pastikan `viewBinding = true`, nama layout sesuai dengan binding, lalu Sync/Rebuild.
- **Scan QR tidak membuka kamera:** pastikan permission kamera diberikan, emulator/perangkat memiliki kamera, dependency ZXing selesai diunduh, dan cek Logcat.
- **Lokasi tidak terbuka:** instal aplikasi peta atau uji di HP yang mendukung URI `geo:`.
