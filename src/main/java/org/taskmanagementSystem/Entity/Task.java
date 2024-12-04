package org.taskmanagementSystem.Entity;

import lombok.Getter;
import lombok.Setter;
import org.taskmanagementSystem.Enum.TaskStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Task {
    private String taskId;
    private String title;
    private String description;
    private LocalDate deadline;
    private User assignedUser;
    List<Task> subTasks;

    public Task(String taskId, String title, String description, LocalDate deadline) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.subTasks = new ArrayList<>();
    }

    public void addSubTask(Task subTask) {
        subTasks.add(subTask);
    }
}
