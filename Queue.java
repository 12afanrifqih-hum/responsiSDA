import java.util.*;

public class Queue {
    
    private LinkedList<Song> queue;

    public Queue() {
        this.queue = new LinkedList<>();
    }

    public void enqueue(Song song) {
        queue.addLast(song);
        System.out.println("'" + song.getTitle() + "' ditambahkan ke antrian");
    }

    public Song dequeue() {
        if (isEmpty()) {
            System.out.println("Tidak ada lagu di antrian");
            return null;
        }
        Song removedSong = queue.removeFirst();
        System.out.println("'" + removedSong.getTitle() + "' dihapus dari antrian");
        return removedSong;
    }

    public Song peek() {
        if (isEmpty()) {
            System.out.println("Tidak ada lagu di antrian");
            return null;
        }
        return queue.getFirst();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }

    public void clear() {
        queue.clear();
        System.out.println("Antrian dihapus");
    }

    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("Tidak ada lagu di antrian");
            return;
        }

        int i = 1;
        for (Song song : queue) {
            System.out.println(i + ". ");
            System.out.println("Title  : " + song.getTitle());
            System.out.println("Artist : " + song.getArtist());
            System.out.println("Album  : " + song.getAlbum());
            System.out.println("Genre  : " + song.getGenre());
            System.out.println("----------------------------------------");
            i++;
        }
    }
}