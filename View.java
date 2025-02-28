/*


the expected results of searching 
● for a song that is in the database: print the song title, the artist, and the album it’s on  -----------------------
● for an album: print the album information and a list of the songs in the appropriate order --------------------------
● for anything that is not in the database: a message indicating that the item is not there  ----------------------------
● for anything that has multiple results: print all the results  -----------------------------------------------------------

 */
import java.util.Scanner;

public class View {
	// As indicated in Part 2, the view is simply the user interface, and its only purpose is to interact 
	// with the user and communicate with the model. It should not be storing or manipulating any of 
	// the data. It simply gets user requests and gets information from the model based on those 
	// requests. 

	private LibraryModel library;
	private Scanner scanner;

	public View(LibraryModel library) {
		this.library = library;
		this.scanner = new Scanner(System.in);
	}

	// Start the UI loop
	public void start() {
		while (true) {
			System.out.println("\nMusic Library Menu:");
			System.out.println("\n1. View Songs");
			System.out.println("\n2. View Albums");
			System.out.println("\n3. View Playlists");
			System.out.println("\n4. View Artists");
			System.out.println("\n5. View Favorites");
			System.out.println("\n6. Add a Song or Album to my Library");
			System.out.println("\n7. Search for song or album in music store");
			System.out.println("\n8. Search for song or album in my library");
			System.out.println("\n9. Search for playlist");
			System.out.println("\n10. Rate or mark song as favorite");
			System.out.println("\n11. Create/modify playlist");
			System.out.println("\n12. Quit");
			System.out.println("\nEnter your choice: ");

			
			String choice = scanner.nextLine();
			switch (choice) {
				case "1": 
					library.listOfItems("Songs"); break;
				case "2":
					library.listOfItems("Albums"); break;
				case "3":
					library.listOfItems("Playlists"); break;
				case "4":
					library.listOfItems("Artists"); break;
				case "5":
					library.listOfItems("Favorites"); break;
				case "6":
					System.out.println("Song or album? : "); 
					String selection = scanner.nextLine();
					if(selection.toLowerCase().equals("song")) {
						System.out.println("Type song name: ");
						String name = scanner.nextLine();
						library.addSongToLibrary(name); break; }
					else { 
						System.out.println("Type album name: ");
						String name = scanner.nextLine();
						library.addAlbumToLibrary(name); break; }
				case "7":
					System.out.println("Enter the type ('Song'/'Album') and the name of a title or an artist");
					String entry = scanner.nextLine();
					String[] selections = entry.split(" ");
					if(selections[0].toLowerCase().equals("song")) library.getStore().getSongInfo(selections[1]);
					else library.getStore().getAlbumInfo(selections[1]); 
					break;
				case "8":
					System.out.println("Enter the type ('Song'/'Album') and the name of a title or an artist");
					String entry2 = scanner.nextLine();
					String[] selections2 = entry2.split(" ");
					if(selections2[0].toLowerCase().equals("song")) library.getSongInfo(selections2[1]);
					else library.getAlbumInfo(selections2[1]); 
					break;
				case "9":
					System.out.println("Enter the name of playlist: ");
					String entry3 = scanner.nextLine();
					library.searchForPlaylist(entry3);
					break;
				// rate or mark song as favorite
				case "10":
					
					
				// create/add or remove from playlist
				case "11":
					library.listOfItems("Favorites");
					break;
					
				case "12":
					System.exit(0);
				default:
					System.out.println("Invalid selection. Please Reselect.");
					continue;
			}
		}
	}
}
