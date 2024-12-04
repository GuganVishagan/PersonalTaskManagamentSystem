package org.taskmanagementSystem;

import org.taskmanagementSystem.Entity.Task;

import java.time.LocalDate;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        TaskManagementSystem tms = new TaskManagementSystem();

        tms.createAccount("Vishal", "Vishal@123");
        tms.logInUser("Vishal", "Vishal@123");

        tms.createTask("Workout" , "Workout tips", "Workout ade", LocalDate.of(2024,12,10));
        tms.createTask("Study" , "Study tips", "Study ade", LocalDate.of(2024,12,20));


        Task subtask = tms.createSubtask("Workout", "Coding","Coding of DSA", "Coding description" ,LocalDate.of(2025,1,19));

        List<Task> userTasks = tms.getUserWorkload("Vishal");
        for (Task task : userTasks) {
            System.out.println("Task for Vishal: " + task.getTaskId() + " have deadlines: " + task.getDeadline());
        }

    }
}