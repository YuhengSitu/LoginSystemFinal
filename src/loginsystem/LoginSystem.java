/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package loginsystem;

import java.util.ArrayList;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
/**
 *
 * @author Yuheng
 */
public class LoginSystem {
    private ArrayList<User> userList;// A list to store all users
           
    /**
     * Constructs a new instance of LoginSystem.
     */
    public LoginSystem(){
        this.userList = new ArrayList<>();//Initialize the userList as an empty ArrayList to store users
    }
    
    /**
     * Registers a new user by appending their details to a file and adding them to the user list.  
     * @param user    The user need to register
     * @return        Registration successful or not
     * @throws FileNotFoundException
     * @throws NoSuchAlgorithmException
     */
    public boolean registerUser(User user) throws FileNotFoundException, NoSuchAlgorithmException {
        //check if the name already exists
        if (!isUniqueName(user.getName())) {
            System.out.println("Registration failed: Username already exists.");
            return false;
        }
        //Append to the file "usersList.txt"
        File f = new File("usersList.txt");
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(f, true))){
            // Write user details into the file
            pw.println(user.getName());
            pw.println(encrypt(user.getPassword()));
            pw.println(user.getEmail());
            pw.println(user.getPhoneNum());
            pw.println(user.getAge());
            pw.println(); // Add a blank line between each user
        }
        System.out.println("Registration for " + user.getName() + " is successful");
        userList.add(user); 
        return true;
    }
    
    /**
     * Loads users from a text file into the system(ArrayList: userList)
     * @throws FileNotFoundException
     * @throws NumberFormatException
     */
    public void loadUsers() throws FileNotFoundException, NumberFormatException {
        File f = new File("usersList.txt"); // The file contain the list of users.
        try (Scanner s = new Scanner(f)) {
            while (s.hasNextLine()) {
                // Read each user's information inorder
                String userName = s.nextLine().trim();
                String encryptedPassword  = s.nextLine().trim(); 
                String email = s.nextLine().trim();
                String phoneNum = s.nextLine().trim();
                int age = Integer.parseInt(s.nextLine().trim());
                if (s.hasNextLine()) s.nextLine(); 
                // Create a new User object with the read data and add it to the list of users.
                User user = new User(userName, encryptedPassword , email, phoneNum, age);
                userList.add(user);
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            throw e; // Rethrow the exception
        }  
    }
    
    /**
     * Retrieves the User from the list of users by matching the provided username. 
     * @param userName   The name of the user to search for within the user list
     * @return           The User with the matching name and null if no user is found
     */
    public User getUserByName(String userName) {
        // Loop over the list of users
        for (User user : userList) {
            // Check if the current user's name matches the provided username
            if (user.getName().equals(userName)) {
                return user; 
            }
        }
        return null; // If no match is found after iterating through all users
    }
    
    /**
     * Attempts to Login a user with the provided username and password
     * @param userName
     * @param password
     * @throws NoSuchAlgorithmException
     */
    public void login(String userName, String password) throws NoSuchAlgorithmException {
        // Attempt to fetch the user by the given userName
        User user = getUserByName(userName);
        String encryptedPassword = encrypt(password);

        // Check if the user object is null, indicating the user does not exist
        if (user == null){
            System.out.println("Login failed: User does not exist.");
        }else{
            // If the user exists, compare the encrypted provided password with the user's stored encrypted password
            if (encryptedPassword.equals(user.getPassword())) {
                System.out.println("Login successfully!");
            }else{
                // If the passwords do not match, indicate a wrong password
                System.out.println("Login failed: Wrong password.");
            }
        }
    }
    
    /**
     * Checks if a given username is unique within the system.
     * @param userName
     * @return          True if the username is unique
     * @throws FileNotFoundException
     */
    public boolean isUniqueName(String userName) throws FileNotFoundException {
        if (userList.isEmpty())//Load user in if the list is empty
            loadUsers();
        // Iterate through the userList to check for a match with the given userName
        for (User user : userList) {
            if (user.getName().equals(userName)) {
                return false; // Username is not unique
            }
        }
        return true; // Username is unique
    }
    
    /**
     * Checks if the provided password meets criteria for strength.
     * At least 8 characters long and contains both uppercase and lowercase letters.
     * @param password     The password that needed to check
     * @return             true if the password is strong
     */
    public boolean checkStrongPassword(String password) {
        boolean isStrong = false;
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        // Check for minimum length
        if (password.length() < 8) 
            return isStrong;
        // Check each character of the password
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            // Check if it contain a upper and a lower case letters
            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            }
            if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            }
            // If both conditions are met, the password is strong
            if (hasUpperCase && hasLowerCase) {
                isStrong = true;
                break;
            }
        }
        return isStrong;
    }
    
    /**
     * Encrypts a password using the MD5 hashing algorithm.
     * @param password    password to be encrypted.
     * @return            A hexadecimal string representation of the hashed password
     * @throws NoSuchAlgorithmException
     */
    public String encrypt(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        // Update the digest using the specified array of bytes
        md.update(password.getBytes());
        byte byteData[] = md.digest();// Complete the hash computation
        String encrypedPassword="";
        // Convert each byte in the digest to a two-digit hexadecimal number
        for (int i = 0; i < byteData.length; ++i) {
            encrypedPassword += (Integer.toHexString((byteData[i] & 0xFF) |
            0x100).substring(1,3));
        }
        return encrypedPassword;
    }
    
    /**
     * Performs a binary search on an array of strings to find the specified term.
     * @param term    The string term to search for in the list
     * @param list    The array of strings in which the term is to be searched
     * @return        The index of the term if it is found in the list; otherwise, -1
     */
    public int binarySearch(String term, String[] list) {
        int low = 0;
        int high = list.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int res = term.compareTo(list[mid]);
            // Check if the term is present at mid
            if (res == 0)
                return mid;
            // If term is greater, ignore left half
            else if (res > 0)
                low = mid + 1;
            // If term is smaller, ignore right half
            else
                high = mid - 1;
        }
        return -1;
    }
    
    /**
     * Performs a sequential search on an array of strings to find the specified term
     * @param term    The string term to search for in the list
     * @param list    The array of strings in which the term is to be searched
     * @return        The index of the term if it is found in the list
     */
    public int seqSearch(String term, String[] list) {
        // Loop through each term in the list
        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(term)) {
                return i;
            }
        }
        return -1; 
    }
    
    /**
     * Loads a list of code names from a file into an array of strings
     * @param filename  The file containing the list of code names
     * @return          An array of strings where each element is a line from the file
     * @throws IOException
     */
    public String[] loadCodeNames(String filename) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filename));
        return lines.toArray(new String[0]);
    }
}
