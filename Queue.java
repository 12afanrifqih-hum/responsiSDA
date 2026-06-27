import java.util.*;

public class Queue {
    
    private LinkedList<Song> queue;

    public Queue() {
        this.queue = new LinkedList<>();
    }

    public void enqueue(Song song) {
        queue.addLast(song);
        System.out.println("'" + song.getTitle() + "' added to queue");
    }

    public Song dequeue() {
        if (isEmpty()) {
            System.out.println("No songs in queue");
            return null;
        }
        Song removedSong = queue.removeFirst();
        System.out.println("'" + removedSong.getTitle() + "' removed from queue");
        return removedSong;
    }

    public Song peek() {
        if (isEmpty()) {
            System.out.println("No songs in queue");
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
        System.out.println("Queue cleared");
    }

    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("No songs in queue");
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