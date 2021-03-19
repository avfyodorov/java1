package misc.dhalfin.trees;

import java.util.function.Consumer;

public class AvlTree<K extends Comparable<K>, V> {
    //private static final String KEYDONOTEXIST = "Key is not available";

    class TreeLeaf {
        int height;
        K key;
        V value;
        TreeLeaf parent;
        TreeLeaf left;
        TreeLeaf right;

        public TreeLeaf(K key, V value) {
            this.key = key;
            this.value = value;
        }

        private TreeLeaf find(K key) {
            int cmp = key.compareTo(this.key);
            if (cmp > 0)
                if (right != null)
                    return right.find(key);
                else
                    return this;
            if (cmp < 0)
                if (left != null)
                    return left.find(key);
                else
                    return this;
            return this;
        }

        void put(TreeLeaf leaf) {
            int cmp = leaf.key.compareTo(key);
            if (cmp == 0) {
                this.value = leaf.value;
            } else if (cmp > 0) {
                right = leaf;
                leaf.parent = this;
                leaf.revaluateHeight();
                leaf.makeBalance();
            } else {
                left = leaf;
                leaf.parent = this;
                leaf.revaluateHeight();
                leaf.makeBalance();
            }
        }

        private void revaluateHeight() {
            TreeLeaf node = this;
            while (node != null) {
                int left = node.getHeight(node.left);
                int right = node.getHeight(node.right);
                node.height = Math.max(left, right) + 1;
                node = node.parent;
            }
        }

        int getBalance() {
            return getHeight(left) - getHeight(right);
        }

        private int getHeight(TreeLeaf node) {
            return node == null ? 0 : node.height;
        }

        private void smallRightRotate() {
            TreeLeaf b = left;
            TreeLeaf c = b == null ? null : b.right;
            left = c;
            b.right = this;
            b.parent = parent;
            if (parent != null) {
                if (parent.right == this)
                    parent.right = b;
                else
                    parent.left = b;
            }
            parent = b;
            if (c != null)
                c.parent = this;
            if (root == this)
                root = b;
            revaluateHeight();
        }

        private void smallLeftRotate() {
            TreeLeaf b = right;
            TreeLeaf c = b == null ? null : b.left;
            right = c;
            b.left = this;
            b.parent = parent;
            if (parent != null) {
                if (parent.right == this)
                    parent.right = b;
                else
                    parent.left = b;
            }
            parent = b;
            if (c != null)
                c.parent = this;
            if (root == this)
                root = b;
            revaluateHeight();
        }

        private void bigRightRotate() {
            TreeLeaf b = left;
            TreeLeaf c = b == null ? null : b.right;
            TreeLeaf n = c == null ? null : c.right;
            TreeLeaf m = c == null ? null : c.left;
            left = n;
            b.right = m;
            c.right = this;
            c.left = b;
            c.parent = parent;
            if (parent != null) {
                if (parent.right == this)
                    parent.right = c;
                else
                    parent.left = c;
            }
            parent = c;
            b.parent = c;
            if (n != null)
                n.parent = this;
            if (m != null)
                m.parent = b;
            if (root == this)
                root = c;
            revaluateHeight();
            b.revaluateHeight();
        }

        private void bigLeftRotate() {
            TreeLeaf b = right;
            TreeLeaf c = b.left;
            TreeLeaf n = c == null ? null : c.right;
            TreeLeaf m = c == null ? null : c.left;
            right = m;
            b.left = n;
            c.left = this;
            c.right = b;
            c.parent = parent;
            if (parent != null) {
                if (parent.right == this)
                    parent.right = c;
                else
                    parent.left = c;
            }
            parent = c;
            b.parent = c;
            if (n != null)
                n.parent = b;
            if (m != null)
                m.parent = this;
            if (root == this)
                root = c;
            revaluateHeight();
            b.revaluateHeight();
        }

        private void makeBalance() {
            TreeLeaf node = this;
            while (node != null) {
                node.achieveBalanceInOneNode();
                node = node.parent;
            }
        }

        private void achieveBalanceInOneNode() {
            while (Math.abs(getBalance()) > 1) {
                if (getBalance() > 1) {
                    TreeLeaf b = left;
                    TreeLeaf c = b == null ? null : b.right;
                    assert b != null;
                    if (getHeight(c) <= getHeight(b.left))
                        smallRightRotate();
                    else
                        bigRightRotate();
                } else if (getBalance() < -1) {
                    TreeLeaf b = right;
                    TreeLeaf c = b == null ? null : b.left;
                    assert b != null;
                    if (getHeight(c) <= getHeight(b.right))
                        smallLeftRotate();
                    else
                        bigLeftRotate();
                }
            }
        }

        private TreeLeaf findMinimum() {
            TreeLeaf node = this;
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }

