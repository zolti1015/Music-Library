public class LibraryModel {
	
	// store to access and modify user library
	private MusicStore musicStore;
	
	public LibraryModel () {
		
	}
	
	// get song by title or artist
		public String getSong(String titleOrArtist) {
			for (Song song : musicStore.getSongs()) {
				if (song.getArtist().equals(titleOrArtist) ||
						song.getTitle().equals(titleOrArtist) ) {
					return ;
				}	
				
			}
		}
		
		// IMPLEMENT
		// ● search for an album by title 
		// ● for an album by artist
	
	
	//  keeps track of the user’s library 
	// and interacts with other classes including the View and the MusicStore. 
	
	
	
	
	
	
}
