package service;

import model.Task;
import repository.JsonStorage;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;

public class TaskService {

    List<Task> tasks = new ArrayList<>();
    JsonStorage js = new JsonStorage();

    public TaskService(){
        try {
            tasks = js.loadAll();
        }catch (NoSuchFileException e){
            tasks = new ArrayList<>();
        }catch (IOException e){
            System.out.println("Erro loading data " + e.getMessage());
        }
    }

    public void addTask(String description) {
        int id = nextId();
        tasks.add(new Task(description, id));
        System.out.println("Task added successfully (ID:"+ id+")\n");
        js.saveAll(tasks);
    }

    public void deleteTask(int id) {
        boolean removeIf = tasks.removeIf(task -> task.getId() == id);
        if (removeIf) {
            System.out.println("Task delete");
            js.saveAll(tasks);
            return;
        }
        throw new IllegalArgumentException("Task not found");
    }

    public void updateTask(int id, String description) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setDescription(description);
                System.out.println("Task updated");
                js.saveAll(tasks);
                return;
            }
        }
        throw new IllegalArgumentException("Task not found");
    }

    public void markInProgress(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setStatus("in-progress");
                System.out.println("task successfully marked");
                js.saveAll(tasks);
                return;
            }
        }
        throw new IllegalArgumentException("Task not found");
    }

    public void markIDone(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setStatus("done");
                System.out.println("task successfully marked");
                js.saveAll(tasks);
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
        System.out.println("You no have tasks");
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
