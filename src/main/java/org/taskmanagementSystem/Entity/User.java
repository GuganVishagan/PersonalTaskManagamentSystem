package org.taskmanagementSystem.Entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class User {
    private String userId;
    private String hashedPassword;
    private List<Task> assignedTasks;

    public User(String userId, String hashedPassword) {
        this.userId = userId;
        this.hashedPassword = hashedPassword;
        this.assignedTasks = new ArrayList<>();
    }

    public void addAssignedTask(Task task) {
        assignedTasks.add(task);
    }
}
