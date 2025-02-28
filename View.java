/*
search for information from the music store 
● for a song by title ------------------------------------------
● for a song by artist -------------------------------------------
● for an album by title ------------------------------------------
● for an album by artist ----------------------------------------


the expected results of searching 
● for a song that is in the database: print the song title, the artist, and the album it’s on  -----------------------
● for an album: print the album information and a list of the songs in the appropriate order --------------------------
● for anything that is not in the database: a message indicating that the item is not there  ----------------------------
● for anything that has multiple results: print all the results  -----------------------------------------------------------


search for information from the user library 
● should cover all the search cases listed for the music store  -------------------------------------
● should also be able to search for a playlist by name – the result should print the songs 
(title and artist)  -------------------------------------------------------------------------------------

 
add something to the library 
● add a song to the library (as long as it is in the store) ----------------------------------------
● add a whole album to the library (as long as it is in the store) ----------------------------------------

rate a song  
● the ratings are 1 to 5 ------------------------------------------------
● songs do not have to be rated so there is no default rating ---------------------------------
● songs that are rated as 5 should automatically be set to “favorite” ---------------------------------

get a list of items from the library -----------------------------------------------------------------------
● a list of song titles (any order) ---------------------------------------------------------------
● a list of artists (any order) ---------------------------------------------------------------
● a list of albums (any order) ---------------------------------------------------------------
● a list of playlists (any order) ---------------------------------------------------------------
● a list of “favorite” songs ---------------------------------------------------------------

mark a song as “favorite” -----------------------------------------------------------------------

create a playlist and add/remove songs -------------------------------------
● playlists should have a name ---------------------------------------------------
● songs should be maintained in the order they are added -----------------------------------

 */
public class View {
	// As indicated in Part 2, the view is simply the user interface, and its only purpose is to interact 
	// with the user and communicate with the model. It should not be storing or manipulating any of 
	// the data. It simply gets user requests and gets information from the model based on those 
	// requests. 
	
	private final MusicStore musicstore = new MusicStore();
	private final LibraryModel library = new LibraryModel();
	
	public static void main(String[] args) {
			
	}
	
}

