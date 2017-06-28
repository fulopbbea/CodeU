package assignment4;

public class Assignment4 {

    /**
     * Makes a copy of the map (boolean[][]) used by the functions that destroy the map while processing it.
     * 
     * @param map
     * @param height
     * @param width
     * @return copy of the map
     */
    private static boolean[][] copyMap(boolean[][] map, int height, int width) {
        boolean[][] copy = new boolean[height][width];
        for (int i = 0; i < height; i++) {
            System.arraycopy(map[i], 0, copy[i], 0, width);
        }
        return copy;
    }
    
    /**
     * Recursively fill an island neighbor by neighbor. It deletes the island in the process.
     * 
     * @param map
     * @param height
     * @param width
     * @param i current row index
     * @param j current column index
     */
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
    
    /**
     * Iteratively searches for an island tile in the map, once found it explores the whole island connected to it.
     * 
     * @param map
     * @param height
     * @param width
     * @return total number of islands
     */
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
    
    /**
     * Iteratively processes the map. Once it finds an island tile, it assigns a label to it
     * based on its already processed neighbors (no neighbor: new label; one neighbor:
     * the neighbor's label; two neighbors: the smaller label) and builds an equivalence graph
     * of the labels (two differently labeled neighbors = edge in the graph).
     * 
     * @param map
     * @param height
     * @param width
     * @return total number of islands (connected components of the equivalence graph)
     */
    public static int countIslandsEquivalenceGraph(boolean[][] map, int height, int width) {
        int[][] label = new int[height][width];
        int nextLabel = 0;
        Graph equiv = new Graph();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int upperNeighbour = i - 1;
                int leftNeighbour = j - 1;
                // island tile
                if (map[i][j]) {
                    //upper neighbour exists
                    if (upperNeighbour >= 0) {
                        //upper neighbour not island
                        if (label[upperNeighbour][j] == 0) {
                            // left neighbour exists
                            if (leftNeighbour >= 0) {
                                //neighbours not islands
                                if (label[i][leftNeighbour] == 0) {
                                    //initialize new label
                                    nextLabel++;
                                    label[i][j] = nextLabel;
                                    equiv.addNode(label[i][j]);
                                //left neighbour island, upper not
                                } else {
                                    label[i][j] = label[i][leftNeighbour];
                                }
                            //upper neighbour not island, left doesn't exist
                            } else {
                                nextLabel++;
                                label[i][j] = nextLabel;
                                equiv.addNode(label[i][j]);
                            }
                        //upper neighbour island
                        } else {
                            //left neighbour exists
                            if (leftNeighbour >= 0) {
                                //upper neighbour island, left not
                                if (label[i][leftNeighbour] == 0) {
                                    label[i][j] = label[upperNeighbour][j];
                                //upper and left neighbours islands
                                } else {
                                    label[i][j] = java.lang.Integer.min(label[upperNeighbour][j], label[i][leftNeighbour]);
                                    //add equivalence edge between the two labels
                                    equiv.addEdge(label[upperNeighbour][j], label[i][leftNeighbour]);
                                }
                            //left neighbour doesn't exist, upper is island
                            } else {
                                label[i][j] = label[upperNeighbour][j];
                            }
                        }
                    //upper neighbour doesn't exist
                    } else {
                        //left neighbour exists
                        if (leftNeighbour >= 0) {
                            //left neighbour not island, upper doesn't exist
                            if (label[i][leftNeighbour] == 0) {
                                //initialize new label
                                nextLabel++;
                                label[i][j] = nextLabel;
                                equiv.addNode(label[i][j]);
                            //left neighbour island, upper doesn't exist
                            } else {
                                label[i][j] = label[i][leftNeighbour];
                            }
                        //left neighbour doesn't exist
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
