package assignment2;

import static java.lang.Integer.min;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Tree {
    
    private class TreeNode {

        private final int key;
        private TreeNode left;
        private TreeNode right;
        private TreeNode parent;

        /**
         * Constructor. Creates a leaf node.
         * @param key
         * @param parent - pointer to the parent node
         */
        public TreeNode(int key, TreeNode parent) {
            this.key = key;
            this.parent = parent;
            this.left = null;
            this.right = null;
        }

        public int getKey() {
            return this.key;
        }

        public TreeNode getLeftChild() {
            return this.left;
        }

        public TreeNode getRightChild() {
            return this.right;
        }

        public TreeNode getParent() {
            return this.parent;
        }

        public void setLeftChild(TreeNode left) {
            this.left = left;
        }

        public void setRightChild(TreeNode right) {
            this.right = right;
        }

        public void setParent(TreeNode parent) {
            this.parent = parent;
        }
        
        /**
         * Internal recursive tree search function.
         * @param key - key of the node to be search for
         * @return the node with the key key
         */
        public TreeNode findNode(int key) {
            if (this.key == key) {
                return this;
            }
            if (this.left != null) {
                TreeNode leftSearch = this.left.findNode(key);
                if (leftSearch != null) {
                    return leftSearch;
                }
            }
            if (this.right != null) {
                TreeNode rightSearch = this.right.findNode(key);
                if (rightSearch != null) {
                    return rightSearch;
                }
            }
            return null;
        }
    }
    private final TreeNode root;
    
    public Tree(int key) {
        this.root = new TreeNode(key, null);
    }
    
    private TreeNode findNode(int key) {
        return this.root.findNode(key);
    }
    
    /**
     * Adds a new node to the tree. Deletes subtree, if the parent already has a left/right child.
     * @param key
     * @param parentKey - key of the node to which to append the new node
     * @param toLeft - specifies whether the new node will be a left child (true) or not (false)
     */
    public void addNode(int key, int parentKey, boolean toLeft) {
        TreeNode parent = this.findNode(parentKey);
        if (parent != null) {
            if (toLeft) {
                parent.setLeftChild(new TreeNode(key, parent));
            } else {
                parent.setRightChild(new TreeNode(key, parent));
            }
        } else {
            throw new IllegalArgumentException("Node " + parentKey + " not found.");
        }
    }
    
    /**
     * Finds and stores in a list the ancestors of a node by finding the node itself
     * and tracing the path back to the root.
     * @param key
     * @return list of ancestors' keys
     */
    public List<Integer> getAncestors(int key) {
        ArrayList<Integer> ancestors = new ArrayList<>();
        TreeNode node = this.findNode(key);
        if (node != null) {
            for (TreeNode parent = node.getParent(); parent != null; parent = parent.getParent()) {
                ancestors.add(parent.getKey());
            }
            return ancestors;
        } else {
            throw new IllegalArgumentException("Node " + key + " not found.");
        }
    }
    
    /**
     * Prints the ancestors of a node by finding the node itself
     * and tracing the path back to the root.
     * @param key 
     */
    public void printAncestors(int key) {
        TreeNode node = this.findNode(key);
        if (node != null) {
            for (TreeNode parent = node.getParent(); parent != null; parent = parent.getParent()) {
                System.out.print(parent.getKey() + " ");
            }
        } else {
            throw new IllegalArgumentException("Node " + key + " not found.");
        }
    }
    
    /**
     * Finds the lowest common ancestor of two nodes by first getting the list of
     * ancestors of both nodes and then searching for the first common ancestor.
     * @param key1
     * @param key2
     * @return lowest common ancestor of the nodes key1 and key2
     */
    public Optional<Integer> getLowestCommonAncestor(int key1, int key2) {
        List<Integer> ancestors1 = getAncestors(key1);
        List<Integer> ancestors2 = getAncestors(key2);
        int length = min(ancestors1.size(), ancestors2.size());
        int offs1 = ancestors1.size() - length;
        int offs2 = ancestors2.size() - length;
        Optional o = Optional.empty();
        for (int i = 0; i < length; i++) {
            if (Objects.equals(ancestors1.get(offs1 + i), ancestors2.get(offs2 + i))) {
                o = Optional.of(ancestors1.get(offs1 + i));
                break;
            }
        }
        return o;
    }
}
