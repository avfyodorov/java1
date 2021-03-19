package misc.lama5267.trees;

import java.util.Iterator;
import java.util.function.Consumer;

public class AvlTree <K extends Comparable<K>, V> implements Iterable<K> {
    private static final String KEYEXIST = "Key already exist";
    private static final String KEYNOTEXIST = "Key not exist";
    enum Operation {PUT, DEL}

    class TreeLeaf <K extends Comparable<K> , V> {
        K key;
        V value;
        TreeLeaf<K,V> parent;
        TreeLeaf<K,V> left;
        TreeLeaf<K,V> right;
        int height;
        int balance;

        public TreeLeaf(K key, V value) {
            this.key = key;
            this.value = value;
        }

        private TreeLeaf<K, V> find(K key) throws TreeException {
            TreeLeaf<K, V> isFind = this;
            int cmp = 0;
            do {
                cmp = key.compareTo(isFind.key);
                if (cmp > 0)
                    if (right != null)
                        isFind = isFind.right;
                    else
                        throw new TreeException(KEYNOTEXIST);
                if (cmp < 0)
                    if (left != null)
                        isFind = isFind.left;
                    else
                        throw new TreeException(KEYNOTEXIST);
            } while (cmp != 0);
            return isFind;
        }

        private TreeLeaf<K, V> findMax () { //если правый не пусто то ищем правый максимальный
            TreeLeaf<K, V> isFind = this;
            while (isFind.right != null)
                isFind = isFind.right;
            return isFind;
        }

        private TreeLeaf<K, V> findMin () {//если левый не пусто ищем левый минемальный
            TreeLeaf<K, V> isFind = this;
            while (isFind.left != null)
                isFind = isFind.left;
            return isFind;
        }

        // поиск замены удаляемому элементу
        private TreeLeaf<K,V> findExchange () {
            TreeLeaf<K,V> node;
            if (balance > 0)
                node = left.findMax();
            else
                node = right.findMin();
            return node;
        }

        // удаление терминального или у кого только один потомок
        private void deleteTerm () {
            balanceOK = false;
            TreeLeaf<K,V> temp;
            if (right == null && left == null)
                temp = null;
            else if (right!=null)
                temp = right;
            else temp = left;

            if (this.equals(root)) {
                root = (AvlTree.TreeLeaf) temp;
                return;
            }

            if (parent.right != null && parent.right.equals(this))
                parent.right = temp;
            else parent.left = temp;
            if (temp != null)
                temp.parent = parent;
            TreeLeaf<K,V> current = parent;
            while (!balanceOK) {
                if (current.equals(root))
                    balanceOK = true;
                current = current.balanceIsNorm(Operation.DEL);
            }
        }

        // проверка баланса
        private TreeLeaf<K, V> balanceIsNorm(Operation oper) {
            newHeight();
            checkBalance();
            if (balance == 2 || balance == -2)
                doBalance();
            else if (balance == 1 || balance == -1) {
                if (oper == Operation.DEL || (oper == Operation.PUT && this.equals(root)))
                    balanceOK = true;
            } else if (balance == 0) {
                if (oper == Operation.PUT)
                    balanceOK = true;
            }
            return parent;
        }

        private void put (TreeLeaf<K, V> leaf) throws TreeException {
            TreeLeaf<K, V> putAt = this;
            boolean putOK = false;
            while (!putOK) {
                int cmp = leaf.key.compareTo(putAt.key);
                if (cmp == 0)
                    throw new TreeException(KEYEXIST);
                if (cmp > 0)
                    if (putAt.right != null) {
                        putAt = putAt.right;
                    } else {
                        putAt.right = leaf;
                        leaf.parent = putAt;
                        putOK = true;
                    }
                else {
                    if (putAt.left != null) {
                        putAt = putAt.left;
                    } else {
                        putAt.left = leaf;
                        leaf.parent = putAt;
                        putOK = true;
                    }
                }
            }
            while (!balanceOK)
                putAt = putAt.balanceIsNorm(Operation.PUT);
        }

