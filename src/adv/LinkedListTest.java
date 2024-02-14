package adv;

import java.util.*;

/**
 * Автор: Фёдоров Александр
 * Дата:  14.02.2024  19:41
 */
public class LinkedListTest {
    Map<Long, Item> items = new HashMap<>();
    Map<Long, List<Item>> userItemIndex = new LinkedHashMap<>();

    void add(Item item) {
        items.put(item.item_id, item);
        //
        final List<Item> user_items = userItemIndex.computeIfAbsent(item.user_id, _ -> new ArrayList<>());
        user_items.add(item);
    }

    List<Item> getByUser(long user_id){
        return userItemIndex.computeIfAbsent(user_id, _ -> new ArrayList<>());
    }

    public static void main(String[] args) {
        LinkedListTest mn = new LinkedListTest();
        mn.add(new Item(101, "aaa", 1));
        mn.add(new Item(102, "bbb", 1));

        System.out.println(mn.getByUser(1L));
        System.out.println(mn.getByUser(2L));
    }
}

class Item {
    long item_id;
    String name;
    long user_id;

    public Item(long item_id, String name, long user_id) {
        this.item_id = item_id;
        this.name = name;
        this.user_id = user_id;
    }
}

