import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class UserAccount {
	private final String username;
	private final String password; // final, assume neither of these can be changed once created
	private LibraryModel library;
	
	
	// essentially creating new user account 
	public UserAccount(String username, String password) throws IOException {
		this.username = username;
		this.password= password;
		this.library = new LibraryModel();
	}
	
	// TO DO: take user library and write all the date to a txt file
	public void writeLibraryToFile() throws IOException {
		
		String fileName = this.username + "_Library.txt";
		FileWriter writer = new FileWriter(fileName);
		
	}
	
	// TO DO: take user txt file and rewrite all data to their library
	public LibraryModel readLibraryFromFile(String username) throws IOException {
		
		LibraryModel userLibrary = new LibraryModel();
		String userFile = username + "_Library.txt";
		
		try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                
	            	
	            }
	        } catch (IOException e) {
	            System.err.println("Error reading file");
	        }
		return userLibrary;
	}
	
	public LibraryModel getLibrary() {
		return library;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}	
}
