package org.taskmanagementSystem.Entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Story {
    private String storyId;
    private String title;
    private String description;
    private LocalDate deadline;
    List<Task> tasks;
    public Story(String storyId, String title, String description, LocalDate deadline) {
        this.storyId = storyId;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void removeTask(Task task) {
        this.tasks.remove(task);
    }

}
