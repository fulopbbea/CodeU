package assignment2;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TreeTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    
    public Tree tree;
    
    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        this.tree = new Tree(16);
        tree.addNode(9, 16, true);
        tree.addNode(18, 16, false);
        tree.addNode(3, 9, true);
        tree.addNode(14, 9, false);
        tree.addNode(19, 18, false);
        tree.addNode(1, 3, true);
        tree.addNode(5, 3, false);
    }
    
    @After
    public void cleanUp() {
        System.setOut(null);
    }

    @Test
    public void testGetAncestors5() {
        List<Integer> result = this.tree.getAncestors(5);
        assertEquals("Ancestors of 5", Arrays.asList(3, 9, 16), result);
    }
    
    @Test
    public void testGetAncestorsRoot() {
        List<Integer> result = this.tree.getAncestors(16);
        assertEquals("Ancestors of the root", Arrays.asList(), result);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testGetAncestorsNonexistent() {
        this.tree.getAncestors(12);
    }
    
    @Test
    public void testPrintAncestors5() {
        this.tree.printAncestors(5);
        assertEquals("3 9 16 ", outContent.toString());
    }
    
    @Test
    public void testPrintAncestorsRoot() {
        this.tree.printAncestors(16);
        assertEquals("", outContent.toString());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testPrintAncestorsNonexistent() {
        this.tree.printAncestors(12);
        assertEquals("", outContent.toString());
    }
    
    @Test
    public void testGetLowestCommonAncestor514() {
        Optional<Integer> result = this.tree.getLowestCommonAncestor(5, 14);
        assertEquals("Lowest common ancestor of 5 and 14", Optional.of(9), result);
    }
    
    @Test
    public void testGetLowestCommonAncestor145() {
        Optional<Integer> result = this.tree.getLowestCommonAncestor(14, 5);
        assertEquals("Lowest common ancestor of 14 and 5", Optional.of(9), result);
    }
    
    @Test
    public void testGetLowestCommonAncestorSame() {
        Optional<Integer> result = this.tree.getLowestCommonAncestor(14, 14);
        assertEquals("Lowest common ancestor of a node and itself", Optional.of(9), result);
    }
    
    @Test
    public void testGetLowestCommonAncestorChild() {
        Optional<Integer> result = this.tree.getLowestCommonAncestor(9, 14);
        assertEquals("Lowest common ancestor of a child and its parent", Optional.of(16), result);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testGetLowestCommonAncestorNonexistent() {
        this.tree.getLowestCommonAncestor(14, 11);
    }
}
