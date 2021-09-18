package misc.lama5267.graph;

import java.util.ArrayList;
import java.util.List;

public class FindUnused {

    private enum State {UNUSED, IN_PROCESSING, PROCESSED} /* состояние корня и объекта в куче */

    public static List<CObject> find(List<CObject> roots, List<CObject> objects) {
        if (roots == null || objects == null)
            throw new NullPointerException("Нет объектов для проверки!");

        List<CObject> unused = new ArrayList<>(); /* лист для накопления возвращаемых неиспользуемых объектов */

        for (CObject root : roots)  /* проверяем, если корень неиспользованный, запускаем поиск в глубину */
            if (root.mark == State.UNUSED)
                deepFirstSearch(root);

        for (CObject object : objects)  /* собираем недостижимые (неиспользованные) объекты */
            if (object.mark == State.UNUSED)
                unused.add(object);

        return unused;
    }

    private static void deepFirstSearch(CObject node) {
        node.mark = State.IN_PROCESSING; /* присваиваем объекту состояние "в процессе" */

        for (CObject cObject : node.references) /* обходим все объекты (узлы) */
            if (cObject.mark == State.UNUSED) { /* если объект помечен как неиспользуемый */
                cObject.mark = State.IN_PROCESSING; /* присваиваем объекту состояние "в процессе" */
                deepFirstSearch(cObject); /* вызываем метод рекурсивно с текущим объектом */
            }

        node.mark = State.PROCESSED; /* при обратном ходе рекурсии помечаем все достижимые бъекты как использованные */
    }

    private static class CObject {
        private final List<CObject> references; /* ссылки на другие объекты */
        private String nameNode;
        private State mark = State.UNUSED; /* состояние объекта */
        // UNUSED - не используется
        // IN_PROCESSING - используется
        // PROCESSED - посещен

        public CObject() {
            this.references = new ArrayList<>();
        }

        @Override
        public String toString() {
            return "CObject{" +
                    "nameNode='" + nameNode + '\'' +
                    ", mark=" + mark +
                    '}';
        }
    }


    public static void main(String[] args) {
        FindUnused.CObject object;
        List<CObject> roots = new ArrayList<>();
        List<CObject> objects = new ArrayList<>();

        /* graph objects */
        for (int i = 0; i < 24; i++) {
            object = new FindUnused.CObject();
            object.nameNode = "Object-" + i;
            objects.add(object);
        }
        objects.get(0).references.add(objects.get(1));
        objects.get(1).references.add(objects.get(2));
        objects.get(1).references.add(objects.get(3));
        objects.get(2).references.add(objects.get(3));
        objects.get(3).references.add(objects.get(4));
        objects.get(3).references.add(objects.get(5));

        objects.get(6).references.add(objects.get(7));
        objects.get(7).references.add(objects.get(6));

        objects.get(8).references.add(objects.get(9));
        objects.get(8).references.add(objects.get(11));
        objects.get(8).references.add(objects.get(12));
        objects.get(9).references.add(objects.get(12));
        objects.get(9).references.add(objects.get(10));

        objects.get(13).references.add(objects.get(14));
        objects.get(14).references.add(objects.get(15));
        objects.get(15).references.add(objects.get(13));

        objects.get(16).references.add(objects.get(17));
        objects.get(16).references.add(objects.get(18));
        objects.get(18).references.add(objects.get(17));
        objects.get(17).references.add(objects.get(19));

        objects.get(20).references.add(objects.get(21));
        objects.get(21).references.add(objects.get(22));
        objects.get(22).references.add(objects.get(23));

        /* roots */
        for (int i = 0; i < 3; i++) {
            object = new FindUnused.CObject();
            object.nameNode = "Root-" + i;
            roots.add(object);
        }
        roots.get(0).references.add(objects.get(0));
        roots.get(1).references.add(objects.get(8));
        roots.get(2).references.add(objects.get(16));

        for (CObject cObject : find(roots, objects))
            System.out.println(cObject);

    }
}