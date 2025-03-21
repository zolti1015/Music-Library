import java.io.IOException;
import java.util.Scanner;


public class View {
    
	
    private Scanner scanner;
    private LibraryModel model;
    private UserAccount user;
    private final usernameAndPasswordDatabase database= new usernameAndPasswordDatabase();

    public View() {
        this.scanner = new Scanner(System.in);
    }


    public void run() throws IOException {
    	handleSignIn();
    	
        displayWelcomeMessage();
        
        while (true) {
            displayMainMenu();
            
            System.out.println("Enter your choice: ");
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 0: // sign out of account
                    System.out.println("Exiting... Goodbye.");
                    this.user.writeLibraryToFile(); // store user library info to unique txt file
                    return;
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
            }
        }
    }
    
    private void handleSignIn() throws IOException {
    	System.out.println("1. LOGIN\n"
    					 + "2. CREATE ACCOUNT");
    	int choice = scanner.nextInt();
    	
    	System.out.println("Username:");
        String username = scanner.nextLine();
        System.out.println("Password:");
        String password = scanner.nextLine();
        
    	switch (choice) {
    		case 1: 
    			user = new UserAccount(username, password);
    			if (!database.findUser(user)) {
    				System.out.println("Username or password incorrect!");
    				handleSignIn(); // revert to login screen to try again
    			}
    			this.model = user.readLibraryFromFile(username); // else restore user data
    			
    		case 2: 
    			user = new UserAccount(username, password);
    			System.out.println("Account successfully created!");
    			this.model = user.getLibrary();
    			break;
    	}  
    }
    
    private void displayMainMenu() {
        System.out.println("\nMAIN MENU");
        System.out.println("1. Search for music");
        System.out.println("2. Add to library");
        System.out.println("3. List library items");
        System.out.println("4. Manage playlists");
        System.out.println("5. Song actions (favorite/rate)");
        System.out.println("0. SignOut");
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
    }
    
    
    private void displayAddMenu() {
        System.out.println("\nADD MENU");
        System.out.println("1. Add song to library");
        System.out.println("2. Add album to library");
        System.out.println("0. Back to main menu");
    }

    private void displayListMenu() {
        System.out.println("\nLIST MENU");
        System.out.println("1. List all songs");
        System.out.println("2. List all artists");
        System.out.println("3. List all albums");
        System.out.println("4. List all playlists");
        System.out.println("5. List favorite songs");
        System.out.println("0. Back to main menu");
    }

    private void displayPlaylistMenu() {
        System.out.println("\nPLAYLIST MENU");
        System.out.println("1. Create new playlist");
        System.out.println("2. Add song to playlist");
        System.out.println("3. Remove song from playlist");
        System.out.println("4. View playlist");
        System.out.println("0. Back to main menu");
    }

    private void displaySongActionsMenu() {
        System.out.println("\nSONG ACTIONS MENU");
        System.out.println("1. Mark song as favorite");
        System.out.println("2. Rate song");
        System.out.println("0. Back to main menu");
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
	   case 5:
			System.out.println(model.listOfItems("Favorites")); break;
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
          default: 
        	  System.out.println("Invalid input"); 
       }
   }
   }
   
}
