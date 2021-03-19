package misc.dansprat.trees;


import java.util.function.Consumer;

public class BinaryTree <K extends  Comparable<K>,V> {
    public class TreeNode <K extends Comparable<K>,V>{
        K key;
        V value;
        TreeNode left;
        TreeNode right;
        TreeNode parent;
        Integer height;
        public TreeNode(K key, V value){
            this.key= key;
            this.value= value;
            height=1;
        }

        private TreeNode<K,V> find(K key){
            int cmp = key.compareTo(this.key);
            if (cmp>0){
                if (right !=null){
                    return right.find(key);
                } else {
                    return this;
                }
            }
            if (cmp<0){
                if (left!=null){
                    return left.find(key);
                } else {
                    return this;
                }
            }
            return this;
        }

        void add (TreeNode<K,V> treeNode){
            int cmp = treeNode.key.compareTo(key);
            if (cmp == 0){
                throw new NullPointerException();
            }
            if (cmp >0){
                right =  treeNode;
                treeNode.parent = this;
            } else {
                left = treeNode;
                treeNode.parent=this;
            }
        }
        void delete(){
            if (parent.right == this){
                parent.right = right;
                if (right!=null)
                    right.parent = parent;
                if (left!=null)
                    parent.find(left.key).add(left);
            } else {
                parent.left = left;
                if (left!=null){
                    left.parent = parent;
                }
                if (right!=null){
                    parent.find(right.key).add(right);
                }
            }
        }
        public String toString() { return "Key: "+ key + " Value: "+ value;}

        public void process (Consumer<TreeNode<K,V>>consumer){
            if (left!=null){
                left.process(consumer);
            }
            consumer.accept(this);
            if (right!=null){
                right.process(consumer);
            }
        }

    }

    TreeNode <K,V> head;
    public void process(Consumer<TreeNode<K,V>>consumer){
        head.process(consumer);
    }
    public V find(K key){
        if (head == null){
            throw  new NullPointerException();
        } else {
            TreeNode <K,V> treeNode = head.find(key);
            return treeNode.key.compareTo(key) == 0? treeNode.value : null;
        }
    }
    public void add (K key,V value){
        add(new TreeNode<>(key,value));
    }
    public  void  add (TreeNode<K,V> node){
        if (head == null){
            head  = node;
        } else {
            head.find(node.key).add(node);
        }
    }
    public void delete(K key){
        internalDelete(key);
    }
    public TreeNode<K,V> internalDelete(K key){
        if (head == null){
            throw new NullPointerException();
        }
        TreeNode <K,V> found = head.find(key);
        int cmp = found.key.compareTo(key);
        if (cmp!=0){
            throw  new NullPointerException();
        }
        if (found.parent == null) {
            if (found.right != null) {
                head = found.right;

                if (found.left != null)
                    add(found.left);
            } else if (found.left != null)
                head = found.left;
            else
                head = null;
        } else
            found.delete();
        return found;
    }
    public TreeIterator<K,V> getIterator(){
        return new TreeIterator<K,V>(head);
    }


    public static void main(String[] args) {


    }

}
