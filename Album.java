import java.util.HashMap;
import java.util.Map;

public class Album {

	private Map<String, Song> songs;
	private final String title;
	private final String artist;
	private final String genre;
	private final String year;
	
	public Album (String title, String artist, String genre, String year) {
		this.title = title;
		this.artist = artist;
		this.genre = genre;
		this.year = year;
		this.songs = new HashMap<>();
	}
	
	public void addSong (Song song) {
		this.songs.put(song.getTitle(), song);
	}
	
	public HashMap<String, Song> getSongs() {
		return new HashMap<String, Song>(songs);
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public String getYear() {
		return year;
	}
	
	public String toString() {
		String listOfSongs = "\nSongs on album: \n";
		for (Song song : songs.values()) {
			listOfSongs += song.getTitle() + "\n";
		}
		return String.format("\nTitle: %s by %s from %s, %s Genre %s", title, artist, year, genre, listOfSongs);
				
	}
	
}
