import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class MusicStore {
	
//  Your code will need to read each item from the albums file, construct each album’s file name, 
//	and then read in the album information.
//  format: <album title>_<artist>.txt 
	
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
		return fileNames;
			// need to read in file using constructed name, and store 
			// (Album Title, Artist, Genre, Year) data from line 1, then 
			// store all songs from album in order
			
			// thinking should make album and song classes before we write this,
			// to construct those objects with the attributes here 
			// and then store them in this classe's data structures	
	}
	
	public void readAlbumInfo () throws IOException{
		List<String> fileNames = ConstructAlbumFiles ();
		for (String fileName : fileNames) {
			FileReader file = new FileReader(fileName); // 
			BufferedReader albums = new BufferedReader(file); // open a specific album file 
			
			String[] albumHeader = albums.readLine().split(",");
			// construct album object using the header info from line 1
			Album album = new Album(albumHeader[0], albumHeader[1], albumHeader[2], albumHeader[3]);
			
			// NEED TO READ IN SONGSIN ORDER, create song objects, AND STORE IN ALBUM
		}
	}
	
	public static void main (String[] args) throws IOException{
		
	}
}
