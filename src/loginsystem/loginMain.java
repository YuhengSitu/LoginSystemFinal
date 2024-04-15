/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginsystem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author yuhen
 */
public class loginMain {

    /**
     * Just some test I made before get in the Jframe
     * @param args
     * @throws FileNotFoundException
     * @throws NoSuchAlgorithmException
     */
    public static void main(String[] args) throws FileNotFoundException, NoSuchAlgorithmException {
        LoginSystem login = new LoginSystem();
        try {
            login.loadUsers();
            
            User user1 = new User("Bruce", "123123Ss", "bruceshi@gmail.com", "1234567890",81);
            
            if (!login.checkStrongPassword(user1.getPassword())){
                System.out.println("The password is too weak");
            }
            else{
                System.out.println("The password is strong");
            }
            
            login.registerUser(user1);
            
        } catch (FileNotFoundException | NoSuchAlgorithmException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
        
        try {
            String[] codeNames = login.loadCodeNames("dictionary.txt");
            String searchTerm = "able"; // Example search term

            // Perform binary search
            int binaryResult = login.binarySearch(searchTerm, codeNames);
            System.out.println("Binary Search Result: " + (binaryResult != -1 ? "Found at index " + binaryResult : "Not Found"));

            // Perform sequential search
            int seqResult = login.seqSearch(searchTerm, codeNames);
            System.out.println("Sequential Search Result: " + (seqResult != -1 ? "Found at index " + seqResult : "Not Found"));

        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }
}
