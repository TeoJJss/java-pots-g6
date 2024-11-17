
import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class User implements Config {

    private String userId, username, password, status;
    private Role role;
    private static final String FILE_PATH = BASE_DIR + "user.txt";

    // Type of user roles
    private enum Role {
        AM,
        SM,
        PM,
        IM,
        FM
    }

    // default Constructor
    User() {

    }

    // Constructor For login
    User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    // Constructor to create new user account
    User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = Role.valueOf(role);
        this.status = "active";
    }

    // Constructor to be returned after successful login
    private User(String userId, String username, String password, String role, String status) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = Role.valueOf(role);
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role.toString();
    }

    public String getStatus() {
        return status;
    }

    // Get the current user
    public String[] getCurrentUserInfo() {
        String[] userInfo = new String[5];
        userInfo[0] = this.userId;
        userInfo[1] = this.username;
        userInfo[2] = this.password;
        userInfo[3] = this.role.toString();
        userInfo[4] = this.status;

        return userInfo;
    }

    // Generate a unique user ID
    @Override
    public String generateNewId() {
        User[] users = getUserList();
        return "U" + String.valueOf(users.length + 1);
    }

    // Login method
    public User doLogin() {
        User[] users = getUserList();
        for (User user : users) {
            if (user.userId.equals(this.userId) && user.password.equals(this.password) && user.status.equals("active")) {
                return user; // If authentication success, return the user object
            }
        }
        return null;
    }

    // Get user info by ID
    public static User getUserById(String userId) {
        User targetUser = null;

        User[] userList = User.getUserList();

        for (User user : userList) {
            if (user.getUserId().equals(userId)) {
                targetUser = user;
                break;
            }
        }

        return targetUser;
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
                String status = userData[4];
                userArray[index++] = new User(userId, username, password, role, status);
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
        return userArray;
    }

    // Add a new user
    public void addUser() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            String userRole = this.role.toString();
            bw.write(this.generateNewId() + "," + this.username + "," + this.password + "," + userRole + "," + this.status);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to save the updated user list to a file
    public static void saveUserListToFile(User[] users) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (User user : users) {
                bw.write(user.userId + "," + user.username + "," + user.password + "," + user.role + "," + user.status);
                bw.newLine(); // Move to the next line for each user
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Edit user information
    public void editUser(String newUsername, String newPassword) throws Exception {
        User[] users = getUserList();

        boolean updated = false;
        for (User user : users) {
            if (user.userId.equals(this.userId)) {
                user.username = newUsername;
                user.password = newPassword;
                updated = true;
                break;
            }
        }
        if (updated) {
            saveUserListToFile(users);
        } else {
            throw new Exception("Fail to edit user");
        }
    }

    // "Delete" a user by updating their status to "deleted"
    public void deleteUser() throws Exception {
        if (this.userId == null) {
            throw new NullValException();
        }
        User[] users = getUserList();
        boolean deleted = false;
        for (User user : users) {
            if (user.userId.equals(this.userId)) {
                if (user.role.toString().equals("AM")) { // not allowed to delete admin
                    throw new Exception("Cannot delete admin");
                }
                user.status = "deleted"; // Change the status to "deleted"
                deleted = true;
                break;
            }
        }
        if (deleted) {
            saveUserListToFile(users); // Save the updated list to the file
        } else {
            throw new Exception("Fail to delete user");
        }
    }
    
    @Override
    public String toString(){
        return userId + ", " + username + ", " + role;
    }
}
