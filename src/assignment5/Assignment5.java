package assignment5;

import java.util.ArrayList;
import java.util.List;

public class Assignment5 {

    public static List<Character> findAlphabet(ArrayList<String> dict) throws Exception {
        DAG dag = new DAG();
        dag.buildGraph(dict);
        return dag.topoSort();
    }
    
}
