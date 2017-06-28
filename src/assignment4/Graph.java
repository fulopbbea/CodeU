package assignment4;

import java.util.ArrayList;

/**
 * Neighbor list representation of an undirected graph.
 */
public class Graph {
    
    private static class GraphNode {

        private final int id;
        private boolean visited;
        private final ArrayList<GraphNode> neighbours = new ArrayList<>();
        
        public GraphNode(int id) {
            this.id = id;
        }
        
        public void addNeighbour(GraphNode neigh) {
            this.neighbours.add(neigh);
        }
        
        public void setVisited() {
            this.visited = true;
        }
        
        public boolean isVisited() {
            return this.visited;
        }
        
        public int getId() {
            return this.id;
        }
        
        public ArrayList<GraphNode> getNeighbours() {
            return this.neighbours;
        }
    }
    
    private final ArrayList<GraphNode> nodes;
    
    public Graph() {
        this.nodes = new ArrayList<>();
        this.nodes.add(0, new GraphNode(0));
        // 0 - default ("no label") value, no need to process it
        this.nodes.get(0).setVisited();
    }
    
    public void addNode(int id) {
        this.nodes.add(id, new GraphNode(id));
    }
    
    public void addEdge(int id1, int id2) {
        this.nodes.get(id1).addNeighbour(this.nodes.get(id2));
        this.nodes.get(id2).addNeighbour(this.nodes.get(id1));
    }
    
    /**
     * Recursive depth first search on a connected component of the graph.
     * 
     * @param node 
     */
    private void depthFirstSearch(GraphNode node) {
        node.setVisited();
        for (GraphNode neighbour : node.getNeighbours()) {
            if (!neighbour.isVisited()) {
                this.depthFirstSearch(neighbour);
            }
        }
    }
    
    /**
     * Computes the number of connected components of the graph by counting the
     * number of the reinitializations of the depth first searches required to traverse
     * the whole graph.
     * 
     * @return total number of connected components of the graph
     */
    public int noOfConnectedComponents() {
        int no = 0;
        for (GraphNode node : this.nodes) {
            if (!node.isVisited()) {
                this.depthFirstSearch(node);
                no++;
            }
        }
        return no;
    }
}
