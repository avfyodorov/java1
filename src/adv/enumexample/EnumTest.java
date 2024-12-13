package adv.enumexample;

import java.util.Optional;

/**
 * Автор: Фёдоров Александр
 * Дата:  08.11.2024  14:27
 */
public class EnumTest {
    public static void main(String[] args) {
        TaskType tt = TaskType.TASK;
        TaskType nn = TaskType.to("qyqy");

    }
}

enum TaskType {
    TASK,
    EPIC,
    SUBTASK,
    NONE;

    public static TaskType to(String s){
        try {
            return TaskType.valueOf(s);
        } catch (IllegalArgumentException e) {
            return TaskType.NONE;
        }
    }
    public static Optional<TaskType> from(String stringState) {
        for (TaskType state : values()) {
            if (state.name().equalsIgnoreCase(stringState)) {
                return Optional.of(state);
            }
        }
        return Optional.empty();
    }

}
