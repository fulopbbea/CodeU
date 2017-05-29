package assignment2;

import static java.lang.Integer.min;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class Tree {
    
    private class TreeNode {

        private final int key;
        private TreeNode left, right, parent;

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
        
        public TreeNode searchNode(int key) {
            if (this.key == key) {
                return this;
            }
            if (this.left != null) {
                TreeNode leftSearch = this.left.searchNode(key);
                if (leftSearch != null) {
                    return leftSearch;
                }
            }
            if (this.right != null) {
                TreeNode rightSearch = this.right.searchNode(key);
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
    
    private TreeNode searchNode(int key) {
        return this.root.searchNode(key);
    }
    
    public void addNode(int key, int parentKey, Boolean toLeft) {
        TreeNode parent = this.searchNode(parentKey);
        if (parent != null) {
            if (toLeft) {
                parent.setLeftChild(new TreeNode(key, parent));
            } else {
                parent.setRightChild(new TreeNode(key, parent));
            }
        } else {
            throw (new KeyNotFoundException(parentKey));
        }
    }
    
    public ArrayList<Integer> getAncestors(int key) {
        ArrayList<Integer> ancestors = new ArrayList<>();
        TreeNode node = this.searchNode(key);
        if (node != null) {
            node = node.getParent();
            for (; node != null; node = node.getParent()) {
                ancestors.add(node.getKey());
            }
            return ancestors;
        } else {
            throw (new KeyNotFoundException(key));
        }
    }
    
    public Optional<Integer> getLowestCommonAncestor(int key1, int key2) {
        ArrayList<Integer> ancestors1 = getAncestors(key1);
        ArrayList<Integer> ancestors2 = getAncestors(key2);
        int length = min(ancestors1.size(), ancestors2.size());
        int offs1 = ancestors1.size() - length;
        int offs2 = ancestors2.size() - length;
        for (int i = 0; i < length; i++) {
            if (Objects.equals(ancestors1.get(offs1 + i),
                    ancestors2.get(offs2 + i))) {
                return Optional.of(ancestors1.get(offs1 + i));
            }
        }
        return Optional.empty();
    }
}
