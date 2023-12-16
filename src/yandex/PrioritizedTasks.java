package yandex;

import java.time.LocalDateTime;
import java.util.*;


/**
 * Автор: Фёдоров Александр
 * Дата:  02.09.2023  14:14
 */
public class PrioritizedTasks {
    private final Comparator<Task> comparableTask = Comparator.comparing(Task::getStart);
    private final Set<Task> taskSet = new TreeSet<>(comparableTask);

    public void addTask(Task task) {
        if (checkTime(task)) {
            taskSet.add(task);
        }
    }

    private boolean checkTime(Task task) {
        for (Task item : taskSet) {
            if (!(task.getFinish().isBefore(item.getStart()) || item.getFinish().isBefore(task.getStart()))) {
                System.out.println("Пересечение по времени");
                return false;
            }
        }
        System.out.println("влазит");
        return true;
    }

    public static void main(String[] args) {
        PrioritizedTasks pt = new PrioritizedTasks();
        pt.addTask(new Task("2023-01-15T19:00:00", "2023-01-15T19:15:00"));
        pt.addTask(new Task("2023-01-15T20:00:00", "2023-01-15T20:15:00"));

        System.out.println("====================");
        pt.checkTime(new Task("2023-01-14T20:00:00", "2023-01-14T20:15:00"));
        pt.checkTime(new Task("2023-01-15T19:30:00", "2023-01-15T20:15:00"));
//        pt.checkTime(new Task("2023-01-15T20:00:00", "2023-01-15T20:15:00"));
//        pt.checkTime(new Task("2023-01-15T20:00:00", "2023-01-15T20:15:00"));
//        pt.checkTime(new Task("2023-01-15T20:00:00", "2023-01-15T20:15:00"));
String N="N".repeat(201);
    }
}

class Task {
    private LocalDateTime start;
    private LocalDateTime finish;
    public Task(String start, String finish) {
        this.start = LocalDateTime.parse(start);
        this.finish = LocalDateTime.parse(finish);
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getFinish() {
        return finish;
    }
}