/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package degreeassignment;
import java.io.*;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 *
 * @author maxsa
 */
public class User {
    private String userId,username, password, role, status;
    private static User currentUser = null;
    private static final String FILE_PATH = "C:\\Users\\maxsa\\OneDrive - Asia Pacific University\\SEM 5\\Java\\netbeans projects\\DEGREEASSIGNMENT\\src\\degreeassignment\\user.txt";
    
    // Constructor
    public User(String userId, String username, String password, String role, String status) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    // Generate a unique user ID
    public static String generateUserId() {
        User[] users = getUserList();
        return String.valueOf(users.length + 1);
    }

    // Login method
    public static boolean doLogin(String username, String password) {
        User[] users = getUserList();
        for (User user : users) {
            if (user.username.equals(username) && user.password.equals(password)) {
                setCurrentUser(user);
                return true;
            }
        }
        return false;
    }

    // Set the current user
    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    // Get the current user
    public static User getCurrentUser() {
        return currentUser;
    }

    // Get user info by ID
    public static User getUserInfoById(String userId) {
        User[] users = getUserList();
        for (User user : users) {
            if (user.userId.equals(userId)) {
                return user;
            }
        }
        return null;
    }

   // Method to read user data from the text file and store in an array
    public static User[] getUserList() {
        User[] userArray = null;
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            int lines = (int) br.lines().count(); // Count the number of lines in the file
            userArray = new User[lines]; // Create array of appropriate size

            // Reset file pointer to beginning of the file
            br.close();
            BufferedReader newBr = new BufferedReader(new FileReader(FILE_PATH));

            String line;
            int index = 0;
            while ((line = newBr.readLine()) != null) {
                String[] userData = line.split(",");
                String userId = userData[0];
                String username = userData[1];
                String password = userData[2];
                String role = userData[3];
                String status= userData[4];
                userArray[index++] = new User(userId, username, password, role, status);
            }
        } catch (IOException e) {
            e.printStackTrace();
        
        }
        return userArray;
    }
    

    // Add a new user
    public static void addUser(String username, String password, String role, String status) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            bw.write(generateUserId() + "," + username + "," +password + "," + role+","+ status);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Method to save the updated user list to a file
public static void saveUserListToFile(User[] users) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
        for (User user : users) {
            bw.write(user.userId + "," + user.username + "," + user.password + "," + user.role+","+ user.status);
            bw.newLine(); // Move to the next line for each user
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
  // Edit user information
    public static boolean editUser(String userId, String newUsername, String newPassword) {
        User[] users = getUserList();
        boolean updated = false;
        for (User user : users) {
            if (user.userId.equals(userId)) {
                user.username = newUsername;
                user.password = newPassword;
                updated = true;
                break;
            }
        }
        if (updated) {
            saveUserListToFile(users);
        }
        return updated;
    }

// "Delete" a user by updating their status to "deleted"
    public static boolean deleteUser(String userId) {
        User[] users = getUserList();
        boolean deleted = false;
        for (User user : users) {
            if (user.userId.equals(userId)) {
                user.status = "deleted"; // Change the status to "deleted"
                deleted = true;
                break;
            }
        }
        if (deleted) {
            saveUserListToFile(users); // Save the updated list to the file
        }
        return deleted;
    }
}




