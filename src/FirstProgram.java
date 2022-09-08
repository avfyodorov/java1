import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirstProgram {

    public static void printJava() {
        System.out.println("На Java работает");
        System.out.println("3 миллиарда смартфонов");
        System.out.println("125 миллионов телевизоров");
        System.out.println("все Blu-Ray проигрыватели");
    }

    public static void printRussia() {
        System.out.println("Площадь России 17125191 квадратных километров");
        System.out.println("Население России 146780720 человек");
        System.out.println("ВВП (ППС) России 28797$ на душу населения");
        System.out.println("ВВП (номинал) России 11289$ на душу населения");
    }
    Map<Integer, String> map = new HashMap<>();

    public static void main(String[] args) {
        printJava();
        printRussia();
        printJava();

//        List<String> list = new ArrayList<>(map.values());

    }
    List<String> getAll(){
        return new ArrayList<>(map.values());

    }
}
