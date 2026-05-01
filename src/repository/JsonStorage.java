package repository;

import model.Task;

import java.io.BufferedWriter;
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
                + ",\"updatedAt\":\"" + task.getUpdateAt() + "\""
                + "}";
        return json;
    }

    public void saveAll(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(taskToJson(tasks.get(i)));
            if (i < tasks.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("]");

        Path path = Paths.get("tasks.json");

        try (FileWriter fw = new FileWriter(path.toFile());
             BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write(sb.toString());
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Task taskFromJson(String json) {
        int startId = json.indexOf("\"id\":") + "\"id\":".length();
        int endId = json.indexOf(",", startId);
        int id = Integer.parseInt(json.substring(startId, endId));

        int startDescription = json.indexOf("\"description\":") + "\"description\":".length();
        int endDescription = json.indexOf("\"", startDescription + 1);
        String description = json.substring(startDescription + 1, endDescription);

        int startStatus = json.indexOf("\"status\":") + "\"status\":".length();
        int endStatus = json.indexOf("\"", startStatus + 1);
        String status = json.substring(startStatus + 1, endStatus);

        int startCreated = json.indexOf("\"createdAt\":") + "\"createdAt\":".length();
        int endCreated = json.indexOf("\"", startCreated + 1);
        LocalDateTime createdAt = LocalDateTime.parse(json.substring(startCreated + 1, endCreated));

        int startUpdate = json.indexOf("\"updatedAt\":") + "\"updatedAt\":".length();
        int endUpdate = json.indexOf("\"", startUpdate + 1);
        LocalDateTime updateAt = LocalDateTime.parse(json.substring(startUpdate + 1, endUpdate));

        Task task = new Task(description, id);
        task.setStatus(status);
        task.setCreatedAt(createdAt);
        task.setUpdateAt(updateAt);
        return task;
    }

    public List<Task> loadAll() throws IOException {
        Path path = Paths.get("tasks.json");
        List<Task> tasks = new ArrayList<>();
        if (Files.notExists(path)) {
            return tasks;
        }
        String content = new String(Files.readAllBytes(path));
        int pos = 0;
        while (content.indexOf("{", pos) != -1) {
            int start = content.indexOf("{", pos);
            int end = content.indexOf("}", start);
            String json = content.substring(start, end + 1);
            Task task = taskFromJson(json);
            tasks.add(task);
            pos = end + 1;
        }
        return tasks;
    }

}
