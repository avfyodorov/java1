package ru.progwards.java2.lessons.graph;

import java.util.ArrayList;
import java.util.List;

public class CObject {

    public List<CObject> references; // ссылки на другие объекты
    int numberInQueue; // Чтобы можно было использовать PriorityQueue, которая реализована на кучу (быстрый поиск объекта, не прижется создавать еще например стуктуру)
    int mark;  // пометка для алгоритма
    String info; // для дебага
    // 0 - не используется
    // 1 - посещен
    // 2 - используется
    public CObject(String info){
        references = new ArrayList<>();
        this.info = info;
        mark =0;
    }

}
