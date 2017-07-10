package assignment5;

import java.util.ArrayList;

public class Node {
    
    private final char letter;
    private final ArrayList<Node> out = new ArrayList<>();
    private Mark mark;
    
    public Node(char letter) {
        this.letter = letter;
        this.mark = Mark.NON;
    }
    
    public void addOut(Node n) {
        this.out.add(n);
    }
    
    public void setMark(Mark m) {
        this.mark = m;
    }
    
    public char getLetter() {
        return this.letter;
    }
    
    public ArrayList<Node> getOut() {
        return this.out;
    }
    
    public Mark getMark() {
        return this.mark;
    }
}
