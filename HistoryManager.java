import java.util.Stack;

public class HistoryManager {

    private Stack<Song> history = new Stack<>();

    // Time Complexity: O(1)
    // Menambahkan lagu ke puncak Stack
    public void addHistory(Song song) {
        history.push(song);
    }

    // Time Complexity: O(n)
    // Menampilkan seluruh lagu dalam history
    public void showHistory() {

        if (history.isEmpty()) {
            System.out.println("History is empty.");
            return;
        }

        System.out.println("\n===== HISTORY =====");

        for (int i = history.size() - 1; i >= 0; i--) {
            Song song = history.get(i);
            System.out.println(song.getTitle() + " - " + song.getArtist());
        }
    }

    // Time Complexity: O(1)
    // Mengambil dan menghapus lagu terakhir dari Stack
    public Song getPreviousSong() {

        if (history.isEmpty()) {
            System.out.println("No previous song.");
            return null;
        }

        return history.pop();
    }
}