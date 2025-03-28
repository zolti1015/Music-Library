import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;


public class View {
    
	
    private final Scanner scanner;
    private LibraryModel model;
    private UserAccount user;
    private final usernameAndPasswordDatabase database;
    
    public View() {
        this.scanner = new Scanner(System.in);
        this.database = new usernameAndPasswordDatabase();
    }


    public void run() throws IOException, NoSuchAlgorithmException {
    	handleSignIn();
    	
        displayWelcomeMessage();
        
        while (true) {
            displayMainMenu();
            
            System.out.println("Enter your choice: ");
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    handleSearchMenu();
                    break;
                case 2:
                    handleAddMenu();
                    break;
                case 3:
                    handleListMenu();
                    break;
                case 4:
                    handlePlaylistMenu();
                    break;
                case 5:
                	// favorite and rate
                    handleSongActionsMenu();
                    break;
                case 6: 
                	// sign out of account
                    System.out.println("Signing out... Goodbye.");
                    run(); // re-run the view to handle new sign in
                    return;
                case 7: 
                	// exit system
                    System.out.println("Terminating program... Goodbye.");
                    return;
                
            }
            
        }
    }
    
    
    private void displayMainMenu() {
        System.out.println("\nMAIN MENU");
        System.out.println("1. Search for music");
        System.out.println("2. Add/remove to/from library");
        System.out.println("3. List library items");
        System.out.println("4. Manage playlists");
        System.out.println("5. Song actions (favorite/rate/play/sort/shuffle)");
        System.out.println("6. SignOut");
        System.out.println("7. Terminate");
    }

    private void displaySearchMenu() {
        System.out.println("\nSEARCH MENU");
        System.out.println("0. Go back to main menu");
        System.out.println("1. Search for song by title in music store");
        System.out.println("2. Search for song by artist in music store");
        System.out.println("3. Search for album by title in music store");
        System.out.println("4. Search for album by artist in music store");
        
        System.out.println("5. Search for song by title in library");
        System.out.println("6. Search for song by artist in library");
        System.out.println("7. Search for album by title in library");
        System.out.println("8. Search for album by artist in library");
        System.out.println("9 Search for song by genre in library");
        
    }
    
    
    private void displayAddMenu() {
        System.out.println("\nADD MENU");
        System.out.println("1. Add song to library");
        System.out.println("2. Add album to library");
        System.out.println("3. Remove song from library");
        System.out.println("4. Remove album from library");
        System.out.println("0. Back to main menu");
    }

    private void displayListMenu() {
        System.out.println("\nLIST MENU");
        System.out.println("1. List all songs");
        System.out.println("2. List all artists");
        System.out.println("3. List all albums");
        System.out.println("4. List all playlists");
        System.out.println("0. Back to main menu");
    }

    private void displayPlaylistMenu() {
        System.out.println("\nPLAYLIST MENU");
        System.out.println("1. Create new playlist");
        System.out.println("2. Add song to playlist");
        System.out.println("3. Remove song from playlist");
        System.out.println("4. View playlist");
        System.out.println("5. Shuffle playlist");
        System.out.println("0. Back to main menu");
    }

    private void displaySongActionsMenu() {
        System.out.println("\nSONG ACTIONS MENU");
        System.out.println("1. Mark song as favorite");
        System.out.println("2. Rate song");
        System.out.println("3. Play song");
        System.out.println("4. Sort songs");
        System.out.println("5. Shuffle songs");
        
        System.out.println("0. Back to main menu");
    }
    
    private void handleSignIn() throws IOException, NoSuchAlgorithmException {
    	System.out.println("1. LOGIN\n"
    					 + "2. CREATE ACCOUNT");
    	int choice = scanner.nextInt();
    	scanner.nextLine();
    	
    	System.out.println("Username:");
        String username = scanner.nextLine();
        
        System.out.println("Password:");
        String password = scanner.nextLine();
        
    	switch (choice) {
    		// logging in
    		case 1:
    			user = new UserAccount(username, password);
    			if (!database.isLoginValid(user)) {
    				System.out.println("Username or password incorrect!");
    				handleSignIn(); // revert to login screen to try again
    				return; 
    			}
    			// if reached, means log in was sucessful and we can extract user's library
    			this.model = database.getCreatedAccounts().get(username).getLibrary(); 
    			this.user = database.getCreatedAccounts().get(username); // update the current user here
    			System.out.println("Successfully signed in!");
    			break;
    		// creating new account
    		case 2: 
    			user = new UserAccount(username, password);
    			System.out.println("Account successfully created!");
    			database.writeNewAccountToDatabase(user);
    			this.model = user.getLibrary();
    			break;
    		default: 
    			System.out.println("Invalid input. Try again");
    			handleSignIn();
    			return; 
    	}  
    }
    
    

    private void handleSearchMenu() {
    
        while (true) {
            displaySearchMenu();
            System.out.println("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the leftover newline character
            
            switch (choice) {
                case 0:
                	return;
                case 1:
                     System.out.println("Enter title: ");
                     String title = scanner.nextLine();
                     System.out.println(model.getStore().getSongInfoByTitle(title));
                     System.out.println("Would you like the album info for this song(s)? (y/n)");
                     String yesOrNo = scanner.nextLine();
                     
                     if (yesOrNo.equals("n")) break;
                     if (yesOrNo.equals("y")) {
                    	 model.getStore().getAlbumInfoForSong(title); // show song's album info from store
                    	 if (model.getAlbums().get(model.getStore().getSongs().get(title).get(0).getAlbumOn()) != null) {
                    		 System.out.println("Album is in the library!");
                    	 }
                    	 else  System.out.println("Album is not in the library!");
                     }
                     break;
                case 2: 
                	 System.out.println("Enter artist: ");
                     String artist = scanner.nextLine();
                     System.out.println(model.getStore().getSongInfoByArtist(artist));
                     break;
                case 3:
                	 System.out.println("Enter title: ");
                     String title2 = scanner.nextLine();
                     System.out.println(model.getStore().getAlbumInfoByTitle(title2));
                     break;
                case 4:
               	 	 System.out.println("Enter artist: ");
                     String artist2 = scanner.nextLine();
                     System.out.println(model.getStore().getAlbumInfoByArtist(artist2));
                     break;
                case 5:
                     System.out.println("Enter title: ");
                     String title3 = scanner.nextLine();
                     System.out.println(model.getSongInfoByTitle(title3));
                     break;
                case 6: 
                	 System.out.println("Enter artist: ");
                     String artist3 = scanner.nextLine();
                     System.out.println(model.getSongInfoByArtist(artist3));
                     break;
                case 7:
                	 System.out.println("Enter title: ");
                     String title4 = scanner.nextLine();
                     System.out.println(model.getAlbumInfoByTitle(title4));
                     break;
                case 8:
               	 	System.out.println("Enter artist: ");
                    String artist4 = scanner.nextLine();
                    System.out.println(model.getAlbumInfoByArtist(artist4));
                    break;
                case 9:
                	System.out.println("Enter genre: ");
                    String genre = scanner.nextLine();
                    model.searchForSongByGenre(genre);
                    break;
                default: 
              	  	System.out.println("Invalid input"); 
            }
        }
    }
    
    public void displayWelcomeMessage() {
    	System.out.println("Welcome to your library.");
    }
    
    
    public void handleAddMenu() {
    	while (true) {
    	displayAddMenu();
    	System.out.println("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume the leftover newline character
        
        switch (choice) {
        	case 0: 
        		return;
        	case 1: 
        		System.out.println("Enter song name: ");
                String song = scanner.nextLine();
                model.addSongToLibrary(song);
                break;
        	case 2:
        		System.out.println("Enter album name: ");
                String album = scanner.nextLine();
                model.addAlbumToLibrary(album);
                break;
        	case 3:
        		System.out.println("Enter song name: ");
                String song2 = scanner.nextLine();
                model.removeSongFromLibrary(song2);
                break;
        	case 4:
        		System.out.println("Enter album name: ");
                String album2 = scanner.nextLine();
                model.removeAlbumFromLibrary(album2);
                break;
        	default: 
           	  	System.out.println("Invalid input"); 
    }
}
    }
    
   public void handleListMenu() {
	   
	   while (true) {
	   displayListMenu();
	   System.out.println("Enter your choice: ");
       int choice = scanner.nextInt();
       scanner.nextLine(); // consume the leftover newline character
       
	   switch (choice) {
	   case 0:
		   return;
	   
	   case 1: 
			System.out.println(model.listOfItems("Songs")); break;
	   case 2:
			System.out.println(model.listOfItems("Artists")); break;
	   case 3:
			System.out.println(model.listOfItems("Albums")); break;
	   case 4:
			System.out.println(model.listOfItems("Playlists")); break;
	   default: 
     	  System.out.println("Invalid input"); 
   }
}
   }
   
   
   public void handlePlaylistMenu() {
	   
	   while (true) {
	   displayPlaylistMenu();
	   System.out.println("Enter your choice: ");
       int choice = scanner.nextInt();
       scanner.nextLine(); // consume the leftover newline character
       
       switch (choice) {
	  
	   case 1: 
		   System.out.println("What would you like to name your playlist?");
           String name = scanner.nextLine();
           model.createNewPlaylist(name);
           break;
	   case 2:
		   System.out.println("Name of song you want to add?");
           String name2 = scanner.nextLine();
           
           System.out.println("Name of playlist you want to add to?");
           String name3 = scanner.nextLine();
		   model.addSongToPlaylist(name3, name2);
		   break;
	   case 3:
		   System.out.println("Name of song you want to remove?");
           String name4 = scanner.nextLine();
           
           System.out.println("Name of playlist you want to remove it from?");
           String name5 = scanner.nextLine();
           model.removeSongFromPlaylist(name5, name4);
           break;
	   case 4:
		   System.out.println("Enter playlist name: ");
           String playlist = scanner.nextLine();
        	    
       	   System.out.println(model.searchForPlaylist(playlist));
       	   break;
	   case 5: 
		   System.out.println("Name of playlist you want to shuffle?");
           String toShuffle = scanner.nextLine();
		   model.getPlaylists().get(toShuffle).shufflePlaylist();
		   break;
	   case 0:
			return;
	   default: 
     	  System.out.println("Invalid input"); 
       }
   }
   	}
   
   public void handleSongActionsMenu() {
	   
	   while (true) {
	   displaySongActionsMenu();
	   System.out.println("Enter your choice: ");
       int choice = scanner.nextInt();
       scanner.nextLine(); // consume the leftover newline character
       
       switch (choice) {
           case 0: 
        	   return;
           case 1:
        	   System.out.println("Name of song you want to mark as favorite?");
               String name = scanner.nextLine();
        	   model.markSongAsFavorite(name);
        	   break;
           case 2: 
        	   System.out.println("Name of song you want to rate?");
               String name2 = scanner.nextLine();
               
               System.out.println("Rating 1-5 you want to assign it?");
               int rating = scanner.nextInt();
               
               model.rateSong(name2, rating);
               break;
          case 3: 
        	  System.out.println("Which song would you like to play?");
              String toPlay = scanner.nextLine();
              model.playSong(toPlay);
              break;
          case 4:
        	  System.out.println("By: 1. rating \n 2. artist \n 3. title ");
        	  int type = scanner.nextInt();
        	  model.printSortedSongs(type);
        	  break;
          case 5: 
        	  model.shuffleSongs();
        	  break;
        	  
          default: 
        	  System.out.println("Invalid input"); 
       }
   }
   }
   
}
