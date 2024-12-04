package org.taskmanagementSystem.Manager;

import org.taskmanagementSystem.Entity.Story;
import org.taskmanagementSystem.Entity.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TaskManager {
    public Task createTask(String taskId, String title, String description, LocalDate deadline);
    public Task createSubtask(String parentTaskId, String subtaskId, String title, String description, LocalDate deadline);
    public void moveTask(String taskId, String newParentTaskId);
    public Story createStory(String storyId, String title, String description, LocalDate deadline, List<Task> tasks);
}
