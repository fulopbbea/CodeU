package assignment2;

public class Assignment2 {

    public static void main(String[] args) {
        Tree tree = new Tree(16);
        tree.addNode(9, 16, Boolean.TRUE);
        tree.addNode(18, 16, Boolean.FALSE);
        tree.addNode(3, 9, Boolean.TRUE);
        tree.addNode(14, 9, Boolean.FALSE);
        tree.addNode(19, 18, Boolean.FALSE);
        tree.addNode(1, 3, Boolean.TRUE);
        tree.addNode(5, 3, Boolean.FALSE);
        System.out.println("Lowest common ancestor: " + tree.getLowestCommonAncestor(3, 5).get());
    }
    
}
