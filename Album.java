import java.util.ArrayList;

public class Album {

	private ArrayList<Song> songs;
	private String title;
	private String artist;
	private String Genre;
	private String year;
	
	public Album (String title, String artist, String genre, String year) {
		this.title = title;
		this.artist = artist;
		this.Genre = genre;
		this.year = year;
	}
	
	// string argument, prevents escaping references
	public void addSong (String title) {
		this.songs.add(new Song(title));
	}
}