        // сделать балансировку
        private void doBalance () {
            if (balance == 2) {
                if (left.balance < 0)
                    left.smallLeft();
                smallRight();
            } else if (balance == -2){
                if (right.balance > 0)
                    right.smallRight();
                smallLeft();
            }
        }

        // правый поворот
        public void smallRight () {
            TreeLeaf<K, V> temp = left;
            left = temp.right;
            temp.right = this;
            if (this.equals(root)) {       // если это корень
                temp.parent = null;
                root = (AvlTree.TreeLeaf) temp;
            } else {
                temp.parent = parent;
                if (parent.right != null && parent.right.equals(this))
                    parent.right = temp;
                else parent.left = temp;
            }
            parent = temp;
            if (left != null)
                left.parent = this;
            heiAndBal();
        }

        // левый поворот
        public void smallLeft () {
            TreeLeaf<K,V> temp = right;
            right = temp.left;
            temp.left = this;
            if (this.equals(root)) {
                temp.parent = null;
                root = (AvlTree.TreeLeaf) temp;
            } else {
                temp.parent = parent;
                if (parent.right != null && parent.right.equals(this))
                    parent.right = temp;
                else parent.left = temp;
            }
            parent = temp;
            if (right != null)
                right.parent = this;
            heiAndBal();
        }

        // пересчет высоты и баланса после поворотов
        public void heiAndBal () {
            newHeight();
            checkBalance();
            parent.newHeight();
            parent.checkBalance();
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

        // пересчет баланса
        public void checkBalance () {
            balance =  right == null ? (left == null ? 0 : left.height - (-1)) : (left == null ? (-1) - right.height : left.height - right.height);
        }

        // пересчет высоты
        public void newHeight () {
            if (left != null && right != null)
                height = (left.height >= right.height) ? left.height + 1 : right.height + 1;
            else
                height = (right == null) ? (left == null ? 0 : left.height + 1) : right.height + 1;
        }
    }

    TreeLeaf<K, V> root;
    boolean balanceOK;

    public V find(K key) throws TreeException {
        if (root == null)
            return null;
        return root.find(key).value;
    }

    public void put (TreeLeaf<K, V> leaf) throws TreeException {
        if (root == null)
            root = leaf;
        else {
            balanceOK = false;
            root.put(leaf);
        }
    }

    public void put (K key, V value) throws TreeException {
        put (new TreeLeaf<>(key, value));
    }

    public void delete(K key) throws TreeException {
        internaldDelete(key);
    }

    public TreeLeaf<K, V> internaldDelete(K key) throws TreeException {
        if (root == null)
            throw new TreeException(KEYNOTEXIST);
        TreeLeaf<K,V> foundDel = root.find(key);
        TreeLeaf<K,V> node;
        if (foundDel.left != null && foundDel.right != null) {  // если это не терминальный узел
            node = foundDel.findExchange();
            node.deleteTerm();
            node.right = foundDel.right;                    //меняем ссылки на потомков
            node.left = foundDel.left;
            if (node.left != null)
                node.left.parent = node;
            if (node.right != null)
                node.right.parent = node;
            if (foundDel.parent == null)
                root = node;
            else if (node.key.compareTo(foundDel.parent.key) > 0)  // меняем у родителя
                foundDel.parent.right = node;
            else
                foundDel.parent.left = node;
            node.parent = foundDel.parent;                 // меняем ссылку на родителя у заменяемого
            node.newHeight();
            node.checkBalance();
        } else                                         // если удаляемый узел - терминальный или один потомок
            foundDel.deleteTerm();
        return foundDel;
    }

    public void change(K oldKey, K newKey) throws TreeException {
        TreeLeaf<K, V> current = internaldDelete(oldKey);
        current.key = newKey;
        put(current);
    }

    public void process(Consumer<TreeLeaf<K,V>> consumer) {
        if (root != null)
            root.process(consumer);
    }

    TreeLeaf<K, V> getRoot() {
        return root;
    }

    public AVLIterator<K,V> getIterator() {
        return new AVLIterator<>(this);
    }

    @Override
    public Iterator<K> iterator() {
        return getIterator();
    }
}