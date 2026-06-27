import java.util.Stack;

public class HistoryManager {

    private Stack<Song> history = new Stack<>();

    public void addHistory(Song song) {
        history.push(song);
    }

    public void showHistory() {

        if (history.isEmpty()) {
            System.out.println("History kosong.");
            return;
        }

        System.out.println("\n===== HISTORY =====");

        for (int i = history.size() - 1; i >= 0; i--) {
            Song song = history.get(i);
            System.out.println(song.getTitle() + " - " + song.getArtist());
        }
    }

    public void undoLastSong() {

        if (history.isEmpty()) {
            System.out.println("History kosong.");
            return;
        }

        Song removed = history.pop();
        System.out.println("Undo berhasil: " + removed.getTitle());
    }
}