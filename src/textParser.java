import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/*This is the driver class for the project.*/
public class textParser {
	/*The main method will be used to read and analyze a user-specified text file.*/
	public static void main(String[] args) throws IOException {
		try (
			/*The user should manually input the path to the file.*/
			Scanner userScan = new Scanner(System.in)) {
				System.out.println("Please enter the path to your input file: ");
				
				String userPath = userScan.nextLine();
				File testFile = new File(userPath);
				/*This try-catch block is used to read the specified file or catch an exception
				 *if the file cannot be found. Extra error handling may be required here.
				 *If the file is found and can be read, then the while loop executes to parse the file.
				 */
				try(Scanner textParser = new Scanner(testFile)) {
					String input = new String(Files.readAllBytes(Paths.get(userPath)));
					Tokenizer detectTokens = new Tokenizer (input);
					tokenTypes token;
					
					/*This do while loop runs until the end of the text file is reached.
					 *It analyzes the file, detects the line and column for where each token begins
					 *and prints the output to the console.
					 */
					do {
						token = detectTokens.scanNext();
						System.out.println(token);
					} while(token.type != Token.EOT);
					
				} catch(FileNotFoundException e) {
					System.out.println("ERROR: File not found.");
					e.printStackTrace();
				}
			}
		}
}
