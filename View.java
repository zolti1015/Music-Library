
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
        displayWelcomeMessage();
        
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
        
        System.out.println("9. Search for playlist by name");
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
            }
        }
    }
    
    public void displayWelcomeMessage() {
    	System.out.println("Welcome to your library.");
    }
    
    
}
