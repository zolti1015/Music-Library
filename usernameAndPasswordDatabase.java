import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;
import java.security.SecureRandom;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class usernameAndPasswordDatabase {


	public usernameAndPasswordDatabase() {
		
	}
	
	// security measures 
	public static String createRandomSalt() {
       Random random = new SecureRandom();
       byte[] salt = new byte[16];
       random.nextBytes(salt);
       return Base64.getEncoder().encodeToString(salt); // random string generated from random byte array
   }
	
	public static String hashPassword(String passwordWithSalt) throws NoSuchAlgorithmException {
		
		MessageDigest digest = MessageDigest.getInstance("SHA-256"); // digest object, has hashing algorithm
        byte[] hashedPassBytes = digest.digest(passwordWithSalt.getBytes()); // turn pass to byte array, call digest (hash)
        return Base64.getEncoder().encodeToString(hashedPassBytes); // turn hashed byte array back to string
	}
	
	public static void writeNewAccountToDatabase(UserAccount account) throws IOException, NoSuchAlgorithmException {
		// use salt and hash
		String file = "usersAndPass.txt";
		FileWriter writer = new FileWriter(file);
		
		String salt = createRandomSalt();
		String saltedPass = account.getPassword() + salt;
		String hashedPassword = hashPassword(saltedPass);
		
		// store username, hashed password, and salt value to a line
		writer.write(account.getUsername() + " " + hashedPassword + salt + "\n");
	}
	
	public static boolean isLoginValid(UserAccount account) throws NoSuchAlgorithmException, IOException {
		// search for username, apply salt to input password and hash, reference hash password we have stored
		File file = new File("usersAndPass.txt");
		FileReader reader;
		BufferedReader database;
		String inputPassword = account.getPassword();
		String inputUsername = account.getUsername();

		reader = new FileReader(file); 
		database = new BufferedReader(reader); // buffered reader object for reading database

		String line;
		while ( (line = database.readLine()) != null) {
			String[] elementList = line.split(" ");
			String username = elementList[0];
			String hashedPassword = elementList[1];
			String saltValue = elementList[2];
			
			String attemptedHash = hashPassword(inputPassword + saltValue);
			
			if (inputUsername.equals(username) && 
				attemptedHash.equals(hashedPassword)) {
					database.close(); return true;
			}
		}
		database.close();
		return false; // line never found, so username wrong or the password doesn't match
	}
}
