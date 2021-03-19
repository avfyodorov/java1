package misc.dhalfin.trees;

import java.util.ArrayList;
import java.util.Iterator;

public class TreeIterator implements Iterator<BinaryTree.TreeLeaf> {
    private BinaryTree tree;
    private ArrayList<BinaryTree.TreeLeaf> list;
    private int presentInd;
    private BinaryTree.TreeLeaf lastReturned;

    TreeIterator(BinaryTree tree) {
        this.tree = tree;
        list = new ArrayList<>();
        this.tree.process(leaf -> list.add((BinaryTree.TreeLeaf) leaf));
        presentInd = 0;
        lastReturned = null;
    }


    @Override
    public boolean hasNext() {
        return presentInd < list.size();
    }

    @Override
    public BinaryTree.TreeLeaf next() {
        lastReturned = list.get(presentInd++);
        return lastReturned;
    }

    @Override
    public void remove() {
        if (lastReturned == null)
            throw new IllegalStateException();
        try {
            this.tree.delete(lastReturned.key);
        } catch (TreeException e) {
            e.printStackTrace();
        }
        lastReturned = null;
        list.remove(--presentInd);
    }
}
