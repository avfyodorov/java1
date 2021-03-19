package misc.kadyrovas.graph;


import java.util.ArrayList;
import java.util.List;

public class CObject {
    public List<CObject> references; // ссылки на другие объекты
    int mark;  // пометка для алгоритма
    int number; // Уникальный номер объекта, используется при отладке
    // 0 - не используется
    // 1 - посещен
    // 2 - используется

    public CObject(int number) {
        this.number = number;
        this.mark = 0;
        references = new ArrayList<>();
    }
}