        private TreeLeaf findMaximum() {
            TreeLeaf node = this;
            while (node.right != null) {
                node = node.right;
            }
            return node;
        }

        void delete() {

            if (left != null || right != null) {
                TreeLeaf node = getBalance() > 0 ? left.findMaximum() : right.findMinimum();
                TreeLeaf nodeToStartBalance = this == node.parent ? node : node.parent;
                TreeLeaf subTreeToBePasted = null;
                if (node != right) {
                    if (node.right == null) {
                        node.right = right;
                        if (right != null)
                            right.parent = node;
                    } else
                        subTreeToBePasted = right;
                }
                if (node != left) {
                    if (node.left == null) {
                        node.left = left;
                        if (left != null)
                            left.parent = node;
                    } else
                        subTreeToBePasted = left;
                }
                left = null;
                right = null;
                if (node.parent.right == node)
                    node.parent.right = null;
                else
                    node.parent.left = null;
                node.parent = parent;
                if (parent != null) {
                    if (parent.right == this)
                        parent.right = node;
                    else
                        parent.left = node;
                    parent = null;
                } else {
                    AvlTree.this.root = node;
                }
                if (subTreeToBePasted != null) {
                    TreeLeaf nodeForPaste = node.find(subTreeToBePasted.key);
                    int cmp = subTreeToBePasted.key.compareTo(nodeForPaste.key);
                    if (cmp == 0) {
                        throw new RuntimeException("Error in delete()");
                    } else if (cmp > 0) {
                        nodeForPaste.right = subTreeToBePasted;
                        subTreeToBePasted.parent = nodeForPaste;
                    } else {
                        nodeForPaste.left = subTreeToBePasted;
                        subTreeToBePasted.parent = nodeForPaste;
                    }
                }
                nodeToStartBalance.revaluateHeight();
                nodeToStartBalance.makeBalance();
            } else {
                if (parent != null) {
                    TreeLeaf nodeToStartBalance = parent;
                    if (parent.right == this)
                        parent.right = null;
                    else
                        parent.left = null;
                    parent = null;
                    nodeToStartBalance.revaluateHeight();
                    nodeToStartBalance.makeBalance();
                } else {
                    AvlTree.this.root = null;
                }
            }
        }

        public String toString() {
            return "(" + key + "," + value + ")";
        }

        public void process(Consumer<TreeLeaf> consumer) {
            if (left != null)
                left.process(consumer);
            consumer.accept(this);
            if (right != null)
                right.process(consumer);
        }
    }

    TreeLeaf root;

    public V find(K key) {
        if (root == null)
            return null;
        TreeLeaf found = root.find(key);
        return found.key.compareTo(key) == 0 ? (V) found.value : null;
    }

    public void put(TreeLeaf leaf) {
        if (root == null)
            root = leaf;
        else
            root.find(leaf.key).put(leaf);
    }

    public void put(K key, V value) {
        put(new TreeLeaf(key, value));
    }

    public void delete(K key) throws TreeException {
        internalDelete(key);
    }

    public TreeLeaf internalDelete(K key) throws TreeException {
        if (root == null)
            throw new TreeException("Key is not available");

        TreeLeaf found = root.find(key);
        int cmp = found.key.compareTo(key);
        if (cmp != 0)
            throw new TreeException("Key is not available");
        found.delete();
        return found;
    }

    public void change(K oldKey, K newKey) throws TreeException {
        TreeLeaf current = internalDelete(oldKey);
        current.key = newKey;
        put(current);
    }

    public void process(Consumer<TreeLeaf> consumer) {
        if (root != null)
            root.process(consumer);
    }

    private void printTree() {
        System.out.println("                      " + (root));
        System.out.println("                  " + getChildren(root));
        if (root != null)
            System.out.println("            " + getChildren(root.left) + " " + getChildren(root.right));
        if (root.left != null)
            System.out.print(getChildren(root.left.left) + " " + getChildren(root.left.right));
        if (root.right != null)
            System.out.println("   " + getChildren(root.right.left) + " " + getChildren(root.right.right));

        if (root.left != null && root.left.left != null)
            System.out.print(getChildren(root.left.left.left) + " " + getChildren(root.left.left.right));
        if (root.left != null && root.left.right != null)
            System.out.print(getChildren(root.left.right.left) + " " + getChildren(root.left.right.right));

        if (root.right != null && root.right.left != null)
            System.out.print("   " + getChildren(root.right.left.left) + " " + getChildren(root.right.left.right));
        if (root.right != null && root.right.right != null)
            System.out.println("   " + getChildren(root.right.right.left) + " " + getChildren(root.right.right.right));
        System.out.println("--------------------------");
    }

    private String getChildren(TreeLeaf node) {
        if (node == null)
            return "null    null";
        String left = node.left == null ? "null" : node.left.toString();
        String right = node.right == null ? "null" : node.right.toString();
        return left + " " + right;
    }
}
