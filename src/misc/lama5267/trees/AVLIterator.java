package misc.lama5267.trees;
import java.util.Iterator;

public class AVLIterator<K extends Comparable<K>, V> implements Iterator<K> {
    AvlTree<K,V>.TreeLeaf<K, V> current;
    AvlTree<K,V>.TreeLeaf<K, V> root;
    boolean leftTree = true;

    public AVLIterator (AvlTree <K, V> avlTree) {
        this.root = avlTree.getRoot();
        current = root;
    }

    private void moveLeft () {
        while (current.left != null) {
            current = current.left;
        }
    }

    @Override
    public boolean hasNext() {
        if (root == null)
            return false;

        // если у корня нет левого поддерева
        if (root.left == null && leftTree) {
            leftTree = false;
            return true;
        } else // иначе в начале уходим по самой левой ветке
            if (current == root && current.left != null && leftTree) {
                moveLeft();
                return true;
            }

        // если у корня нет правого поддерева, то обход закончен
        if (root.right == null && !leftTree)
            return false;

        // если есть правый потомок - уходим в него
        if (current.right != null) {
            current = current.right;
            if (current.left != null) {  // если у него есть левый потомок - уходим по левой ветке
                moveLeft();
                return true;
            }
        } else {
            // иначе возврат наверх
            if (current.parent.right == current) {
                while (current.parent.right == current) {
                    current = current.parent;
                    if (current == root && !leftTree) {  // конец обхода
                        return false;
                    }
                }
            }
            current = current.parent;
            if (current == root && leftTree) {   // переход на правое поддерево
                leftTree = false;
            }
        }
        return true;
    }

    @Override
    public K next() {
        return current.key;
    }
}
