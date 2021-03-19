package misc.dansprat.trees;

public class MyTest {
   public static void main(String[] args)  {
      BinaryTree<Integer,String> tree=new BinaryTree();
      tree.add(8,"888");
      tree.add(3,"333");
      tree.add(10,"10");
      tree.add(1,"111");
      tree.add(6,"6");
      tree.add(14,"14");
      tree.add(4,"444");
      tree.add(7,"777");
      tree.add(13,"13");
      tree.add(9,"999");

      tree.find(8);
      TreeIterator ti= tree.getIterator();

      while (ti.hasNext())
      {
         BinaryTree.TreeNode leaf=  ti.next();
         System.out.println(leaf.toString());
      }
   }
}

