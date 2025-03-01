import java.util.ArrayList;


public class Playlist {

	private String name;
	private ArrayList<Song> playlist;
	
	public Playlist(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Song> getSongs() {
		return new ArrayList<Song>(playlist);
	}
	
	
	public void addSong(String name, String Artist) {
		playlist.add(new Song(name, Artist));
	}
	
	public void removeSong(String name) {
		for (Song song : playlist) {
			if(song.getTitle().equals(name)) playlist.remove(song);
		}
	}
	@Override
	public String toString() {
		String songs = "Songs: ";
		for (Song song : playlist) {
			songs += song.getTitle() + " by " + song.getArtist() + "\n";
		}
		return songs;
	}
}
