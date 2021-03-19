package misc.dansprat.trees;

import java.util.Iterator;
import java.util.Random;


public class TreeIterator <K extends Comparable<K>,V> implements Iterator <BinaryTree<K,V>.TreeNode<K,V>> {
    BinaryTree.TreeNode head;
    BinaryTree.TreeNode current;
    BinaryTree.TreeNode last;

    public TreeIterator (BinaryTree<K,V>.TreeNode<K,V> node){
        head = node;
        BinaryTree.TreeNode tmp=head;
        if (head !=null){
            while (tmp.left!=null){
                tmp = tmp.left;
            }
            current = tmp;
            last = tmp.parent;
        }
    }


    @Override
    public boolean hasNext() {
        return true ? current!=null : false;
    }

    @Override
    public BinaryTree<K, V>.TreeNode<K, V> next() {
        BinaryTree<K, V>.TreeNode<K, V> val;
        val = current;
        if (last == current.left || last ==null){
            if (current.right == null){
                if (current.parent.left ==current || current.parent ==null){
                    last = current;
                    current = current.parent;
                } else {
                    last = current;
                    current=current.parent;
                    while (last == current.parent){
                        if (current.parent==null){
                            if (last == current.right) {
                                current = null;
                                break;
                            }
                        }
                        last = current;
                        current=current.parent;
                    }
                }
            } else {
                last = current;
                current = current.right;
                while (current.left!=null){
                    last =current;
                    current = current.left;
                }
            }

        } else {
            if (last ==current.right) {
                if(current.parent!=null){
                if (current.parent.left == current) {
                    last = current;
                    current = current.parent;
                } else {
                    last = current;
                    current = current.parent;
                    while (last == current.right) {

                        if (current.parent == null) {
                            if (last == current.right) {
                                current = null;
                                break;
                            }
                        }
                        last = current;
                        current = current.parent;
                    }
                }
            }
                else current =null;
            } else {
                if (current.right == null) {
                    last=current;
                    current = current.parent;
                    while (last == current.right) {
                        if (current.parent==null){
                            if (last == current.right) {
                                current = null;
                                break;
                            }
                        }
                        last = current;
                        current = current.parent;
                    }
                } else {
                last = current;
                current = current.right;
                while (current.left != null) {
                    last = current;
                    current = current.left;
                }
            }
            }
        }
        return val;
    }

    public static void main(String[] args) {
        BinaryTree<Integer,Integer> tree = new BinaryTree<>();
        Random generator = new Random(1000);
        for (int i=0;i<10000;i++){
            int i1 =generator.nextInt();
            tree.add(i1,i1);
        }

        TreeIterator<Integer,Integer> it = tree.getIterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }
}
