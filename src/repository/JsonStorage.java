package repository;

import model.Task;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public void saveAll(List<Task> tasks)  {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Task task : tasks) {
            sb.append(taskToJson(task)).append(",");
            if (task.equals(tasks.get(tasks.size()-1))){
                sb.append(taskToJson(task));
            }
        }
        sb.append("]");

        Path path = Paths.get("tasks.json");

        try (FileWriter fw = new FileWriter(path.toFile(), true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            if (Files.notExists(path))Files.createFile(path);
            bw.write(sb.toString());
            bw.newLine();
            bw.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }




}
