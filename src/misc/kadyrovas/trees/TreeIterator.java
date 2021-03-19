package misc.kadyrovas.trees;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;

public class TreeIterator<K extends Comparable<K>,V> {

    public BinaryTree.TreeLeaf current;
    private boolean beforeRoot;

    public TreeIterator(BinaryTree.TreeLeaf root) {
        this.beforeRoot = true;
        this.current = root;
        while (current.left != null)
            current = current.left;
    }

    private BinaryTree.TreeLeaf findNext() {
        //Поиск следующего узла
        BinaryTree.TreeLeaf current = this.current;
            if (current.right != null)
                return getNext(current.right);
            else if (current.parent.left != null &&
                    current.parent.left.key.compareTo(current.key) == 0) //текущая позиция в левой ветке
                return current.parent;
            else if (current.parent.right != null &&
                    current.parent.right.key.compareTo(current.key) == 0) { //текущая позиция в правой ветке
                return findUp();
            }
        return current;
    }

    public BinaryTree.TreeLeaf next(){
        BinaryTree.TreeLeaf current;
        current = findNext();
        if (current == null)
            return null;
        this.current = current;
        return current;
    }

    public boolean hasNext(){
        if (findNext() == null)
            return false;
        else
            return true;
    }

    private BinaryTree.TreeLeaf findUp() {
        //Поиск родительского узла из правого поддерева, у которого ключ больше текущего
        BinaryTree.TreeLeaf current = this.current;
        while (current.key.compareTo(current.parent.key) > 0) {
            current = current.parent;
            if (current.parent == null)
                return null;
        }
        return current.parent;
    }
    private BinaryTree.TreeLeaf getNext(BinaryTree.TreeLeaf leaf) {
        if (leaf.left == null)
            return leaf;
        return getNext(leaf.left);
    }

    public static void main(String[] args) throws TreeException{
        final int ITERATIONS = 10;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        BinaryTree<Integer, String> tree = new BinaryTree<>();
        for(int i=0; i<ITERATIONS; i++) {
            int key = ThreadLocalRandom.current().nextInt();
            if (!map.containsKey(key)) {
                map.put(key, key);
                tree.add(key, "key=" + key);
            }
        }
        TreeIterator treeIterator = tree.getIterator();
        while (treeIterator.hasNext())
            System.out.println(treeIterator.next());
    }
  }
