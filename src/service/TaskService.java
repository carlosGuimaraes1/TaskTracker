package service;

import model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskService {

    List<Task> tasks = new ArrayList<>();

    public void addTask(String description) {
        tasks.add(new Task(description));
        // id
        // save
    }

    public void deleteTask(int id) {
        boolean removeIf = tasks.removeIf(task -> task.getId() == id);
        if (removeIf) {
            System.out.println("Task delete");
            return;
        }
        throw new IllegalArgumentException("Task not found");
    }

    public void updateTask(int id, String description) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setDescription(description);
                System.out.println("Task updated");
                return;
            }
        }
        throw new IllegalArgumentException("Task not found");
    }

    public void markInProgress(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setStaus("in-progress");
                System.out.println("task successfully marked");
                return;
            }
        }
        throw new IllegalArgumentException("Task not found");
    }

    public void markIDone(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setStaus("done");
                System.out.println("task successfully marked");
                return;
            }
        }
        throw new IllegalArgumentException("Task not found");
    }

    public void listAllTask() {
        if (!tasks.isEmpty()) {
            System.out.println(tasks);
            return;
        }
        System.out.println("You have no task");
    }

    public void listByStatus(String status) {
        for (Task task : tasks) {
            if (task.getStaus().equals(status)) {
                System.out.println(task);
            }
        }
    }
}
