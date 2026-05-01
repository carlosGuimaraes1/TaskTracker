# Task Tracker CLI

A simple command-line interface to track and manage your tasks. Built with pure Java and no external libraries.

## Technologies

- Java 17+
- JSON (manual parsing — no external libraries)
- Native File System (`java.nio.file`)

## Project Structure

```
task-tracker/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── tasktracker/
│                   ├── Main.java
│                   ├── model/
│                   │   └── Task.java
│                   ├── repository/
│                   │   └── JsonStorage.java
│                   ├── service/
│                   │   └── TaskService.java
│                   └── cli/
│                       └── CommandHandler.java
├── tasks.json
└── README.md
```

## How to Run

**1. Clone the repository**
```bash
git clone https://github.com/your-username/task-tracker.git
cd task-tracker
```

**2. Compile**
```bash
javac -d out src/main/java/com/tasktracker/**/*.java
```

**3. Run**
```bash
java -cp out com.tasktracker.Main <command> [arguments]
```

## Commands

### Add a task
```bash
java -cp out com.tasktracker.Main add "Buy groceries"
# Output: Task added successfully (ID: 1)
```

### Update a task
```bash
java -cp out com.tasktracker.Main update 1 "Buy groceries and cook dinner"
```

### Delete a task
```bash
java -cp out com.tasktracker.Main delete 1
```

### Mark as in progress
```bash
java -cp out com.tasktracker.Main mark-in-progress 1
```

### Mark as done
```bash
java -cp out com.tasktracker.Main mark-done 1
```

### List all tasks
```bash
java -cp out com.tasktracker.Main list
```

### List by status
```bash
java -cp out com.tasktracker.Main list todo
java -cp out com.tasktracker.Main list in-progress
java -cp out com.tasktracker.Main list done
```

## Task Properties

Each task is stored in `tasks.json` with the following fields:

| Property      | Type   | Description                          |
|---------------|--------|--------------------------------------|
| `id`          | int    | Unique identifier                    |
| `description` | String | Short description of the task        |
| `status`      | String | `todo`, `in-progress` or `done`      |
| `createdAt`   | String | Date and time when task was created  |
| `updatedAt`   | String | Date and time of the last update     |

Example `tasks.json`:
```json
[
  {
    "id": 1,
    "description": "Buy groceries",
    "status": "todo",
    "createdAt": "2026-04-29T10:00:00",
    "updatedAt": "2026-04-29T10:00:00"
  }
]
```

## Technical Decisions

- **JSON persistence:** Tasks are stored in a `tasks.json` file created automatically on first run. JSON is parsed manually using `String.indexOf()` and `String.substring()` — no external libraries used.
- **ID generation:** The next ID is calculated by finding the highest existing ID in the list and adding 1.
- **Architecture:** The project follows a layered architecture separating model, storage, service, and CLI concerns.

## Author

Carlos — [GitHub](https://github.com/your-username)
