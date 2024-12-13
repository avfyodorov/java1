import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Автор: Фёдоров Александр
 * Дата:  13.10.2024  17:12
 */
public class Main {
    public static void main(String[] args) {
        Task task = new Task(1, "task1", "qyqy",
                TaskStatus.IN_PROGRESS, Duration.ofMinutes(5), LocalDateTime.now());
        Gson gson = getGson();

        String json = gson.toJson(task);
        System.out.println("Туда - " + json);

        System.out.println("=".repeat(44));

        Task newTask = gson.fromJson(json, Task.class);
        System.out.println("Обратно - " + newTask);

        Map<Integer,Task> tm=new HashMap<>();
        opa();

    }

    private static void opa() {
        List<String> sl = new ArrayList<>();
        sl.add("mmm");
    }

    public static Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter());
        gsonBuilder.registerTypeAdapter(Duration.class, new DurationAdapter());
        return gsonBuilder.create();
    }

}