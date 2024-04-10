/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginsystem;

/**
 *
 * @author yuhen
 */
public class User {
    private String userName;
    private String password;
    private String email;
    private int age;
    private String phoneNum;
    
    /**
     * Constructs a new User instance with these 5 information
     * @param userName
     * @param password
     * @param email
     * @param phoneNum
     * @param age
     */
    public User(String userName, String password, String email, String phoneNum, int age){
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.age = age;
        this.phoneNum = phoneNum;
    }
    
    /**
     * Returns the user's username.
     * @return the username of the user
     */
    public String getName(){
        return userName;
    }
    
    /**
     * Returns the user's password.
     * @return the password of the user
     */
    public String getPassword(){
        return password;
    }
    
    /**
     * Returns the user's email address
     * @return the email address of the user
     */
    public String getEmail(){
        return email;
    }
    
    /**
     * Returns the user's age
     * @return the age of the user
     */
    public int getAge(){
        return age;
    }
    
    /**
     * Returns the user's phone number
     * @return the phone number of the user
     */
    public String getPhoneNum(){
        return phoneNum;
    }
}
