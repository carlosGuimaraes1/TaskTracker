package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Task {

    private int id;
    private String description;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

    public Task(String description, int id) {
        this.description = description;
        status = "todo";
        this.id = id;
        this.createdAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", staus='" + status + '\'' +
                ", createdAt=" + createdAt.format(DateTimeFormatter.ISO_LOCAL_DATE) +
                ", updateAt=" + updateAt.format(DateTimeFormatter.ISO_DATE_TIME) +
                '}';
    }
}
