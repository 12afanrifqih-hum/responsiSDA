import java.util.*;

public class Queue {
    
    private LinkedList<Song> queue;

    public Queue() {
        this.queue = new LinkedList<>();
    }

    // menambahkan lagu ke queue
    public void enqueue(Song song) {
        queue.addLast(song);
        System.out.println("'" + song.getTitle() + "' added to queue");
    }

    // mengambil lagu dari queue terdepan 
    public Song dequeue() {
        if (isEmpty()) {
            System.out.println("Your queue is empty");
            return null;
        }
        Song removedSong = queue.removeFirst();
        return removedSong;
    }

    // menambahkan lagu di awal queue
    public void addFirst(Song song) {
        queue.addFirst(song);
    }

    // melihat lagu di awal queue tanpa menghapusnya
    public Song peek() {
        if (isEmpty()) {
            System.out.println("Your queue is empty");
            return null;
        }
        return queue.getFirst();
    }

    // mengecek apakah queue kosong
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    // mendapatkan ukuran queue
    public int size() {
        return queue.size();
    }

    // menampilkan semua lagu dalam queue
    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("Your queue is empty");
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
