public class Song {

    private int id;
    private String title;
    private String artist;
    private String album;
    private String genre;

    public Song(int id, String title, String artist, String album, String genre) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
    }

    public Song(String title, String artist, String album, String genre) {
        this(0, title, artist, album, genre);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return id + " | " + title + " - " + artist + " | " + album + " | " + genre;
    }
}