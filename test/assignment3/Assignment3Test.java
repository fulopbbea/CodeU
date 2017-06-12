package assignment3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class Assignment3Test {
    
    private static final int HEIGHT = 2;
    private static final int WIDTH = 3;
    private static final char[][] GRID = new char[HEIGHT][WIDTH];
    
    @BeforeClass
    public static void setUpClass() {
        Assignment3Test.GRID[0][0] = 'a';
        Assignment3Test.GRID[0][1] = 'a';
        Assignment3Test.GRID[0][2] = 'r';
        
        Assignment3Test.GRID[1][0] = 't';
        Assignment3Test.GRID[1][1] = 'c';
        Assignment3Test.GRID[1][2] = 'd';
    }

    @Test
    public void testFindWordsGeneric() {
        System.out.println("testFindWordsGeneric");
        Dictionary dict = new Dictionary(Arrays.asList("car", "card", "cart", "cat"));
        Set<String> result = Assignment3.findWords(GRID, WIDTH, HEIGHT, dict);
        Assert.assertEquals("Generic test", result,
                new HashSet<>(Arrays.asList("car", "card", "cat")));
    }
    
    @Test
    public void testFindWordsTwoHits() {
        System.out.println("testFindWordsTwoHits");
        Dictionary dict = new Dictionary(Arrays.asList("car", "card", "cart", "cat", "tart"));
        Set<String> result = Assignment3.findWords(GRID, WIDTH, HEIGHT, dict);
        Assert.assertEquals("Two Hits", result,
                new HashSet<>(Arrays.asList("car", "card", "cat")));
    }
    
    @Test
    public void testFindWordsVisitedOrder() {
        System.out.println("testFindWordsVisitedOrder");
        Dictionary dict = new Dictionary(Arrays.asList("car", "card", "cart", "cat", "aca"));
        Set<String> result = Assignment3.findWords(GRID, WIDTH, HEIGHT, dict);
        Assert.assertEquals("Two Hits", result,
                new HashSet<>(Arrays.asList("car", "card", "cat", "aca")));
    }
    
    @Test
    public void testFindWordsInvalidSequence() {
        System.out.println("testFindWordsInvalidSequence");
        Dictionary dict = new Dictionary(Arrays.asList("car", "card", "cart", "cat", "dart"));
        Set<String> result = Assignment3.findWords(GRID, WIDTH, HEIGHT, dict);
        Assert.assertEquals("Invalid Sequence", result,
                new HashSet<>(Arrays.asList("car", "card", "cat")));
    }
    
    @Test
    public void testFindWordsEmptyDict() {
        System.out.println("testFindWordsEmptyDict");
        Dictionary dict = new Dictionary(new ArrayList<>());
        Set<String> result = Assignment3.findWords(GRID, WIDTH, HEIGHT, dict);
        Assert.assertEquals("Empty Dict", result,
                new HashSet<>());
    }
    
    @Test
    public void testFindWordsEmptyString() {
        System.out.println("testFindWordsEmptyString");
        Dictionary dict = new Dictionary(Arrays.asList("car", "card", "cart", "cat", ""));
        Set<String> result = Assignment3.findWords(GRID, WIDTH, HEIGHT, dict);
        Assert.assertEquals("Empty String", result,
               new HashSet<>(Arrays.asList("car", "card", "cat")));
    }
}
