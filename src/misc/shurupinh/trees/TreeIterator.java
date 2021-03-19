package misc.shurupinh.trees;

import java.util.ArrayList;
import java.util.Iterator;

public class TreeIterator<K extends Comparable<K>, V> implements Iterator {
    K key;
    V value;
    BinaryTree.TreeLeaf tree;
    ArrayList<BinaryTree.TreeLeaf> sorted = new ArrayList<>();
    int indx;


    public TreeIterator(BinaryTree.TreeLeaf root) {
        this.tree = root;
        indx = 0;
        process(tree);
    }


    @Override
    public boolean hasNext() {

        return indx < sorted.size();
    }

    @Override
    public Object next() {


        return sorted.get(indx++);

    }


    public void process(BinaryTree.TreeLeaf tree) {
        if (tree != null) {
            if (tree.left != null) process(tree.left);
            sorted.add(tree);
            if (tree.right != null)
                process(tree.right);
        }

    }


 /*   public static void main(String[] args) {
        BinaryTree<Integer, String> test = new BinaryTree<>();

        for (int i = 0; i < 10; i++) {
            Integer tt=(int)(Math.random()*100);
            try {
                test.add(tt,"gfgdf//"+tt);
            } catch (TreeException e) {
                e.printStackTrace();
            }
        }
        Iterator it= test.getIterator();

        while (it.hasNext()){
            System.out.println(it.next().toString());
        }

    }*/
}

