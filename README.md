# Aplikasi Resep Masakan

Aplikasi ini adalah program berbasis Java yang memungkinkan pengguna untuk mengelola resep masakan. Dengan menggunakan antarmuka grafis (GUI) berbasis Swing, pengguna dapat menambahkan, melihat, mengedit, dan menghapus resep dengan mudah. Resep yang telah dimasukkan akan disimpan secara lokal dalam file sehingga dapat diakses kembali pada sesi berikutnya.

## Fitur Utama

1. **Tambah Resep**
   - Pengguna dapat menambahkan resep baru dengan mengisi nama, bahan-bahan, langkah-langkah, dan memilih gambar yang terkait dengan resep tersebut.

2. **Lihat Resep**
   - Resep yang dipilih dari tabel akan ditampilkan dalam dialog yang memuat informasi bahan, langkah-langkah, dan gambar terkait (jika ada).

3. **Edit Resep**
   - Pengguna dapat mengubah informasi resep yang telah disimpan sebelumnya, termasuk mengganti nama, bahan-bahan, langkah-langkah, atau gambar.

4. **Hapus Resep**
   - Pengguna dapat menghapus resep yang tidak diperlukan lagi dari daftar.

5. **Penyimpanan Data**
   - Semua resep yang ditambahkan akan disimpan dalam file `recipes.dat` menggunakan mekanisme serialization Java. Data akan dimuat secara otomatis saat aplikasi dijalankan kembali.

## Struktur Program

### 1. **Kelas Utama: `Main`**
   - Mengelola antarmuka pengguna dan interaksi dengan data.
   - Fitur antarmuka menggunakan `JFrame`, `JTable`, dan komponen Swing lainnya.
   - Menangani aksi tombol untuk menambah, melihat, mengedit, dan menghapus resep.
   - Memuat dan menyimpan data resep ke file lokal.

### 2. **Kelas `Recipe`**
   - Mewakili entitas resep dengan atribut:
     - `name` (nama resep)
     - `ingredients` (bahan-bahan)
     - `steps` (langkah-langkah)
     - `imagePath` (path gambar resep, opsional)
   - Diimplementasikan sebagai kelas `Serializable` agar dapat disimpan dalam file.

## Teknologi yang Digunakan
- **Bahasa Pemrograman:** Java
- **Antarmuka Grafis:** Java Swing
- **Penyimpanan Data:** Serialization menggunakan file `recipes.dat`

## Cara Menggunakan

1. **Jalankan Program:**
   - Kompilasi dan jalankan file utama `Main.java`.

2. **Tambah Resep Baru:**
   - Klik tombol "Tambah Resep" dan isi formulir yang tersedia.
   - Tekan tombol "Simpan" untuk menambahkan resep.

3. **Melihat Resep:**
   - Pilih salah satu resep dari tabel dan klik tombol "Lihat Resep".

4. **Edit Resep:**
   - Pilih resep dari tabel, lalu klik tombol "Edit Resep".
   - Lakukan perubahan pada formulir, lalu klik "Simpan Perubahan".

5. **Hapus Resep:**
   - Pilih resep dari tabel, lalu klik tombol "Hapus Resep".

## Catatan
- Jika gambar tidak dipilih, maka gambar tidak akan ditampilkan dalam dialog "Lihat Resep".
- Nama resep tidak boleh kosong saat menambah atau mengedit resep.
- Semua data resep disimpan dalam file lokal (`recipes.dat`), sehingga pengguna harus memastikan file tersebut tidak dihapus jika ingin mempertahankan data.

## Pengembangan Lebih Lanjut
Fitur yang dapat ditambahkan di masa depan:
- Ekspor dan impor resep ke/dari file format lain (misalnya, CSV atau JSON).
- Pencarian resep berdasarkan nama atau bahan.
- Dukungan untuk kategori atau jenis masakan.

## Penulis
Program ini dibuat untuk memberikan solusi pengelolaan resep masakan secara digital dan praktis dengan antarmuka yang ramah pengguna.

