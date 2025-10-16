package hello;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HelloWorld {
    public static void main(String[] args) {
	    
	    System.out.println("testing");
	    
	    // Demo path traversal vulnerability
	    demonstratePathTraversalVulnerability(args[0]);
	    /*We need only the Password in this method or class*/
	
		/*No need to have SSH Keys*/
		String keypassword = "ihaveremovedmypassword"; 
        /*In case if we need the key */
	
		String apiKey="this-is-a-dummy-key";
	    String username="admin";
	    String password="admin123";
	    System.out.println("Password is: "+password);
	    
		Greeter greeter = new Greeter();
        System.out.println(greeter.sayHello());

	    
    }
    
    /**
     * VULNERABLE METHOD - For demonstration purposes only!
     * This method contains a path traversal vulnerability where user input
     * is not properly validated before being used to access files.
     */
    public static void demonstratePathTraversalVulnerability(String userInput) {
        try {
            // VULNERABILITY: No input validation - allows path traversal attacks
            String baseDirectory = "src/main/resources/";
            String filePath = baseDirectory + userInput;
            
            System.out.println("Attempting to read file: " + filePath);
            
            File file = new File(filePath);
            if (file.exists() && file.isFile()) {
                // Read the file content
                String content = new String(Files.readAllBytes(Paths.get(filePath)));
                System.out.println("File content (first 200 chars): " + 
                    content.substring(0, Math.min(content.length(), 200)));
            } else {
                System.out.println("File not found or is not a regular file: " + filePath);
            }
            
            // Show the actual resolved path to demonstrate the vulnerability
            System.out.println("Actual file path resolved to: " + file.getAbsolutePath());
            
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }
}
