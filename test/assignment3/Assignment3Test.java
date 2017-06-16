package assignment3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.Assert;
import org.junit.Test;

public class Assignment3Test {
    
    private static final int HEIGHT = 2;
    private static final int WIDTH = 3;
    private static final char[][] GRID = {{'a', 'a', 'r'}, {'t', 'c', 'd'}};

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
        Assert.assertEquals("Visited Order", result,
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
        Assert.assertEquals("Empty Dict", result, new HashSet<>());
    }
    
    @Test
    public void testFindWordsEmptyString() {
        System.out.println("testFindWordsEmptyString");
        Dictionary dict = new Dictionary(Arrays.asList("car", "card", "cart", "cat", ""));
        Set<String> result = Assignment3.findWords(GRID, WIDTH, HEIGHT, dict);
        Assert.assertEquals("Empty String", result,
               new HashSet<>(Arrays.asList("car", "card", "cat")));
    }
    
    @Test
    public void testFindWordsNoWOrdsFound() {
        System.out.println("testFindWordsNoWordsFound");
        Dictionary dict = new Dictionary(Arrays.asList("adat", "radt"));
        Set<String> result = Assignment3.findWords(GRID, WIDTH, HEIGHT, dict);
        Assert.assertEquals("No Words Found", result, new HashSet<>());
    }
    
    @Test
    public void testFindWordsEmptyGrid() {
        System.out.println("testFindWordsEmptyGrid");
        Dictionary dict = new Dictionary(Arrays.asList("car", "card", "cart", "cat", "dart"));
        Set<String> result = Assignment3.findWords(null, 0, 0, dict);
        Assert.assertEquals("Empty Grid", result, new HashSet<>());
    }
}
