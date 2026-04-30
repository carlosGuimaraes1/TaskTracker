package view;

import service.TaskService;

public class CommandHandler {
    TaskService taskService = new TaskService();

    public void handleArgs(String[] args) {

        switch (args[0]) {
            case "add":
                handleAdd(args[1]);
                break;
            case "update":
                handleUpdate(args);
                break;
            case "delete":
                handleDelete(args[1]);
                break;
            case "mark-in-progress":
                handleMarkProgress(args[1]);
                break;
            case "mark-done":
                handleMarkDone(args[1]);
                break;
            case "list":
                taskService.listAllTask();
                break;
            case "list done":
                taskService.listByStatus(args[1]);
            default:
                throw new IllegalArgumentException("Command not found");
        }
    }

    private void handleAdd(String args) {
        if (args.isEmpty()) {
            throw new IllegalArgumentException("It is necessary to pass a text");
        }
        taskService.addTask(args);
    }

    private void handleUpdate(String[] args) {
        String sId = "";
        String description = "";
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("update") && i + 1 > args.length) {
                args[i] = sId;
                args[i + 1] = description;
            }
        }
        if (sId.isEmpty() || description.isEmpty()) {
            throw new IllegalArgumentException("It is necessary to pass a text");
        }
        int id = Integer.parseInt(sId);
        taskService.updateTask(id, description);
    }

    private void handleDelete(String args) {
        for (char c : args.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException("It is necessary to pass a number");
            }
        }
        int id = Integer.parseInt(args);
        taskService.deleteTask(id);
    }

    private void handleMarkProgress(String args) {
        for (char c : args.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException("It is necessary to pass a number");

            }
        }
        int id = Integer.parseInt(args);
        taskService.markInProgress(id);
    }

    private void handleMarkDone(String args) {
        for (char c : args.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException("It is necessary to pass a number");
            }
        }
        int id = Integer.parseInt(args);
        taskService.markIDone(id);
    }
}
