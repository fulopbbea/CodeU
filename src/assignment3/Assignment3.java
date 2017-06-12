package assignment3;

import java.util.HashSet;
import java.util.Set;

public class Assignment3 {

    private static void recursiveSearch(char[][] grid, boolean visited[][],
            int width, int height, int startI, int startJ,
            Dictionary dict, String prefix, Set<String> foundWords) {
        
        visited[startI][startJ] = true;
        if (dict.isWord(prefix)) {
            if (!foundWords.contains(prefix)) {
                foundWords.add(prefix);
            }
        }
        int north = startI - 1 < 0 ? 0 : startI - 1;
        int east = startJ + 1 > width - 1 ? width - 1 : startJ + 1;
        int south = startI + 1 > height - 1 ? height - 1 : startI + 1;
        int west = startJ - 1 < 0 ? 0 : startJ - 1;
        
        for (int i = north; i <= south; i++) {
            for (int j = west; j <= east; j++) {
                if (visited[i][j] == false) {
                    String prefixAug = prefix.concat(String.valueOf(grid[i][j]));
                    if (dict.isPrefix(prefixAug)) {
                        Assignment3.recursiveSearch(grid, visited, width, height,
                                i, j, dict, prefixAug, foundWords);
                    }
                }
            }
        }
        visited[startI][startJ] = false;
    }
    
    public static Set<String> findWords(char[][] grid,
            int width, int height, Dictionary dict) {
        
        Set<String> foundWords = new HashSet<>();
        
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (foundWords.size() < dict.size()) {
                    if (dict.isPrefix(String.valueOf(grid[i][j]))) {
                        boolean visited[][] = new boolean[height][width];
                        for (int m = 0; m < height; m++) {
                            for (int n = 0; n < width; n++) {
                                visited[m][n] = false;
                            }
                        }
                        Assignment3.recursiveSearch(grid, visited,
                                width, height, i, j,
                                dict, String.valueOf(grid[i][j]), foundWords);
                    }
                } else {
                    break;
                }
            }
        }
        return foundWords;
    }
    
    public static void main(String[] args) {
    }
    
}
