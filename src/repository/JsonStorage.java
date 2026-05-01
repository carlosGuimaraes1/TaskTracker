package repository;

import model.Task;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JsonStorage {

    public String taskToJson(Task task) {
        String json = "{" +
                "\"id\":" + task.getId()
                + ",\"description\":\"" + task.getDescription() + "\""
                + ",\"status\":\"" + task.getStatus() + "\""
                + ",\"createdAt\":\"" + task.getCreatedAt() + "\""
                + ",\"updateAt\":\"" + task.getUpdateAt() + "\""
                + "}";
        return json;
    }

    public void saveAll(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Task task : tasks) {
            sb.append(taskToJson(task)).append(",");
            if (task.equals(tasks.get(tasks.size() - 1))) {
                sb.append(taskToJson(task));
            }
        }
        sb.append("]");

        Path path = Paths.get("tasks.json");

        try (FileWriter fw = new FileWriter(path.toFile(), true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            if (Files.notExists(path)) Files.createFile(path);
            bw.write(sb.toString());
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Task taskFromJson(String json) {
        int startId = json.indexOf("\"id\":");
        int endId = json.indexOf(",", startId);
        int id = Integer.parseInt(json.substring(endId - 1, endId));

        int startDescription = json.indexOf("\"description\":") + "\"description\":".length();
        int endDescription = json.indexOf("\"", startDescription + 1);
        String description = json.substring(startDescription + 1, endDescription);

        int startStatus = json.indexOf("\"status\":") + "\"status\":".length();
        int endStatus = json.indexOf("\"", startStatus + 1);
        String status = json.substring(startStatus + 1, endStatus);

        int startCreated = json.indexOf("\"createdAt\":") + "\"createdAt\":".length();
        int endCreated = json.indexOf("\"", startCreated + 1);
        LocalDateTime createdAt = LocalDateTime.parse(json.substring(startCreated + 1, endCreated));

        int startUpdate = json.indexOf("\"updateAt\":") + "\"updateAt\":".length();
        int endUpdate = json.indexOf("\"", startUpdate + 1);
        LocalDateTime updateAt = LocalDateTime.parse(json.substring(startUpdate + 1, endUpdate));

        Task task = new Task(description, id);
        task.setStatus(status);
        task.setCreatedAt(createdAt);
        task.setUpdateAt(updateAt);
        return task;
    }

    public List<Task> loadAll() throws IOException{
        Path path = Paths.get("tasks.json");
        List<Task> tasks = new ArrayList<>();
        if (Files.notExists(path)){
            throw new FileNotFoundException("File not found");
        }
        List<String> strings = Files.readAllLines(path);
        for (String string : strings) {
            Task task = taskFromJson(string);
            tasks.add(task);
        }
        return tasks;

    }

}
