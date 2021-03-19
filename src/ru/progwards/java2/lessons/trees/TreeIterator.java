package ru.progwards.java2.lessons.trees;

import java.util.Iterator;
import java.util.Stack;

public class TreeIterator implements Iterator<BinaryTree.TreeLeaf> {

   public BinaryTree.TreeLeaf current;
   Stack<BinaryTree.TreeLeaf> stack = new Stack<>();

   public TreeIterator(BinaryTree.TreeLeaf root) {
      this.current = root;
   }

   @Override
   public boolean hasNext() {
      return (current != null);
   }

   @Override
   public BinaryTree.TreeLeaf next() {
      BinaryTree.TreeLeaf res = current;

      if (current.right != null)
         stack.push(current.right);

      if (current.left != null)
         current = current.left;
      else {
         current = stack.isEmpty() ? null : stack.pop();
      }
      return res;
   }

   public static void main(String[] args) throws TreeException {
      BinaryTree<Integer, String> tree = new BinaryTree();
      tree.add(8, "888");
      tree.add(3, "333");
      tree.add(10, "10");
      tree.add(1, "111");
      tree.add(6, "6");
      tree.add(14, "14");
      tree.add(4, "444");
      tree.add(7, "777");
      tree.add(13, "13");
      tree.add(9, "999");

      TreeIterator ti = tree.getIterator();

      while (ti.hasNext()) {
         BinaryTree.TreeLeaf leaf = ti.next();
         System.out.println(leaf.toString());
      }
   }
}
