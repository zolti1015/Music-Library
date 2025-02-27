import java.util.ArrayList;

public class LibraryModel {
	
	ArrayList<Album> albums = new ArrayList<>();
	ArrayList<Song> songs = new ArrayList<>();
	
	public LibraryModel () {
		
		
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
		
		// print album info and list of songs in order if found 
		public String getAlbumInfo(String titleOrArtist) {
			for (Album album : albums) {
				if (album.getTitle().equals(titleOrArtist) ||
					album.getArtist().equals(titleOrArtist)) {
					return album.toString();
				}
				}
		return "Searched for Data is not in the database.";
		}
		
		public ArrayList<Song> getSongs () {
			return new ArrayList<Song>(songs); // copy list to avoid reference
		}
		
		public ArrayList<Album> getAlbums () {
			return new ArrayList<Album>(albums); // copy list to avoid reference
		}
		
	
	
	
	//  keeps track of the user’s library 
	// and interacts with other classes including the View and the MusicStore. 
	
}
