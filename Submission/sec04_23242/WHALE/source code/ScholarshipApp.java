import java.util.Scanner;
import java.io.*;

public class ScholarshipApp {
    static Scanner inp = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        System.out.println("---------- WHALE SCHOLARSHIP ----------");
        System.out.println("Are you a Student or an Administrator?");
        int countAdmin = 0;

        System.out.print("[0]\tStudent\n[1]\tAdministrator\n");
        int choice = inp.nextInt();

        if (choice == 1) {
            System.out.println("Register? (Y/N)");
            char rs = inp.next().toUpperCase().charAt(0);

            if (rs == 'Y') {
                registerAdministrator();
                countAdmin++;
            } else if (rs == 'N') {
                signInAdministrator();
            } else {
                System.out.println("Invalid choice. Please enter Y or N.");
            }
        }

        inp.close();
    }

    private static void registerAdministrator() {
        try {
            System.out.println("---------- Personal Information ----------");
            System.out.print("FIRST NAME:\t");
            String fn = inp.next();
            System.out.print("LAST NAME:\t");
            String ln = inp.next();
            System.out.print("CURRENT AGE:\t");
            int age = inp.nextInt();
            inp.nextLine(); // Consume newline
            System.out.print("EMAIL:\t");
            String email = inp.nextLine();

            System.out.println("---------- Address Information ----------");
            System.out.print("STREET:\t");
            String s = inp.nextLine();
            System.out.print("CITY:\t");
            String ci = inp.nextLine();
            System.out.print("STATE:\t");
            String st = inp.nextLine();

            System.out.println("---------- Administrator Information ----------");
            System.out.print("USERNAME:\t");
            String usname = inp.next();
            inp.nextLine(); // Consume newline
            System.out.print("PASSWORD (Suggest to combine alphabet with numbers and symbols):\t");
            String pass = inp.next();
            inp.nextLine(); // Consume newline

            // Write to File
            PrintWriter outFile = new PrintWriter("Admin" + usname + ".txt");
            outFile.printf("%-20s\t%-20s\n", usname, pass);
            outFile.print(fn + "\t" + ln + "\t" + age + "\t" + email + "\n" + s + ", " + ci + ", " + st);
            outFile.close();
            System.out.println("Administrator information saved successfully.");
        } catch (FileNotFoundException e) {
            System.err.println("Error: Unable to create or write to the file.");
            e.printStackTrace();
        }
    }

    private static void signInAdministrator() {
        System.out.println("---------- Sign In ----------");
        System.out.print("ENTER YOUR USERNAME:\t");
        String us = inp.next();
        System.out.print("ENTER YOUR PASSWORD:\t");
        String pass = inp.next();

        boolean authenticated = false;

        // Try to authenticate with "Admin" + us + ".txt"
        authenticated = authenticateUser("Admin" + us + ".txt", us, pass);

        // If not authenticated and username is "haziq", try "Admintaiman.txt"
        if (!authenticated && us.equals("haziq")) {
            authenticated = authenticateUser("Admintaiman.txt", us, pass);
        }

        if (!authenticated) {
            System.out.println("Authentication failed. Incorrect username or password.");
        }
    }

    private static boolean authenticateUser(String fileName, String username, String password) {
        try {
            File file = new File(fileName);
            Scanner fileScanner = new Scanner(file);

            // Read the username and password from the file
            String fileUsername = fileScanner.next();
            String filePassword = fileScanner.next();

            // Check if the entered credentials match the stored ones
            if (fileUsername.equals(username) && filePassword.equals(password)) {
                System.out.println("Login successful!");

                // Read the remaining details
                String fname = fileScanner.next();
                String lname = fileScanner.next();
                int age = fileScanner.nextInt();
                //fileScanner.nextLine(); // Consume the newline character after age
                String email = fileScanner.nextLine();

                String address = fileScanner.nextLine();

                System.out.println("Full Name: " + fname + " " + lname);
                System.out.println("Age: " + age);
                System.out.println("Email: " + email);

                // Split the address by commas
                String[] addressParts = address.split(",");

                // Trim and assign each part to the corresponding variable
                String street = addressParts[0].trim();
                String cityAndPostalCode = addressParts[1].trim();
                String state = addressParts[2].trim();

                System.out.println("Street: " + street);
                System.out.println("City and Postal Code: " + cityAndPostalCode);
                System.out.println("State: " + state);

                fileScanner.close();
                return true;
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            // If file is not found, continue to next authentication attempt
        }

        return false;
    }
}
