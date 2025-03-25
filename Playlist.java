import java.util.LinkedHashSet;
import java.util.ArrayList;
import java.util.Collections;

public class Playlist {

	private String name;
	private LinkedHashSet<Song> songs;
	
	public Playlist(String name) {
		this.songs = new LinkedHashSet<>(); // fast add/remove, keeps order
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public LinkedHashSet<Song> getSongs() {
		return new LinkedHashSet<Song>(songs);
	}
	
	public void addSong(Song song) {
		songs.add(song);
	}
	
	// for ordered playlists, size caps at 10
	public void addSongToFront(Song song) {
		if (songs.size() == 10) {
			songs.removeLast(); 
		}
		songs.addFirst(song);
	}
	
	public void removeSong(Song song) {
		songs.remove(song);
	}
	
	public void shufflePlaylist() {
		ArrayList<Song> songs = new ArrayList<Song>(this.songs); // turn to list for shuffling
		Collections.shuffle(songs);
		this.songs = new LinkedHashSet<Song>(songs);
	}
	
	@Override
	public String toString() {
		String list = "Songs: ";
		for (Song song : songs) {
			list += song.getTitle() + " by " + song.getArtist() + "\n";
		}
		return list;
	}
}
