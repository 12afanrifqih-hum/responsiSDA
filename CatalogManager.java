import java.util.*;

public class CatalogManager {
    
    private List<Song> catalog = new ArrayList<>();

    public void addSong(Song song) {
        song.setId(catalog.size() + 1);
        catalog.add(song);
    }

    public void removeSong(int id) {
        int index = id - 1;
        if (index < 0 || index >= catalog.size()) {
            return;
        }
        catalog.remove(index);
        refreshIds();
    }

    public List<Song> getAllSongs() {
        return new ArrayList<>(catalog);
    }

    public Song findSongById(int id) {
        int index = id - 1;
        if (index < 0 || index >= catalog.size()) {
            return null;
        }
        return catalog.get(index);
    }

    private void refreshIds() {
        for (int i = 0; i < catalog.size(); i++) {
            catalog.get(i).setId(i + 1);
        }
    }

    public void displayCatalog() {
        System.out.println("\n===== MUSIC CATALOG =====");
        if (catalog.isEmpty()) {
            System.out.println("Catalog is empty.");
            return;
        }
        for (Song s : catalog) {
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
            System.out.print("Title: ");
            String title = scanner.nextLine();
            System.out.print("Artist: ");
            String artist = scanner.nextLine();
            System.out.print("Album: ");
            String album = scanner.nextLine();
            System.out.print("Genre: ");
            String genre = scanner.nextLine();
            
            addSong(new Song(title, artist, album, genre));
            System.out.println("Song added successfully!");
        } else if (pilih == 3) {
            System.out.print("Enter ID: ");
            int id;
            try {
                id = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("[!] Failed: ID must be a number.");
                return;
            }
            
            if (findSongById(id) == null) {
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