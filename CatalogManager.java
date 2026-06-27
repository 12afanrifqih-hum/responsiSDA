import java.util.*;

public class CatalogManager {
    
    private TreeMap<Integer, Song> catalog = new TreeMap<>();

    public void addSong(Song song) {
        catalog.put(song.getId(), song);
    }

    public void removeSong(int id) {
        catalog.remove(id);
    }

    public List<Song> getAllSongs() {
        return new ArrayList<>(catalog.values());
    }

    public Song findSongById(int id) {
        return catalog.get(id);
    }

    public void displayCatalog() {
        System.out.println("\n===== MUSIC CATALOG =====");
        if (catalog.isEmpty()) {
            System.out.println("Catalog is empty.");
            return;
        }
        for (Song s : catalog.values()) {
            System.out.println(s.getId() + ". " + s.getTitle() + " - " + s.getArtist() + " (" + s.getAlbum() + ")");
        }
    }

    public void runCatalogMenu(Scanner scanner) {
        System.out.println("\n1. View Catalog");
        System.out.println("2. Add Song");
        System.out.println("3. Delete Song");
        System.out.print("Choice: ");
        
        int pilih;
        try {
            pilih = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a number.");
            return; 
        }

        if (pilih == 1) {
            displayCatalog();
        } else if (pilih == 2) {
            System.out.print("ID: ");
            int id;
            try {
                id = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("[!] Failed: ID must be a number.");
                return;
            }
            
            if (catalog.containsKey(id)) {
                System.out.println("[!] Failed: ID " + id + " is already registered to another song.");
            } else {
                System.out.print("Title: ");
                String title = scanner.nextLine();
                System.out.print("Artist: ");
                String artist = scanner.nextLine();
                System.out.print("Album: ");
                String album = scanner.nextLine();
                System.out.print("Genre: ");
                String genre = scanner.nextLine();
                
                addSong(new Song(id, title, artist, album, genre));
                System.out.println("Song added successfully!");
            }
        } else if (pilih == 3) {
            System.out.print("Enter ID: ");
            int id;
            try {
                id = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("[!] Failed: ID must be a number.");
                return;
            }
            
            if (!catalog.containsKey(id)) {
                System.out.println("[!] Failed: ID " + id + " not found.");
            } else {
                removeSong(id);
                System.out.println("Song deleted successfully.");
            }
        } else {
            System.out.println("Option not available!");
        }
    }
}