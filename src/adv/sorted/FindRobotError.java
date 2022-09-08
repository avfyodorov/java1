package adv.sorted;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Автор: Фёдоров Александр
 * Дата:  31.05.2022  10:50
 */
public class FindRobotError {
    List<String> list;

    public void printFile() {
        try {
            list = Files.readAllLines(Path.of("src/adv/sorted/test.txt"));
//            for (String string : list) {
//                System.out.println(string);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void compareRobot() {
        String s;
        for (int i = 0; i < 8; i++) {
            s = list.get(8 + i).equals(list.get(18 + i)) ? " GOD " : " BAD ";
            System.out.println("Строка " + i + s+list.get(8+i));
        }
    }

    public static void main(String[] args) {
        FindRobotError mane = new FindRobotError();
        mane.printFile();
        mane.compareRobot();
    }

}
