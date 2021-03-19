package misc.dansprat.trees;


import java.util.ArrayList;
import java.util.TreeMap;
import java.util.function.Consumer;

public class AvlTree <K extends Comparable<K>,V> {
    public class TreeNode <K extends Comparable<K>,V> {
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

        public int balance(){
            int l;
            int r;
            if (left==null)
                l=0;
            else
                l=left.height;
            if (right==null)
                r=0;
            else
                r = right.height;
            return l-r;
        }


        public TreeNode <K,V> findMin(){
            if (this.left == null){
                return this;
            } else {
                return this.left.findMin();
            }
        }

        public TreeNode<K,V> findMax(){
            if (this.right == null){
                return this;
            } else {
                return this.right.findMax();
            }
        }

        public TreeNode <K,V> deleteMin(){
            TreeNode <K,V> tmp = this.findMin();
            tmp.parent.left = null;
            return tmp;
        }

        public TreeNode <K,V> deleteMax(){
            TreeNode <K,V> tmp = this.findMax();
            tmp.parent.right = null;
            return tmp;
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
                value = treeNode.value;
                return;
            }
            if (cmp >0){
                right =  treeNode;
                treeNode.parent = this;
            } else {
                left = treeNode;
                treeNode.parent=this;
            }
        }
        TreeNode <K,V> delete(){
          if (left !=null || right!=null){
              TreeNode<K,V> tmp;
              if (this.balance()>0){
                  tmp= left.findMin();
              } else {
                  tmp = right.findMax();
              }
              key = tmp.key;
              value = tmp.value;
              if (tmp.parent.left == tmp){
                  tmp.parent.left = null;
              } else {
                  tmp.parent.right = null;
              }
              return tmp;
          } else {
              if(parent.left == this){
                  parent.left = null;
              } else {
                  parent.right = null;
              }
              return this;
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

    private int getHeight(TreeNode <K,V> node){
        if (node == null)
            return 0;
        return node.height;
    }
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
     public void put (K key,V value){
        add(new TreeNode<>(key,value));
     }
     public  void  add (TreeNode<K,V> node){
        if (head == null){
            head  = node;
        } else {
            head.find(node.key).add(node);
            balance(node);
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
            throw new NullPointerException();
        }
        if (found.parent == null) {
            if (found.right != null) {
                head = found.right;
                head.parent=null;
                if (found.left != null)
                    add(found.left);
            } else if (found.left != null) {
                head = found.left;
                head.parent =null;
            }
            else
                head = null;
            balance(head);
        } else {
            TreeNode <K,V> parent = found.delete();
            balance(parent);
        }
        return found;
     }

     private void balance(TreeNode<K,V> node){
        if (node == null){
            return;
        }
        TreeNode <K,V> tmp = node;
         while (tmp.parent!=null){
            tmp = tmp.parent;
            tmp.height = Math.max(getHeight(tmp.left),getHeight(tmp.right))+1;
            if (getHeight(tmp.right) - getHeight( tmp.left)==2){
                if (getHeight(tmp.right.left)<=getHeight(tmp.right.right)){
                    tmp =  smallLeft(tmp);
                    break;
                } else {
                    tmp = bigLeft(tmp);
                    break;
                }
            } else if (getHeight(tmp.left)-getHeight(tmp.right)==2){
                if(getHeight(tmp.left.right) <= getHeight(tmp.left.left)){
                    tmp = smallRight(tmp);
                    break;
                } else {
                    tmp = bigRight(tmp);
                    break;
                }
            }
        }
     }
     public TreeNode <K,V> smallLeft(TreeNode<K,V> a){
         TreeNode <K,V> c = a.right.left;
         TreeNode <K,V> b = a.right;
         a.right = c;
         if (c!=null) {
             c.parent = a;
         }
         if (a.parent!=null){
             if (a == a.parent.left){
                 a.parent.left = b;
             } else {
                 a.parent.right = b;
             }
         }
         else {
             head = b;
         }
         b.left = a;
         b.parent = a.parent;
         a.parent = b;
         a.height = Math.max(getHeight(a.left),getHeight(a.right))+1;
         b.height = Math.max(getHeight(b.right),getHeight(b.left))+1;
         return b;
     }
    public TreeNode <K,V> smallRight(TreeNode<K,V> a){
        TreeNode<K,V> c = a.left.right;
        TreeNode<K,V> b = a.left;
        a.left = c;
        if (c!=null){
            c.parent = a;
        }
        if (a.parent!=null){
            if (a == a.parent.left){
                a.parent.left = b;
            } else {
                a.parent.right = b;
            }
        } else {
            head = b;
        }
        b.right = a;
        b.parent = a.parent;
        a.parent =b;
        a.height = Math.max(getHeight(a.left),getHeight(a.right))+1;
        b.height = Math.max(getHeight(b.right),getHeight(b.left))+1;
        return b;


    }
    public TreeNode <K,V> bigLeft(TreeNode <K,V> a){
        TreeNode<K,V> b = a.right;
        TreeNode<K,V> c = a.right.left;
        a.right = b.left.left;
        if (b.left.left != null) {
            b.left.left.parent = a;
        }
        b.left = b.left.right;
        if (b.left!=null){
            b.left.parent = b;
        }
        c.left = a;
        c.right = b;
        if (a.parent!=null){
            if (a.parent.left == a){
                a.parent.left = c;
            } else {
                a.parent.right = c;
            }
        } else {
            head = c;
        }
        c.parent = a.parent;
        b.parent = c;
        a.parent = c;
        a.height = Math.max(getHeight(a.left),getHeight(a.right))+1;
        b.height = Math.max(getHeight(b.left),getHeight(b.right))+1;
        c.height = Math.max(a.height,b.height)+1;

        return c;
    }
    public TreeNode <K,V> bigRight(TreeNode <K,V> a){
        TreeNode<K,V> b = a.left;
        TreeNode<K,V> c = a.left.right;
        a.left = c.right;
        if (c.right!=null){
            c.right.parent = a;
        }
        b.right = c.left;
        if (c.left!=null){
            c.left.parent = b;
        }
        c.left = b;
        c.right =a;
        b.parent = c;
        if (a.parent!=null){
            if (a.parent.left == a){
                a.parent.left = c;
            } else {
                a.parent.right = c;
            }
        } else {
            head = c;
        }
        c.parent = a.parent;
        a.parent = c;
        a.height = Math.max(getHeight(a.left),getHeight(a.right))+1;
        b.height = Math.max(getHeight(b.left),getHeight(b.right))+1;
        c.height =  Math.max(a.height,b.height)+1;
        return c;
    }
    private boolean isAvl(TreeNode<K,V> node){
        if (node.left!=null){
            boolean is =  isAvl(node.left);
            if(is == false)
                return false;
        }
        if(node.balance()==2 || node.balance()==-2){
            return false;
        }
        if (node.right!=null){
            boolean is = isAvl(node.right);
            if (is == false)
                return false;
        }
        return true;
    }
    public boolean isAvl(){
        return isAvl(head);
    }
    public void change(K oldKey,K newKey){
        TreeNode<K,V> delete = internalDelete(oldKey);
        delete.key = newKey;
        add(delete);
    }

    public static void main(String[] args) {
    }



}
