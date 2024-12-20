package UserInputOutput;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class UserDataInput {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            // Read user input
            System.out.print("Enter your name: ");
            String name = reader.readLine();

            System.out.print("Enter your phone number: ");
            String phoneNumber = reader.readLine();

            System.out.print("Enter the amount (double): ");
            double amount = Double.parseDouble(reader.readLine());

            System.out.print("Enter the key for your amount: ");
            int key = Integer.parseInt(reader.readLine());

            // Consume the leftover newline character after reading the number
            reader.readLine(); // To handle any remaining newline after reading 'key'

            System.out.print("Enter some details: ");
            String details = reader.readLine();

            // Display the entered data
            System.out.println("\n--- Entered Data ---");
            System.out.println("Name: " + name);
            System.out.println("Phone Number: " + phoneNumber);
            System.out.println("Amount: " + amount);
            System.out.println("Key: " + key);
            System.out.println("Details: " + details);

        } catch (IOException e) {
            System.out.println("An error occurred while reading input: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for amount. Please enter a valid number.");
        }
    }
}
