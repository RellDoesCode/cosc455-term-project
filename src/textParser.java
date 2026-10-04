import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class textParser {
	/*Main method, will read the file and print out each character on its own line
	 *the method should be able to detect the end of file
	 */
	public static void main(String[] args) {
		/*The user should manually input the path to the file.*/
		Scanner userScan = new Scanner(System.in);
		
		System.out.println("Please enter the path to your input file, please ensure that the path is NOT encased in quotation marks: ");
		
		String userPath = userScan.nextLine();
		File testFile = new File(userPath);
		/*This try-catch block is used to read the specified file or catch an exception
		 *if the file cannot be found. Extra error handling may be required here.
		 *If the file is found and can be read, then the while loop executes to parse the file
		 *and the for loop nested inside prints each character on its own line.
		 */
		try(Scanner textParser = new Scanner(testFile)) {
			while(textParser.hasNext()) {
				String textOut = textParser.next();
				for (int i = 0; i < textOut.length(); i++) {
					System.out.println(textOut.charAt(i));
				}
			}
		} catch(FileNotFoundException e) {
			System.out.println("ERROR: File not found.");
			e.printStackTrace();
		}
	}
}
