import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class MusicStore {
	
	private Map<String, Album> albums = new HashMap<>(); // album name, album object
	private Map<String, ArrayList<Song>> songs = new HashMap<>(); // song name, all songs with that name
	
	private Map<String, ArrayList<Album>> ArtistAndAlbums = new HashMap<>(); // artist name, list of albums
	private Map<String, ArrayList<Song>> ArtistAndSongs = new HashMap<>(); // artist name, list of songs
	
	/*
	 * Constructs all file names and return a list of them
	 */
	public static List<String> ConstructAlbumFiles () throws IOException {
		
		FileReader file = new FileReader("albums.txt"); // format for each line: album title, artist
		BufferedReader albums = new BufferedReader(file);
		List<String> fileNames =  new ArrayList<>();
		
			String line;
			while ( (line = albums.readLine()) != null) {
				
				String[] albumArtist = line.split(",");
			
				String album = albumArtist[0];
				String artist = albumArtist[1];
			
				// construct a file name from an album and artist 
				String albumFileName = album + "_" + artist + ".txt";
				fileNames.add(albumFileName);
		}
		albums.close();
		return fileNames;
	}
	
	public void readInAlbumInfo () throws IOException {
		
		List<String> fileNames = ConstructAlbumFiles();
		for (String fileName : fileNames) {
			FileReader file = new FileReader(fileName); 
			BufferedReader albums = new BufferedReader(file); // open a specific album file 
			
			String[] albumHeader = albums.readLine().split(",");
							    // (Album Title,    Artist,         Genre,          Year) data from line 1
			Album album = new Album(albumHeader[0], albumHeader[1], albumHeader[2], albumHeader[3]);
			this.albums.put(album.getTitle(), album); // add album to store
			
			// READ IN SONGS IN ORDER AND STORE IN ALBUM
			String songName;
			while ((songName = albums.readLine()) != null) {
				
									// name,   Artist,         album song is on  genre
				Song song = new Song(songName, albumHeader[1], albumHeader[0], albumHeader[2]);
				album.addSong(song);  // add the song to the album's collection of songs in the list
				
				songs.putIfAbsent(song.getTitle(), new ArrayList<Song>());
				songs.get(song.getTitle()).add(song); 
				
				ArtistAndSongs.putIfAbsent(albumHeader[1], new ArrayList<Song>());
				ArtistAndSongs.get(albumHeader[1]).add(song); 
			}
			
				this.albums.put(album.getTitle(), album); 
				
				ArtistAndAlbums.putIfAbsent(albumHeader[1], new ArrayList<Album>());
				ArtistAndAlbums.get(albumHeader[1]).add(album);
				
			albums.close();
	}
}
	
	// get song info by title
	public String getSongInfoByTitle(String title) {
		
		
		String endInfo = "";
		ArrayList<Song> songList = songs.get(title);
		
		if (songList != null) {
			for (Song song : songList) {
				endInfo += (song.toString()) ;		
			}
		}
		
		if (endInfo.equals("")) endInfo += "Searched for Data is not in the database.";
		endInfo += "\n";
		
		return endInfo; 
	}
	
	public String getSongInfoByArtist(String artist) {
		
		String endInfo = "";
		
		ArrayList<Song> songs = ArtistAndSongs.get(artist);
		
		if (songs != null) {
			for (Song song : songs) {
					endInfo += song.toString();		
				}
			}
		
		
		if (endInfo.equals("")) endInfo += "Searched for Data is not in the database.";
		endInfo += "\n";
		return endInfo; 
	}
	
	// get album info and list of songs by title or Artist
	public String getAlbumInfoByTitle(String title) {
			
			String endInfo = "";
			Album album = albums.get(title);
			if (album != null) endInfo += albums.get(title).toString(); 
				
			if (endInfo.equals("")) endInfo += "Searched for Data is not in the database.";
			endInfo += "\n";
			return endInfo; 
			}
	
	// get album info and list of songs by artist name
	public String getAlbumInfoByArtist(String artist) {
				
			String endInfo = "";
			ArrayList<Album> albums = ArtistAndAlbums.get(artist);
			if (albums != null) {
				for (Album album : albums) {
					endInfo += album.toString(); 
				}
			}
			if (endInfo.equals("")) endInfo += "Searched for Data is not in the database.";
			endInfo += "\n";
			return endInfo; 
		}
			
	
	public HashMap<String, ArrayList<Song>> getSongs () {
		return new HashMap<String, ArrayList<Song>>(songs); // copy list to avoid reference
	}
	
	public HashMap<String, Album> getAlbums () {
		return new HashMap<String, Album>(albums); // copy list to avoid reference
	}
	
	public void getAlbumInfoForSong(String songName) {
		songs.get(songName).forEach(song -> albums.get(song.getAlbumOn()).toString());
	}
}
