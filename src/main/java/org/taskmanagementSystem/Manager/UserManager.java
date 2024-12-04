package org.taskmanagementSystem.Manager;

import org.taskmanagementSystem.Entity.Task;
import org.taskmanagementSystem.Entity.User;

import java.util.List;

public interface UserManager {
    public void createUser(String userId, String inputPassword);
    public void loginUser(String userId, String inputPassword);
    public List<Task> getCurrentUserWorkLoad(String userId);
    public void addTaskToUser(Task task);
}
