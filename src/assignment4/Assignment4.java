package assignment4;

public class Assignment4 {

    private static boolean[][] copyMap(boolean[][] map, int height, int width) {
        boolean[][] copy = new boolean[height][width];
        for (int i = 0; i < height; i++) {
            System.arraycopy(map[i], 0, copy[i], 0, width);
        }
        return copy;
    }
    
    private static void fillIsland(boolean[][] map, int height, int width, int i, int j) {
        map[i][j] = false;
        //North
        if (i - 1 >= 0 && map[i - 1][j]) {
            fillIsland(map, height, width, i - 1, j);
        }
        //East
        if (j + 1 < width && map[i][j + 1]) {
            fillIsland(map, height, width, i, j + 1);
        }
        //South
        if (i + 1 < height && map[i + 1][j]) {
            fillIsland(map, height, width, i + 1, j);
        }
        //West
        if (j - 1 >= 0 && map[i][j - 1]) {
            fillIsland(map, height, width, i, j - 1);
        }
    }
    
    public static int countIslandsFloodFill(boolean[][] map, int height, int width) {
        int noOfIslands = 0;
        boolean[][] mapLocal = copyMap(map, height, width);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (mapLocal[i][j]) {
                    noOfIslands++;
                    fillIsland(mapLocal, height, width, i, j);
                }
            }
        }
        return noOfIslands;
    }
    
    public static int countIslandsEquivalenceGraph(boolean[][] map, int height, int width) {
        int[][] label = new int[height][width];
        int nextLabel = 0;
        Graph equiv = new Graph();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (map[i][j]) {
                    if (i - 1 >= 0) {
                        if (label[i - 1][j] == 0) {
                            if (j - 1 >= 0) {
                                if (label[i][j - 1] == 0) {
                                    //no neighbours
                                    nextLabel++;
                                    label[i][j] = nextLabel;
                                    equiv.addNode(label[i][j]);
                                } else {
                                    //left neigbour
                                    label[i][j] = label[i][j - 1];
                                }
                            } else {
                                //no neighbours
                                nextLabel++;
                                label[i][j] = nextLabel;
                                equiv.addNode(label[i][j]);
                            }
                        } else {
                            if (j - 1 >= 0) {
                                if (label[i][j - 1] == 0) {
                                    //upper neighbour
                                    label[i][j] = label[i - 1][j];
                                } else {
                                    //two neigbours
                                    label[i][j] = java.lang.Integer.min(label[i - 1][j], label[i][j - 1]);
                                    equiv.addEdge(label[i - 1][j], label[i][j - 1]);
                                }
                            } else {
                                //upper neighbour
                                label[i][j] = label[i - 1][j];
                            }
                        }
                    } else {
                        if (j - 1 >= 0) {
                            if (label[i][j - 1] == 0) {
                                //no neighbours
                                nextLabel++;
                                label[i][j] = nextLabel;
                                equiv.addNode(label[i][j]);
                            } else {
                                //left neighbour
                                label[i][j] = label[i][j - 1];
                            }
                        } else {
                            nextLabel++;
                            label[i][j] = nextLabel;
                            equiv.addNode(label[i][j]);
                        }
                    }
                }
            }
        }
        return equiv.noOfConnectedComponents();
    }
}
