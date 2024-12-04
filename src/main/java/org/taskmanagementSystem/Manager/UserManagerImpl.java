package org.taskmanagementSystem.Manager;

import org.taskmanagementSystem.Entity.Task;
import org.taskmanagementSystem.Entity.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserManagerImpl implements UserManager {
    Map<String, User> users;
    public String loggedInUser = null;

    public UserManagerImpl() {
        this.users = new HashMap<>();
    }
    @Override
    public void createUser(String userId, String inputPassword) {
        if(this.users.containsKey(userId))
            throw new RuntimeException("User already exists");
        User user = new User(userId, hashPassword(inputPassword));
        this.users.put(user.getUserId(), user);
        System.out.println("User created: " + user.getUserId());
    }

    @Override
    public void loginUser(String userId, String inputPassword) {
        if(!this.users.containsKey(userId))
            throw new RuntimeException("User does not exist, please create an account first");
        User user = this.users.get(userId);
        if(hashPassword(inputPassword).equals(user.getHashedPassword())) {
            this.loggedInUser = user.getUserId();
            System.out.println("Logged in user: " + loggedInUser);
        }
        else
            throw new RuntimeException("Wrong password for the user: " + userId);
    }

    @Override
    public List<Task> getCurrentUserWorkLoad(String userId) {
        if(!this.users.containsKey(userId))
            throw new RuntimeException("User does not exist, please create an account first");
        User user = this.users.get(userId);
        if(user.getAssignedTasks() != null)
            return user.getAssignedTasks();
        else
            throw new RuntimeException("User does not have any assigned tasks");
    }

    @Override
    public void addTaskToUser(Task task) {
        User user = this.users.get(this.loggedInUser);
        if(user == null)
            throw new RuntimeException("User does not exist, please create an account first or login");
        user.addAssignedTask(task);
        System.out.println("Task added to user: " + user.getUserId());
    }



    // Hashes the password using SHA-256
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
}
