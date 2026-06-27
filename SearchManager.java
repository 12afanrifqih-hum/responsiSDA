import java.util.ArrayList;
import java.util.List;

public class SearchManager {

    public List<Song> searchSongs(List<Song> songs, String keyword) {

        List<Song> result = new ArrayList<>();
        keyword = keyword.toLowerCase();

        for (Song song : songs) {

            if (song.getTitle().toLowerCase().contains(keyword)
                    || song.getArtist().toLowerCase().contains(keyword)
                    || song.getAlbum().toLowerCase().contains(keyword)
                    || song.getGenre().toLowerCase().contains(keyword)) {

                result.add(song);
            }
        }

        return result;
    }

    public void displayResult(List<Song> songs) {

        if (songs.isEmpty()) {
            System.out.println("Lagu tidak ditemukan.");
            return;
        }

        System.out.println("\n===== HASIL PENCARIAN =====");

        for (Song song : songs) {
            System.out.println("ID     : " + song.getId());
            System.out.println("Title  : " + song.getTitle());
            System.out.println("Artist : " + song.getArtist());
            System.out.println("Album  : " + song.getAlbum());
            System.out.println("Genre  : " + song.getGenre());
            System.out.println("----------------------------------------");
        }
    }
}