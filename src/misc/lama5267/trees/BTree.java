package misc.lama5267.trees;


public class BTree<K extends Comparable<K>, V> {
    class Page<K extends Comparable<K>, V> {
        int maxSize;
        V[] values;
        K[] keys;
        Page<K, V>[] children;
        Page<K, V> parent;

        int findKey(K key) {
            for (int i = 0; i < maxSize; i++) {
                if (key.compareTo(keys[i]) <= 0)
                    return i;
            }
            return maxSize;
        }

        int findKey2(K key) {
            int min = 0;
            int max = maxSize - 1;
            int i = max;
            while (max > min) {
                i = (max + min) / 2;
                int cmp = key.compareTo(keys[i]);
                if (cmp == 0)
                    return i;
                if (cmp < 0)
                    max = i;
                else
                    min = i + 1;
            }
            int cmp = key.compareTo(keys[i++]);
            if (cmp > 0)
                i++;
            return i;
        }

        void setItem(K key, V value) {

        }

        void addItem(int pos, K key, V value, Page<K, V> child) {

        }

        boolean isFull() {
            return maxSize == keys.length;
        }

        void moveTo(Page<K,V> page, int from) {
            int n = keys.length-from+1;
            System.arraycopy(page.keys, from, keys, 0, n);
            System.arraycopy(page.values, from, values, 0, n);
            System.arraycopy(page.children, from, children, 0, n+1);
        }

        void splitPage() {
            int middle = keys.length/2+1;
            Page<K, V> newpage = new Page<>();
            newpage.moveTo(this, middle+1);
            parent.addItem(-1, keys[middle], values[middle], newpage);
            maxSize--;
        }
    }

    private Page<K, V> root;

    public V find(K key) {
        Page cur = root;
        while(cur != null) {
            int i = cur.findKey2(key);
            if (i < cur.keys.length && key.compareTo((K)cur.keys[i]) == 0)
                return (V)cur.values[i];
            cur = cur.children[i];
        }
        return null;
    }

    public void add(K key, V value) {
        // check root
        if (root == null || root.isFull()) {
            Page<K, V> newroot = new Page<>();
            if (root != null) {
                root.parent = newroot;
                root.splitPage();
            }
            root = newroot;
        }
        // search to insert
        Page cur = root;
        int i = -1;
        while (cur != null) {
            if (cur.isFull())
                cur.splitPage();
            i = cur.findKey2(key);
            if (i < cur.keys.length && key.compareTo((K) cur.keys[i]) == 0)
                cur.setItem(key, value);
            cur = cur.children[i];
        }
        cur.addItem(i, key, value, null);
    }
}
