# MUZYC APP - Sistem Manajemen Playlist Musik

Tugas Responsi Akhir — Mata Kuliah Struktur Data dan Algoritma  
Tema 2: Hierarchical Management and Linear Data Flow Systems

---

## Anggota Kelompok

 [Briellyan Sekartaji] [L0125006] 
 [M. Afan Rifqi Hanifa] [L0135086] 
 [Pandu Jatu Pradityo] [L0125111] 

---

## Deskripsi Proyek

MUZYC APP adalah aplikasi manajemen musik berbasis Command Line Interface (CLI) yang mensimulasikan cara kerja platform streaming. Program ini mengelola katalog lagu secara hierarkis berdasarkan Genre, Artis, dan Album, mendukung antrean putar menggunakan prinsip FIFO, serta menyimpan riwayat pemutaran yang memungkinkan pengguna kembali ke lagu sebelumnya menggunakan prinsip LIFO.

---

## Fitur Program

**1. Music Catalog**  
Menampilkan seluruh lagu dalam tampilan pohon tiga tingkat: Genre → Artis → Album → Lagu. Pengguna bisa menambahkan lagu baru ke katalog atau menghapus lagu berdasarkan ID. Setiap kali lagu dihapus, ID seluruh lagu akan diperbarui ulang secara otomatis.

**2. Search Song**  
Mencari lagu menggunakan kata kunci yang dicocokkan terhadap judul, artis, album, dan genre secara bersamaan. Pencarian bersifat case-insensitive sehingga pengguna tidak perlu memperhatikan huruf kapital.

**3. Play Queue (Antrean Putar)**  
Pengguna dapat menambahkan lagu ke antrean berdasarkan ID dan melihat daftar lagu yang sedang mengantri. Saat Play Song dijalankan, lagu paling depan diambil dari antrean dan otomatis masuk ke riwayat.

**4. History & Previous Song**  
Menyimpan riwayat lagu yang telah diputar. Fitur Previous Song mengambil lagu terakhir dari riwayat dan mengembalikannya ke bagian paling depan antrean agar diputar berikutnya.

---

## Tampilan Program

**Menu Utama**

[main](./media/main.png)]

**Katalog Hierarkis**

[katalog](./media/katalog.png)]

**Play Queue dan History**

[queue](./media/queue.png)
[history](./media/history.png)

---

## Struktur Data dan Algoritma

### TreeMap Bersarang — Katalog Hierarkis

Digunakan di `CatalogManager.java` untuk menyimpan katalog dengan struktur tiga tingkat: Genre → Artis → Album → List<Song>.

```
TreeMap<String, TreeMap<String, TreeMap<String, List<Song>>>> hierarchy
```

TreeMap dipilih karena secara otomatis mengurutkan key secara alfabetis, sehingga tampilan katalog selalu rapi tanpa perlu sorting tambahan. Operasi pencarian dan penyisipan berjalan dalam O(log n). Tiga level TreeMap bersarang merepresentasikan hubungan hierarkis secara langsung sesuai spesifikasi tema.

| Operasi | Kompleksitas |
|---------|-------------|
| addSong() | O(log G + log A + log B) |
| removeSong() | O(N) |
| findSongById() | O(1) |
| displayCatalog() | O(N) |

### ArrayList — Flat Catalog

Selain TreeMap, katalog juga disimpan dalam sebuah ArrayList datar (`List<Song> catalog`) untuk memudahkan akses lagu berdasarkan ID secara langsung dalam O(1). ArrayList dipakai karena mendukung akses berbasis indeks yang tidak dimiliki TreeMap.

### LinkedList sebagai Queue (FIFO)

Digunakan di `Queue.java` untuk antrean putar. LinkedList dipilih karena mendukung `addLast()` dan `removeFirst()` dalam O(1) tanpa pergeseran elemen. LinkedList juga mendukung `addFirst()` yang dibutuhkan saat lagu dikembalikan ke depan antrean pada fitur Previous Song.

| Operasi | Kompleksitas |
|---------|-------------|
| enqueue() | O(1) |
| dequeue() | O(1) |
| addFirst() | O(1) |
| displayQueue() | O(N) |

### Stack — Riwayat Pemutaran (LIFO)

Digunakan di `HistoryManager.java`. Stack dipilih karena secara semantis paling sesuai untuk riwayat pemutaran — lagu yang paling baru diputar selalu berada di paling atas dan bisa diambil kembali dengan `pop()` pada fitur Previous Song.

| Operasi | Kompleksitas |
|---------|-------------|
| addHistory() / push() | O(1) |
| getPreviousSong() / pop() | O(1) |
| showHistory() | O(N) |

### Linear Search — Pencarian Lagu

Digunakan di `SearchManager.java`. Pencarian dilakukan secara sekuensial terhadap seluruh katalog dengan kompleksitas O(N). Linear search dipilih karena pencarian berbasis substring (`.contains()`) tidak bisa diindeks — kata kunci bisa cocok di bagian mana pun dari string judul, artis, album, atau genre.

---

## Struktur File

```
muzyc-app/
├── Song.java             # Model data lagu
├── CatalogManager.java   # Manajemen katalog hierarkis
├── Queue.java            # Antrean putar (FIFO)
├── HistoryManager.java   # Riwayat putar (LIFO)
├── SearchManager.java    # Pencarian lagu
└── Main.java             # Entry point dan menu utama
```

---

## Cara Menjalankan Program

**Prasyarat:** Pastikan Java Development Kit (JDK) versi 8 atau lebih baru sudah terinstal.

```bash
java -version
```

**Langkah-langkah:**

1. Clone atau ekstrak repositori ini, lalu masuk ke direktori proyek.

```bash
git clone https://github.com/[username]/muzyc-app.git
cd muzyc-app
```

2. Kompilasi semua file Java.

```bash
javac *.java
```

3. Jalankan program.

```bash
java Main
```

---

## Library yang Digunakan

Program ini dibangun menggunakan Pure Java tanpa library eksternal. Semua struktur data berasal dari `java.util.*` yang sudah tersedia bawaan JDK, sehingga tidak diperlukan instalasi atau konfigurasi tambahan.

- `TreeMap` — katalog hierarkis dengan urutan alfabetis otomatis
- `ArrayList` — flat list katalog untuk akses berdasarkan ID
- `LinkedList` — implementasi antrean putar (FIFO)
- `Stack` — implementasi riwayat putar (LIFO)
- `Scanner` — membaca input pengguna dari terminal
