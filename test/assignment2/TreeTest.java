package assignment2;

import java.util.ArrayList;
import java.util.Optional;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TreeTest {
    
    public Tree tree;
    public Tree nullTree;
    
    public TreeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.tree = new Tree(16);
        tree.addNode(9, 16, Boolean.TRUE);
        tree.addNode(18, 16, Boolean.FALSE);
        tree.addNode(3, 9, Boolean.TRUE);
        tree.addNode(14, 9, Boolean.FALSE);
        tree.addNode(19, 18, Boolean.FALSE);
        tree.addNode(1, 3, Boolean.TRUE);
        tree.addNode(5, 3, Boolean.FALSE);
        this.nullTree = null;
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetAncestors5() {
        System.out.println("getAncestors5");
        int key = 5;
        ArrayList<Integer> expResult = new ArrayList<>();
        expResult.add(3);
        expResult.add(9);
        expResult.add(16);
        ArrayList<Integer> result = this.tree.getAncestors(key);
        assertEquals("Ancestors of 5", expResult, result);
    }
    
    @Test
    public void testGetAncestorsRoot() {
        System.out.println("getAncestorsRoot");
        int key = 16;
        ArrayList<Integer> expResult = new ArrayList<>();
        ArrayList<Integer> result = this.tree.getAncestors(key);
        assertEquals("Ancestors of the root", expResult, result);
    }

    @Test (expected = KeyNotFoundException.class)
    public void testGetAncestorsNonexistent() {
        System.out.println("getAncestorsNonexistent");
        int key = 12;
        this.tree.getAncestors(key);
    }
    
    @Test
    public void testGetLowestCommonAncestor514() {
        System.out.println("getLowestCommonAncestor514");
        int key1 = 5;
        int key2 = 14;
        Optional<Integer> expResult = Optional.of(9);
        Optional<Integer> result = this.tree.getLowestCommonAncestor(key1, key2);
        assertEquals("Lowest common ancestor of 5 and 14", expResult, result);
    }
    
    @Test
    public void testGetLowestCommonAncestor145() {
        System.out.println("getLowestCommonAncestor145");
        int key1 = 14;
        int key2 = 5;
        Optional<Integer> expResult = Optional.of(9);
        Optional<Integer> result = this.tree.getLowestCommonAncestor(key1, key2);
        assertEquals("Lowest common ancestor of 14 and 5", expResult, result);
    }
    
    @Test
    public void testGetLowestCommonAncestorSame() {
        System.out.println("getLowestCommonAncestorSame");
        int key1 = 14;
        int key2 = 14;
        Optional<Integer> expResult = Optional.of(9);
        Optional<Integer> result = this.tree.getLowestCommonAncestor(key1, key2);
        assertEquals("Lowest common ancestor of 14 and 14", expResult, result);
    }
    
    @Test (expected = KeyNotFoundException.class)
    public void testGetLowestCommonAncestorNonexistent() {
        System.out.println("getLowestCommonAncestorNonexistent");
        int key1 = 14;
        int key2 = 11;this.tree.getLowestCommonAncestor(key1, key2);
    }
}
