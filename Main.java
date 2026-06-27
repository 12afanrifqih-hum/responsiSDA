import java.util.*;

public class Main {
    
    static Scanner scanner = new Scanner(System.in);
    
    static CatalogManager catalog = new CatalogManager();
    static Queue queue = new Queue();
    static HistoryManager history = new HistoryManager();
    static SearchManager search = new SearchManager();

    public static void main(String[] args) {
        seedData();
        
        while (true) {
            showMenu();
            int choice = readInt("Choose menu: ");
            
            switch (choice) {
                case 1:
                    catalog.runCatalogMenu(scanner);
                    break;
                case 2:
                    searchSong();
                    break;
                case 3:
                    addQueue();
                    break;
                case 4:
                    queue.displayQueue();
                    break;
                case 5:
                    playSong();
                    break;
                case 6:
                    history.showHistory();
                    break;
                case 7:
                    history.undoLastSong();
                    break;
                case 8:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid menu!");
            }
        }
    }

    static void showMenu() {
        System.out.println("\n===== MUZYC APP =====");
        System.out.println("1. Music Catalog");
        System.out.println("2. Search Song");
        System.out.println("3. Add to Queue");
        System.out.println("4. View Queue");
        System.out.println("5. Play Song");
        System.out.println("6. View History");
        System.out.println("7. Undo History");
        System.out.println("8. Exit");
        System.out.println("=====================");
    }

    static void searchSong() {
        System.out.print("Keyword: ");
        String key = scanner.nextLine();
        
        List<Song> results = search.searchSongs(catalog.getAllSongs(), key);
        search.displayResult(results);
        
        for (Song s : results) {
            history.addHistory(s);
        }
    }

    static void addQueue() {
        int id = readInt("Enter song ID: ");
        Song s = catalog.findSongById(id);
        if (s != null) {
            queue.enqueue(s);
        } else {
            System.out.println("Song not found!");
        }
    }

    static void playSong() {
        Song s = queue.dequeue();
        if (s != null) {
            System.out.println("Playing: " + s.getTitle());
        }
    }

    static int readInt(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input! Enter a number.");
            }
        }
    }

    static void seedData() {
        catalog.addSong(new Song(1, "Separuh Nafas", "Dewa 19", "Laskar Cinta", "Rock"));
        catalog.addSong(new Song(2, "Aku Milikmu", "Dewa 19", "Format Masa Depan", "Rock"));
        catalog.addSong(new Song(3, "Risalah Hati", "Dewa 19", "Bintang Lima", "Rock"));
        catalog.addSong(new Song(4, "Terakhir Kali", "Wijaya 80", "Perjumpaan", "Pop"));
        catalog.addSong(new Song(5, "Film Favorit", "Sheila On 7", "Musim Yang Baik", "Pop Rock"));
        catalog.addSong(new Song(6, "Kita", "Sheila On 7", "Sheila On 7", "Alternative Rock"));
        catalog.addSong(new Song(7, "Seberapa Pantas", "Sheila On 7", "07 Des", "Alternative Rock"));
        catalog.addSong(new Song(8, "The Fate of Ophelia", "Taylor Swift", "The Tortured Poets Department: The Anthology", "Folk"));
        catalog.addSong(new Song(9, "Message In A Bottle", "Taylor Swift", "Red (Taylor's Version)", "Pop"));
        catalog.addSong(new Song(10, "Shake It Off", "Taylor Swift", "1989", "Pop"));
    }
}