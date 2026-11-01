# Laporan Implementasi Komponen — Pemrograman Mobile Pertemuan 9

**Nama Proyek:** QRKota Pekanbaru  
**Nama:** Manda Syahyati Kamal  
**Kelas:** 3 TIA  
**Mata Kuliah:** Pemrograman Mobile  
**Materi:** ChipGroup, `bg_header_wave`, GridLayout, Tutorial Screen

## 1. Tujuan

Menerapkan empat komponen pertemuan 9 ke proyek QRKota Pekanbaru sesuai tugas dosen, sambil menjaga tata letak dan fungsi proyek sebelumnya. Semua implementasi menggunakan Kotlin dan XML di Android Studio.

## 2. ChipGroup (Filter Kategori)

**File:** `app/src/main/res/layout/fragment_search.xml`, `SearchFragment.kt`.

Di halaman Cari, tombol pilihan kategori sebelumnya diganti menjadi Chip dalam ChipGroup. Kategori yang ditampilkan tetap **Semua, Taman, Pendidikan, Museum** sehingga susunan dan fungsi pencarian tidak berubah. `singleSelection="true"` memastikan hanya satu pilihan aktif. Ketika chip dipilih, `setOnCheckedStateChangeListener` memperbarui variabel `category` dan memanggil `render()` agar ListView menampilkan fasilitas sesuai kategori.

**Bukti pengujian yang perlu diambil:** screenshot tab Cari ketika chip Museum aktif, dengan daftar hasil yang telah difilter.

## 3. Background Vector `bg_header_wave`

**File:** `app/src/main/res/drawable/bg_header_wave.xml`, `fragment_home.xml`.

File `bg_header_wave.xml` berupa vector drawable yang menggunakan `pathData` berbentuk gelombang. Drawable dipasang sebagai latar halus (alpha 0,16) di belakang judul sapaan pada Beranda. Warna dan teks asli tetap dipertahankan.

**Bukti pengujian:** screenshot bagian header Beranda yang menampilkan gelombang tipis di belakang teks.

## 4. GridLayout (Menu Fitur Utama)

**File:** `app/src/main/res/layout/fragment_home.xml`.

Empat kartu fitur **Scan QR Code, Lihat Lokasi, Riwayat Scan, Favorit** awalnya disusun dalam dua LinearLayout horizontal. Sekarang keempatnya menjadi anak langsung `androidx.gridlayout.widget.GridLayout` dengan `columnCount="2"` dan `layout_columnWeight="1"`. Layout tetap berupa 2 baris × 2 kolom untuk mempertahankan tampilan yang sudah dibuat. Setiap kartu dapat diklik dan membuka menu yang sama seperti sebelumnya.

**Bukti pengujian:** screenshot Beranda dengan empat kartu yang tersusun rapi.

## 5. Tutorial Screen (ViewPager2 dan DotsIndicator)

**File:** `TutorialActivity.kt`, `TutorialFragmentAdapter.kt`, `Tutorial1Fragment.kt`, `Tutorial2Fragment.kt`, `Tutorial3Fragment.kt`, `activity_tutorial.xml`, dan tiga `fragment_tutorial_*.xml`.

Pada menu titik tiga di Toolbar, terdapat menu **Tutorial QRKota**. Menu ini membuka `TutorialActivity`, yang menampilkan 3 Fragment menggunakan `ViewPager2` dan `FragmentStateAdapter`:

1. **Kenali QRKota Pekanbaru:** pengenalan aplikasi.
2. **Cari dan Pilih Fasilitas:** petunjuk pencarian dan kategori.
3. **Simpan dan Lihat Kembali:** penjelasan fitur favorit dan riwayat.

Di bawah layar terdapat **DotsIndicator**. Kode `binding.dotIndicator.attachTo(binding.tutorialViewPager)` menghubungkan titik dengan posisi halaman.

**Bukti pengujian:** screenshot tutorial halaman 1 dan halaman 3 untuk menunjukkan perubahan posisi indikator.

## 6. Materi Sebelumnya yang Masih Digunakan

| Pertemuan | Contoh pada proyek |
|---|---|
| 1–2 | Dasar Kotlin: class, fungsi, variabel, kondisi, list |
| 3 | Toast untuk pemberitahuan |
| 4 | CardView untuk kotak tampilan login dan daftar fasilitas |
| 5 | Toolbar, Option Menu, CoordinatorLayout, WebView |
| 6 | Login/Logout, SharedPreferences, Splash Screen, Fragment, AlertDialog |
| 7 | BottomNavigationView, ListView, ArrayAdapter, SimpleAdapter, Custom Adapter, Glide |
| 8 | Tidak ada materi tambahan menurut keterangan dosen |
| 9 | ChipGroup, vector drawable wave, GridLayout, ViewPager2, DotsIndicator |

Library scanner ZXing dan penggunaan izin kamera dari proyek awal dihapus karena belum dipelajari. Fitur scan sekarang berupa simulasi input kode menggunakan AlertDialog dan Toast. Pendaftaran akun juga dihapus supaya login tetap sederhana dan tidak menyimpan password pengguna.

## 7. Hasil dan Kesimpulan

Keempat komponen pertemuan 9 telah dimasukkan dalam source code QRKota Pekanbaru. Warna, logo, gambar fasilitas, daftar dan tata letak lama tetap dipakai. Perubahan penempatan filter dari Button menjadi Chip serta perubahan teks tombol scanner merupakan penyesuaian fungsi yang diperlukan.

**Catatan pengujian:** bagian hasil uji aplikasi dan screenshot perlu dilengkapi setelah menjalankan build pada emulator atau HP Android. Pemeriksaan statis tidak sama dengan uji aplikasi berjalan.
