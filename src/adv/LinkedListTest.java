package adv;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.*;

/**
 * Автор: Фёдоров Александр
 * Дата:  14.02.2024  19:41
 */
public class LinkedListTest {
    /*
    private final Map<Long, Item> items =  new HashMap<>();
    private final Map<Long, List<Item>> userItemIndex = new LinkedHashMap<>();

    private void add(Item item) {
        items.put(item.getItem_id(), item);
        //
        List<Item> user_items = userItemIndex.computeIfAbsent(item.getUser_id(), x -> new ArrayList<>());
        user_items.add(item);
    }

    public List<Item> getByUser(Long user_id){
        return userItemIndex.computeIfAbsent(user_id, z -> new ArrayList<>());
    }

    public static void main(String[] args) {
        LinkedListTest mn = new LinkedListTest();
        mn.add(new Item(101L, "aaa", 1L));
        mn.add(new Item(102L, "bbb", 1L));

        System.out.println(mn.getByUser(1L));
        System.out.println(mn.getByUser(2L));
    }
*/
}
/*
@Getter
@AllArgsConstructor
class Item {
    private final Long item_id;
    private final String name;
    private final Long user_id;
}

*/