package service;

import model.Task;
import repository.JsonStorage;

import java.util.ArrayList;
import java.util.List;

public class TaskService {

    List<Task> tasks = new ArrayList<>();
    JsonStorage js = new JsonStorage();

    public void addTask(String description) {
        tasks.add(new Task(description, nextId()));
        js.saveAll(tasks);
        System.out.println("Task added");
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
                task.getStatus("in-progress");
                System.out.println("task successfully marked");
                return;
            }
        }
        throw new IllegalArgumentException("Task not found");
    }

    public void markIDone(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.getStatus("done");
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
            if (task.getStatus().equals(status)) {
                System.out.println(task);
            }
        }
    }
    public int nextId(){
        int nextId = 0;
        for (Task task : tasks){
            if (task.getId()>nextId){
                nextId =task.getId();
            }
        }
        return nextId +1;
    }
}
