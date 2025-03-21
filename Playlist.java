import java.util.HashMap;

public class Playlist {

	private String name;
	private HashMap<String, Song> songs;
	
	public Playlist(String name) {
		this.songs= new HashMap<String, Song>();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public HashMap<String, Song> getSongs() {
		return new HashMap<String, Song>(songs);
	}
	
	public void addSong(Song song) {
		songs.put(song.getTitle(), song);
	}
	
	public void removeSong(String name) {
		songs.remove(name);
	}
	
	@Override
	public String toString() {
		String list = "Songs: ";
		for (Song song : songs.values()) {
			list += song.getTitle() + " by " + song.getArtist() + "\n";
		}
		return list;
	}
}
