package misc.kadyrovas.graph;


//import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FindUnused {

    public static List<CObject> find(List<CObject> roots, List<CObject> objects) {
        for (CObject object: roots)
            if (object.mark == 0) dsf(object); //Еще не использован

        Predicate<CObject> preLambda = object -> {return object.mark == 0;};
        Stream<CObject> stream = objects.stream().filter(preLambda);
        return stream.collect(Collectors.toList());
    }

    public static void dsf(CObject object) {
        if (object.mark != 0) return;
        object.mark = 1; //Используется в данный момент
        for (CObject cObject: object.references)
            dsf(cObject);
        object.mark = 3; // Объект использован
    }

    public static void addObject (List<CObject>objects, int num, Integer... numbers){
        //Вспомогательный метод. Использовался при отладке
        //находит объект с номером num, создает и добавляет в его references объекты c номерами из numbers
        List<CObject> listObjects = new ArrayList<>(objects);
        for (CObject object: listObjects)
            if (object.number == num)
                for (int i = 0; i < numbers.length; i ++) {
                    object.references.add(new CObject(numbers[i]));
                    objects.add(object.references.get(object.references.size()-1));
                }
    }

    public static void main(String[] args) {
        List<CObject> roots = new ArrayList<>();
        List<CObject> objects = new ArrayList<>();

        CObject object = new CObject(0);
        objects.add(object);
        roots.add(object);

        object = new CObject(12);
        objects.add(object);
        roots.add(object);

        addObject(objects, 0, 1, 8);
        addObject(objects, 1, 2, 3);
        addObject(objects, 2, 6, 7);
        addObject(objects, 3, 4, 5);
        addObject(objects, 8, 9, 30);
        addObject(objects, 9, 10, 11);
        addObject(objects, 12, 13, 14);
        addObject(objects, 13, 15, 19);
        addObject(objects, 15, 16, 17);
        addObject(objects, 17, 18);
        addObject(objects, 19, 20);
        addObject(objects, 14, 21, 22);
        addObject(objects, 21, 23, 24);
        addObject(objects, 22, 25);

        //неиспользуемые
        objects.add(new CObject(26));
        addObject(objects, 26, 27, 28, 29);

        objects.sort(Comparator.comparing(myObject->myObject.number));
        find(roots,objects).forEach(myobject-> System.out.println(myobject.number));
    }
}
