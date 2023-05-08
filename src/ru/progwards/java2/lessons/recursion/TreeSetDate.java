package ru.progwards.java2.lessons.recursion;

import javax.validation.constraints.Max;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Автор: Фёдоров Александр
 * Дата:  23.03.2023  22:43
 */
public class TreeSetDate {

    static String isFreeTime(Set<Task> taskSet, Task task) {
        return taskSet.stream().noneMatch(
                x -> (x.getStartTime().isBefore(task.getStartTime())
                        && x.getFinishTime().isAfter(task.getStartTime()))
                        || (x.getStartTime().isAfter(task.getStartTime())
                        && x.getStartTime().isBefore(task.getFinishTime()))) ?
                "Можно впихнуть - " + task :
                "Нет места - " + task;
    }

    public static void main(String[] args) {
        Set<Task> taskSet = new TreeSet<>(Comparator.comparing(Task::getStartTime));
        taskSet.add(new Task(LocalDateTime.parse("2023-01-15T20:00:00")));
        taskSet.add(new Task(LocalDateTime.parse("2023-01-15T19:30:00")));
        taskSet.add(new Task(LocalDateTime.parse("2023-01-15T19:45:00")));
        taskSet.add(new Task(LocalDateTime.parse("2023-01-15T19:00:00")));

        taskSet.forEach(System.out::println);

        Task taskGood = new Task(LocalDateTime.parse("2023-01-15T19:15:00"));
        Task taskBad = new Task(LocalDateTime.parse("2023-01-15T19:35:00"));

        System.out.println(isFreeTime(taskSet, taskGood));
        System.out.println(isFreeTime(taskSet, taskBad));

        List<Integer> l=new ArrayList<>(List.of(new Integer(6),new Integer(7)));
        l.remove(Integer.valueOf(7));
        System.out.println(l);

        HashMap<Integer,String>hm=new HashMap<>();
        hm.put(1,"2");
        hm.put(2,"2");

    }
}

class Task {
    private final LocalDateTime startTime;
    private final Duration duration;

    public Task(LocalDateTime startTime) {
        this.startTime = startTime;
        duration = Duration.ofMinutes(15);
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getFinishTime() {
        return startTime.plus(duration);
    }

    @Override
    public String toString() {
        return "Task{" +
                "startTime=" + startTime +
                ", finishTime=" + getFinishTime() +
                '}';
    }

}
