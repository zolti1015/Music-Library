/*


the expected results of searching 
● for a song that is in the database: print the song title, the artist, and the album it’s on  -----------------------
● for an album: print the album information and a list of the songs in the appropriate order --------------------------
● for anything that is not in the database: a message indicating that the item is not there  ----------------------------
● for anything that has multiple results: print all the results  -----------------------------------------------------------

 */
import java.util.List;
import java.util.Scanner;


public class View {
    private LibraryModel model;
    private Scanner scanner;


    public view(LibraryModel model) {
        this.model = model;
        this.scanner = new Scanner(System.in);
    }


    public void run() {
        displayWelcomeMessage();
        
        while (true) {
            displayMainMenu();
            int choice = getIntInput("Enter your choice: ", 0, 5);
            
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
        System.out.println("1. Search for song by title in music store");
        System.out.println("2. Search for song by artist in music store");
        System.out.println("3. Search for album by title in music store");
    }

    private void handleSearchMenu() {
        boolean back = false;
        
        while (!back) {
            displaySearchMenu();
            int choice = getIntInput("Enter your choice: ", 0, 9);
            
            switch (choice) {
                case 0:
                    back = true;
                    break;
                case 1:
                    searchSongByTitleInStore();
                    break;
            }
        }
    }
}
