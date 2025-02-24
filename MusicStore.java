import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.HashMap;

public class MusicStore {
	
//  Your code will need to read each item from the albums file, construct each album’s file name, 
//	and then read in the album information.
//  format: <album title>_<artist>.txt 
	
	
	public void readInAlbums () throws IOException {
		
		FileReader file = new FileReader("albums.txt"); // format for each line: album title, artist
		BufferedReader albums = new BufferedReader(file);
		
		while (albums.readLine() != null) {
			
			String line = albums.readLine();
			String[] albumArtist = line.split(",");
			
			String album = albumArtist[0];
			String artist = albumArtist[1];
			
			String albumFileName = album + "_" + artist;
			
			// need to read in file using constructed name, and store 
			// (Album Title, Artist, Genre, Year) data from line 1, then 
			// store all songs from album in order
			
			// thinking should make album and song classes before we write this,
			// to construct those objects with the attributes here 
			// and then store them in this classe's data structures
			
			
		}
	}
}
