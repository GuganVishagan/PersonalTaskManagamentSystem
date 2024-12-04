package org.taskmanagementSystem;

import org.taskmanagementSystem.Entity.Story;
import org.taskmanagementSystem.Entity.Task;
import org.taskmanagementSystem.Manager.TaskManager;
import org.taskmanagementSystem.Manager.TaskManagerImpl;
import org.taskmanagementSystem.Manager.UserManager;
import org.taskmanagementSystem.Manager.UserManagerImpl;

import java.time.LocalDate;
import java.util.List;

public class TaskManagementSystem {

    private final TaskManager taskManager;
    private final UserManager userManager;

    public TaskManagementSystem() {
        this.taskManager = new TaskManagerImpl();
        this.userManager = new UserManagerImpl();
    }

    public void createAccount(String userId, String inputPassword) {
        this.userManager.createUser(userId, inputPassword);
    }

    public void logInUser(String userId, String inputPassword) {
        this.userManager.loginUser(userId, inputPassword);
    }

    public Task createTask(String taskId, String title, String description, LocalDate deadline) {
        Task task = this.taskManager.createTask(taskId, title, description, deadline);
        this.userManager.addTaskToUser(task);
        return task;
    }

    public Task createSubtask(String parentTaskId, String subtaskId, String title, String description, LocalDate deadline){
        Task subtask = this.taskManager.createSubtask(parentTaskId, subtaskId, title, description, deadline);
        this.userManager.addTaskToUser(subtask);
        return subtask;
    }

    public Story createStory(String storyId, String title, String description, LocalDate deadline, List<Task> tasks) {
        return this.taskManager.createStory(storyId, title, description, deadline, tasks);
    }

    public void moveTask(String taskId, String newParentTaskId){
        this.taskManager.moveTask(taskId, newParentTaskId);
    }

    public List<Task> getUserWorkload(String userId) {
        return this.userManager.getCurrentUserWorkLoad((userId));
    }

}
