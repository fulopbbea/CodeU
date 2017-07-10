package assignment5;

import java.util.ArrayList;
import java.util.List;

/**
 * Directed graph having a node for each character.
 * 
 */
public class DAG {
    
    public final Node[] nodeArray = new Node[256];
    
    /**
     * Builds the DAG recursively based on the given dictionary. It adds the arcs
     * based on the first characters of each word and builds subdicts of words
     * starting with the same character for further recursive processing.
     * @param dict dictionary of words in lexicographical order
     */
    public void buildGraph(ArrayList<String> dict) {
        if (dict == null || dict.isEmpty()) {
            return;
        }
        // subdict of words starting with the same character (first character not stored)
        ArrayList<String> sameFirst = new ArrayList<>();
        if (dict.get(0).length() >= 2) {
            sameFirst.add(dict.get(0).substring(1, dict.get(0).length()));
        }
        //initialize new character
        if (this.nodeArray[dict.get(0).charAt(0)] == null) {
            this.nodeArray[dict.get(0).charAt(0)] = new Node(dict.get(0).charAt(0));
        }
        for (int i = 1; i < dict.size(); i++) {
            //first character different from the one before
            if (dict.get(i - 1).charAt(0) != dict.get(i).charAt(0)) {
                if (this.nodeArray[dict.get(i).charAt(0)] == null) {
                    this.nodeArray[dict.get(i).charAt(0)] = new Node(dict.get(i).charAt(0));
                }
                //add arc
                this.nodeArray[dict.get(i - 1).charAt(0)].addOut(this.nodeArray[dict.get(i).charAt(0)]);
                //process subdict of words beginning with the same character
                this.buildGraph(sameFirst);
                //empty subdict
                sameFirst.clear();
                //add current word to subdic
                if (dict.get(i).length() >= 2) {
                    sameFirst.add(dict.get(i).substring(1, dict.get(i).length()));
                }
            //same first character as before
            } else {
                //add word to subdict
                if (dict.get(i).length() >= 2) {
                    sameFirst.add(dict.get(i).substring(1, dict.get(i).length()));
                }
            }
        }
        //process last subdict
        this.buildGraph(sameFirst);
    }
    
    /**
     * Processes a connected component of the DAG, builds the partially ordered alphabet.
     * @param n Node to be processed
     * @param sorted list of ordered characters (to be built)
     * @throws Exception 
     */
    private void visit(Node n, ArrayList<Character> sorted) throws Exception {
        // node visited twice in the same connected component => cycle => broken dict
        if (n.getMark().equals(Mark.TEMP)) {
            throw new Exception("Not a DAG.");
        }
        // node not yet visited
        if (n.getMark().equals(Mark.NON)) {
            // flag node belonging to the current connected component
            n.setMark(Mark.TEMP);
            // visit all its descendants
            for (Node m : n.getOut()) {
                this.visit(m, sorted);
            }
            // flag node processed
            n.setMark(Mark.PERM);
            // add character to the head of the ordered list
            sorted.add(0, n.getLetter());
        }
    }
    
    /**
     * Computes the alphabet of the language based on the DAG representing the partial order of the characters.
     * Algorithm based on depth-first traversal.
     * @return list of alphabetically ordered characters
     * @throws Exception graph contains a cycle (i.e. contradictory dictionary)
     */
    public List<Character> topoSort() throws Exception {
        ArrayList<Character> sorted = new ArrayList<>();
        for (Node n : this.nodeArray) {
            if (n != null && n.getMark().equals(Mark.NON)) {
                this.visit(n, sorted);
            }
        }
        return sorted;
    }
}
