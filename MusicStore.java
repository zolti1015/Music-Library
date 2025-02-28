import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MusicStore {
	
	ArrayList<Album> albums = new ArrayList<>();
	ArrayList<Song> songs = new ArrayList<>();
	
	
	/*
	 * Constructs all file names and return a list of them
	 */
	public static List<String> ConstructAlbumFiles () throws IOException {
		
		FileReader file = new FileReader("albums.txt"); // format for each line: album title, artist
		BufferedReader albums = new BufferedReader(file);
		List<String> fileNames =  new ArrayList<>();
		
		while (true) {
			
			String line = albums.readLine();
			// cut loop when end of file reached
			if (line == null) break;
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
			FileReader file = new FileReader(fileName); // 
			BufferedReader albums = new BufferedReader(file); // open a specific album file 
			
			String[] albumHeader = albums.readLine().split(",");
			// (Album Title, Artist, Genre, Year) data from line 1
			Album album = new Album(albumHeader[0], albumHeader[1], albumHeader[2], albumHeader[3]);
			this.albums.add(album); // add album to store
			
			// READ IN SONGS IN ORDER AND STORE IN ALBUM
			while (true) {
				
				String songName = albums.readLine(); // advances one line
				if (songName == null) break; // end of song list file 
				album.addSong(songName);  // add the song to the album's collection of songs in the list
				songs.add(new Song(songName)); // add song to music store
		}
	}
}
	
	// get song info by title or artist
		public String getSongInfo(String titleOrArtist) {
				for (Album album : getAlbums()) 
				{
					for (Song song : album.getSongs())
				    {
						if (song.getArtist().equals(titleOrArtist) ||
							song.getTitle().equals(titleOrArtist) ) 
						{
								return song.toString() +
										"\nAlbum: " + album.getTitle();
					    }
				   }
			   }
			return "Searched for Data is not in the database."; // need thing not found message
	}
			
			// get album info and list of songs by title or Artist
		public String getAlbumInfo(String titleOrArtist) {
				for (Album album : albums) {
					if (album.getTitle().equals(titleOrArtist) ||
						album.getArtist().equals(titleOrArtist)) {
						return album.toString();
					}
					}
			return "Searched for Data is not in the database.";
			}
			
		
	public boolean isInStore(String songOrAlbumName) {
		if(songs.contains(new Song(songOrAlbumName))) { 
			return true;
		}
		for (Album album : albums) {
			if (album.getTitle().equals(songOrAlbumName)) {
				return true;
			}
		}
		
		return false;
	}
	
	public ArrayList<Song> getSongs () {
		return new ArrayList<Song>(songs); // copy list to avoid reference
	}
	
	public ArrayList<Album> getAlbums () {
		return new ArrayList<Album>(albums); // copy list to avoid reference
	}
	
	public static void main (String[] args) throws IOException {
		
	}
}
