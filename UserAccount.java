import java.io.IOException;

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
