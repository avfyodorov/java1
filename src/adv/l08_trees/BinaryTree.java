package adv.l08_trees;

import java.util.function.Consumer;
import java.util.function.Function;

public class BinaryTree<K extends Comparable<K>, V> {
    private static final String KEYEXIST = "Key already exist";
    private static final String KEYNOTEXIST = "Key not exist";

    class TreeLeaf<K extends Comparable<K>, V> {
        K key;
        V value;
        TreeLeaf parent;
        TreeLeaf left;
        TreeLeaf right;

        public TreeLeaf(K key, V value) {
            this.key = key;
            this.value = value;
        }

        private TreeLeaf<K,V> find(K key) {
            int cmp = key.compareTo(this.key);
            if (cmp > 0)
                if (right != null)
                    return right.find(key);
                else
                    return this;
            if (cmp < 0)
                if (left != null)
                    return left.find(key);
                else
                    return this;
            return this;
        }

        void add(TreeLeaf<K, V> leaf) throws TreeException {
            int cmp = leaf.key.compareTo(key);
            if (cmp == 0)
                throw new TreeException(KEYEXIST);
            if (cmp > 0) {
                right = leaf;
                leaf.parent = this;
            } else {
                left = leaf;
                leaf.parent = this;
            }
        }

        void delete() throws TreeException {
            if (parent.right == this) {
                parent.right = right;
                if (right != null)
                    right.parent = parent;
                if (left != null)
                    parent.find(left.key).add(left);
            } else {
                parent.left = left;
                if (left != null)
                    left.parent = parent;
                if (right != null)
                    parent.find(right.key).add(right);
            }
        }

        public String toString() {
            return "("+key+","+value+")";
        }

        public void process(Consumer<TreeLeaf<K,V>> consumer) {
            if (left != null)
                left.process(consumer);
            consumer.accept(this);
            if (right != null)
                right.process(consumer);
        }
    }
    private TreeLeaf<K, V> root;

    public V find(K key) {
        if (root == null)
            return null;
        TreeLeaf found = root.find(key);
        return found.key.compareTo(key) == 0 ? (V)found.value : null;
    }

    public void add(TreeLeaf<K, V> leaf) throws TreeException {
        if (root == null)
            root = leaf;
        else
            root.find(leaf.key).add(leaf);
    }

    public void add(K key, V value) throws TreeException {
        add(new TreeLeaf<>(key, value));
    }

    public void delete(K key) throws TreeException {
        internaldDelete(key);
    }

    public TreeLeaf<K, V> internaldDelete(K key) throws TreeException {
        if (root == null)
            throw new TreeException(KEYNOTEXIST);

        TreeLeaf found = root.find(key);
        int cmp = found.key.compareTo(key);
        if (cmp != 0)
            throw new TreeException(KEYNOTEXIST);
        if (found.parent == null) {
            if (found.right != null) {
                root = found.right;
                if (found.left != null)
                    add(found.left);
            } else if (found.left != null)
                root = found.left;
            else
                root = null;
        } else
            found.delete();
        return found;
    }

    public void change(K oldKey, K newKey) throws TreeException {
        TreeLeaf<K, V> current = internaldDelete(oldKey);
        current.key = newKey;
        add(current);
    }

    public void process(Consumer<TreeLeaf<K,V>> consumer) {
        if (root != null)
            root.process(consumer);
    }
}
