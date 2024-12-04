package org.taskmanagementSystem.Manager;

import org.taskmanagementSystem.Entity.Story;
import org.taskmanagementSystem.Entity.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class TaskManagerImpl implements TaskManager {
    private Map<String, Task> tasks; // Map of Tasks
    private Map<String, Story> stories; // Map of Stories;

    public TaskManagerImpl() {
        this.tasks = new HashMap<>();
    }


    @Override
    public Task createTask(String taskId, String title, String description, LocalDate deadline) {
        Task task = new Task(taskId, title, description, deadline);
        this.tasks.put(taskId, task);
        System.out.println("Created Task: " + taskId);
        return task;
    }

    @Override
    public Task createSubtask(String parentTaskId, String subtaskId, String title, String description, LocalDate deadline) {
        Task task = this.tasks.get(parentTaskId);
        if(task == null)
            throw new RuntimeException("Task not found");
        Task subTask = new Task(subtaskId, title, description, deadline);
        this.tasks.put(parentTaskId, subTask);
        System.out.println("Created Subtask: " + subtaskId);
        task.addSubTask(subTask);
        return subTask;

    }

    @Override
    public void moveTask(String taskId, String newParentTaskId) {
        if(!this.tasks.containsKey(newParentTaskId))
            throw new RuntimeException("Parent Task not found");
        if(!this.tasks.containsKey(taskId))
            throw new RuntimeException("Task not found");
        Task task = this.tasks.get(taskId);
        Task parentTask = this.tasks.get(newParentTaskId);

        if(hasCycle(task, parentTask))
            throw new RuntimeException("This task assignment would create a circular dependency");
        else{
            for(Task oldParentTask : this.tasks.values()){
                oldParentTask.getSubTasks().remove(task);
            }

            parentTask.addSubTask(task);
        }
    }

    @Override
    public Story createStory(String storyId, String title, String description, LocalDate deadline, List<Task> tasks) {
        Story story = new Story(storyId, title, description, deadline);
        if(this.stories.containsKey(storyId))
            throw new RuntimeException("Story already exists");
        else {
            this.stories.put(storyId, story);
            story.setTasks(tasks);
            System.out.println("Created Story: " + storyId);
        }
        return story;
    }

    public boolean hasCycle(Task task, Task parentTask){
        Set<String> visited = new HashSet<>();
        return dfs(task, parentTask, visited);
    }

    public boolean dfs(Task task, Task parentTask, Set<String> visited){
        if(task == parentTask)
            return true;
        if(visited.contains(task.getTaskId()))
            return false;
        visited.add(task.getTaskId());
        for (Task subTask : task.getSubTasks()) {
            if(dfs(subTask, parentTask, visited)){
                return true;
            }
        }
        return false;
    }
}
