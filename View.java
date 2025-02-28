
import java.util.List;
import java.util.Scanner;


public class View {
    private LibraryModel model;
    private Scanner scanner;


    public View(LibraryModel model) {
        this.model = model;
        this.scanner = new Scanner(System.in);
    }


    public void run() {
        
        while (true) {
            displayMainMenu();
            System.out.println("Enter your choice: ");
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 0:
                    System.out.println("Exiting... Goodbye.");
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
        
        displayGoodbyeMessage();
    }

    private void displayMainMenu() {
        System.out.println("\nMAIN MENU");
        System.out.println("1. Search for music");
        System.out.println("2. Add to library");
        System.out.println("3. List library items");
        System.out.println("4. Manage playlists");
        System.out.println("5. Song actions (favorite/rate)");
        System.out.println("0. Exit");
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
            
            switch (choice) {
                case 0:
                	return;
                case 1:
                     System.out.println("Enter title: ");
                     String title = scanner.nextLine();
                     System.out.println(model.getStore().getSongInfo(title));
                     break;
                case 2: 
                	 System.out.println("Enter artist: ");
                     String artist = scanner.nextLine();
                     System.out.println(model.getStore().getSongInfo(artist));
                     break;
                case 3:
                	 System.out.println("Enter title: ");
                     String title2 = scanner.nextLine();
                     System.out.println(model.getStore().getSongInfo(title2));
                     break;
                case 4:
               	 	 System.out.println("Enter artist: ");
                     String artist2 = scanner.nextLine();
                     System.out.println(model.getStore().getSongInfo(artist2));
                     break;
                case 5:
                     System.out.println("Enter title: ");
                     String title3 = scanner.nextLine();
                     System.out.println(model.getStore().getSongInfo(title3));
                     break;
                case 6: 
                	 System.out.println("Enter artist: ");
                     String artist3 = scanner.nextLine();
                     System.out.println(model.getSongInfo(artist3));
                     break;
                case 7:
                	 System.out.println("Enter title: ");
                     String title4 = scanner.nextLine();
                     System.out.println(model.getSongInfo(title4));
                     break;
                case 8:
               	 	System.out.println("Enter artist: ");
                    String artist4 = scanner.nextLine();
                    System.out.println(model.getSongInfo(artist4));
                    break;
            }
        }
    }
    
    public void displayWelcomeMessage() {
    	System.out.println("Welcome to your library.");
    }
    
    public void displayGoodbyeMessage() {
    	System.out.println("Goodbye");
    }
    
    
    public void handleAddMenu() {
    	
    	displayAddMenu();
    	System.out.println("Enter your choice: ");
        int choice = scanner.nextInt();
        switch (choice) {
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
    }
}
    
   public void handleListMenu() {
	   displayListMenu();
	   System.out.println("Enter your choice: ");
       int choice = scanner.nextInt();
	   switch (choice) {
	   
	   case 1: 
			System.out.println(model.listOfItems("Songs")); break;
	   case 2:
			System.out.println(model.listOfItems("Albums")); break;
	   case 3:
			System.out.println(model.listOfItems("Playlists")); break;
	   case 4:
			System.out.println(model.listOfItems("Artists")); break;
	   case 5:
			System.out.println(model.listOfItems("Favorites")); break;
   }
}
   
   
   public void handlePlaylistMenu() {
	   displayPlaylistMenu();
	   System.out.println("Enter your choice: ");
       int choice = scanner.nextInt();
       
       switch (choice) {
	   
	   case 1: 
		   System.out.println("What would you like to name your playlist?");
           String name = scanner.nextLine();
           model.createNewPlaylist(name);
           break;
	   case 2:
		   System.out.println("Name of song you want to add?");
           String name2 = scanner.nextLine();
		   model.addSongToPlaylist(name2);
		   break;
		   
	   // remove song from playlist
	   case 3:
			
	   
	   case 4:
		   System.out.println("Enter playlist name: ");
           String playlist = scanner.nextLine();
       	   System.out.println(model.searchForPlaylist(playlist));
       	   break;
	   case 0:
			return;
   }
	   
   }
   
   public void handleSongActionsMenu() {
	   
   }
   
   
}
