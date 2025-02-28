import java.util.List;
import java.util.ArrayList;

public class Album {
    private String title;
    private String artist;
    private String genre;
    private int year;
    private List<Song> songs;

    public Album(String title, String artist, String genre, int year) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.year = year;
        this.songs = new ArrayList<>();
    }

    public void addSong(String songTitle) {
        songs.add(new Song(songTitle, artist, title));
    }

    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public List<Song> getSongs() { return songs; }
}
