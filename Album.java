import java.util.ArrayList;


public class Album {

	private ArrayList<Song> songs;
	private final String title;
	private final String artist;
	private final String genre;
	private final String year;
	
	public Album (String title, String artist, String genre, String year) {
		this.title = title;
		this.artist = artist;
		this.genre = genre;
		this.year = year;
	}
	
	// string argument, prevents escaping references
	public void addSong (String title) {
		this.songs.add(new Song(title));
	}
	
	public ArrayList<Song> getSongs() {
		return new ArrayList<Song>(songs);
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

	@Override
	public String toString() {
		String listOfSongs = "\nSongs on album: \n";
		for (Song song : songs) {
			listOfSongs += song.getTitle() + "\n";
		}
		return "Title: " + title +
				"\nArtist: " + artist + 
				"\nGenre: " + genre + 
				"\nYear: " + year + 
				listOfSongs;
	}
	
}
