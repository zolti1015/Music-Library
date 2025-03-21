import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class usernameAndPasswordDatabase {


	public usernameAndPasswordDatabase() {
		
	}
	
	public void writeNewAccountToDatabase(UserAccount account) throws IOException {
		String file = "usersAndPass.txt";
		FileWriter writer = new FileWriter(file);
		writer.write(account.getUsername() + " " + account.getPassword() + "\n");
	}
	
	public boolean findUser(UserAccount account) {
		File file = new File("usersAndPass.txt");
		Scanner scanner;
		boolean exists;
		
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
			return false;
		}
		
		while (true) {
			String[] line  = scanner.nextLine().split(" ");
			String username = line[0];
			String password = line[1];
			if (account.getUsername().equals(username) && 
				account.getPassword().equals(password)) return true;
			return false;
		}
		
	}
}
