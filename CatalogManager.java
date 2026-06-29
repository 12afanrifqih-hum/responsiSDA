import java.util.*;

public class CatalogManager {
    
    private TreeMap<String, TreeMap<String, TreeMap<String, List<Song>>>> hierarchy = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    private List<Song> catalog = new ArrayList<>();

    // Time Complexity: O(log G + log A + log B) di mana G = jumlah genre, A = jumlah artis, B = jumlah album
    public void addSong(Song song) {
        song.setId(catalog.size() + 1);
        catalog.add(song);

        hierarchy
            .computeIfAbsent(song.getGenre(), k -> new TreeMap<>(String.CASE_INSENSITIVE_ORDER))
            .computeIfAbsent(song.getArtist(), k -> new TreeMap<>(String.CASE_INSENSITIVE_ORDER))
            .computeIfAbsent(song.getAlbum(), k -> new ArrayList<>()) 
            .add(song);
    }

    // Time Complexity: O(N) karena penghapusan elemen pada ArrayList dan pembaruan ID (refreshIds) membutuhkan pergeseran/iterasi sepanjang N elemen
    public void removeSong(int id) {
        int index = id - 1;
        if (index < 0 || index >= catalog.size()) {
            return;
        }
        
        Song songToRemove = catalog.get(index);
        catalog.remove(index);
        
        TreeMap<String, TreeMap<String, List<Song>>> genreNode = hierarchy.get(songToRemove.getGenre());
        if (genreNode != null) {
            TreeMap<String, List<Song>> artistNode = genreNode.get(songToRemove.getArtist());
            if (artistNode != null) {
                List<Song> albumSongs = artistNode.get(songToRemove.getAlbum());
                if (albumSongs != null) {
                    albumSongs.remove(songToRemove); 

                    if (albumSongs.isEmpty()) artistNode.remove(songToRemove.getAlbum());
                    if (artistNode.isEmpty()) genreNode.remove(songToRemove.getArtist());
                    if (genreNode.isEmpty()) hierarchy.remove(songToRemove.getGenre());
                }
            }
        }

        refreshIds();
    }

    // Time Complexity: O(N) karena menyalin seluruh elemen dari list catalog ke dalam ArrayList baru
    public List<Song> getAllSongs() {
        return new ArrayList<>(catalog);
    }

    // Time Complexity: O(1) karena operasi pencarian elemen berdasarkan indeks pada ArrayList bersifat konstan
    public Song findSongById(int id) {
        int index = id - 1;
        if (index < 0 || index >= catalog.size()) {
            return null;
        }
        return catalog.get(index);
    }

    // Time Complexity: O(N) karena melakukan iterasi sepanjang elemen catalog untuk memperbarui urutan ID
    private void refreshIds() {
        for (int i = 0; i < catalog.size(); i++) {
            catalog.get(i).setId(i + 1);
        }
    }

    // Time Complexity: O(N) karena melakukan traversal ke seluruh struktur pohon hierarki untuk mencetak total N lagu
    public void displayCatalog() {
        System.out.println("\n===========================================================");
        System.out.println("            KATALOG MUSIK (HIERARCHICAL VIEW)");
        System.out.println("===========================================================");
        
        if (hierarchy.isEmpty()) {
            System.out.println("Katalog masih kosong.");
            System.out.println("===========================================================");
            return;
        }

        for (String genre : hierarchy.keySet()) {
            System.out.println("├── [Genre] " + genre);
            TreeMap<String, TreeMap<String, List<Song>>> artists = hierarchy.get(genre);
            
            for (String artist : artists.keySet()) {
                System.out.println("│   ├── [Artis] " + artist);
                TreeMap<String, List<Song>> albums = artists.get(artist);
                
                for (String album : albums.keySet()) {
                    System.out.println("│   │   └── [Album] " + album);
                    List<Song> songs = albums.get(album);
                    
                    for (Song s : songs) {
                        System.out.println("│   │       ├── ID [" + s.getId() + "] " + s.getTitle());
                    }
                }
            }
        }
        System.out.println("===========================================================");
    }

    public void runCatalogMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n--- MANAJEMEN KATALOG ---");
            System.out.println("1. View Catalog (Hierarchical Tree)");
            System.out.println("2. Add Song");
            System.out.println("3. Delete Song");
            System.out.println("4. Back to Main Menu"); 
            System.out.print("Choice: ");
            
            int pilih;
            try {
                pilih = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("[!] Invalid input! Please enter a number.");
                continue; 
            }

            if (pilih == 1) {
                displayCatalog();
            } else if (pilih == 2) {
                System.out.println("\n--- Add New Song ---");
                String title = readNonEmptyString(scanner, "Title");
                String artist = readNonEmptyString(scanner, "Artist");
                String album = readNonEmptyString(scanner, "Album");
                String genre = readNonEmptyString(scanner, "Genre");
                
                addSong(new Song(title, artist, album, genre));
                System.out.println("[✓] Song added successfully!");
            } else if (pilih == 3) {
                System.out.print("Enter ID: ");
                int id;
                try {
                    id = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("[!] Failed: ID must be a number.");
                    continue;
                }
                
                if (findSongById(id) == null) {
                    System.out.println("[!] Failed: ID " + id + " not found.");
                } else {
                    removeSong(id);
                    System.out.println("[✓] Song deleted successfully.");
                }
            } else if (pilih == 4) {
                System.out.println("Returning to Main Menu...");
                break; 
            } else {
                System.out.println("[!] Option not available! Please choose 1 - 4.");
            }
        }
    }

    private String readNonEmptyString(Scanner scanner, String label) {
        while (true) {
            System.out.print(label + ": ");
            String input = scanner.nextLine().trim(); 
            if (!input.isEmpty()) {
                return input; 
            }
            System.out.println("[!] " + label + " cannot be empty. Please try again.");
        }
    }
}