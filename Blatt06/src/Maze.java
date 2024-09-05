import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Class that represents a maze with N*N junctions.
 *
 * @author Vera Röhr
 */
public class Maze {
    private final int N;
    private Graph M;    //Maze
    public int startnode;

    public Maze(int N, int startnode) {

        if (N < 0) throw new IllegalArgumentException("Number of vertices in a row must be nonnegative");
        this.N = N;
        this.M = new Graph(N * N);
        this.startnode = startnode;
        buildMaze();
    }

    public Maze(In in) {
        this.M = new Graph(in);
        this.N = (int) Math.sqrt(M.V());
        this.startnode = 0;
    }


    /**
     * Adds the undirected edge v-w to the graph M.
     *
     * @param  v one vertex in the edge
     * @param  w the other vertex in the edge
     * @throws IllegalArgumentException unless both {@code 0 <= v < V} and {@code 0 <= w < V}
     */
    public void addEdge(int v, int w) {
        // TODO
        /*
        Hier müssen wir zwei Knoten mit einer Kante verbinden
         */
        M.addEdge(v, w);
    }

    /**
     * Returns true if there is an edge between 'v' and 'w'
     * @param v one vertex
     * @param w another vertex
     * @return true or false
     */
    public boolean hasEdge(int v, int w) {
        // TODO
        /*
        Hier prüfen wir, ob zwischen den Knoten eine Kante vorhanden ist.
        Wir erhalten eine Liste benachbarter Scheitelpunkte und prüfen dann, ob unser Knote in der Liste enthalten ist
         */
        if (v == w) {
            return true;
        }
        return M.adj(v).stream().anyMatch(knote -> knote == w);
    }

    /**
     * Builds a grid as a graph.
     * @return Graph G -- Basic grid on which the Maze is built
     */
    public Graph mazegrid() {
        // TODO
        /*
        Hier erstellen wir einen neuen Graphen und verbinden ihn für jeden Scheitelpunkt dieses Graphen,
        wo möglich, mit dem rechten und unteren Scheitelpunkt und so weiter im gesamten Graphen
         */
        Graph graph = new Graph(N * N);

        IntStream.range(0, N * N).forEach(knote -> {
            int row = knote / N;
            int col = knote % N;

            if (col < N - 1) {
                int right_knote = knote + 1;
                graph.addEdge(knote, right_knote);
            }
            if (row < N - 1) {
                int bottom_knote = knote + N;
                graph.addEdge(knote, bottom_knote);
            }
        });
        return graph;
    }

    /**
     * Builds a random maze as a graph.
     * The maze is build with a randomized DFS as the Graph M.
     */
    private void buildMaze() {
        // TODO
        /*
        Hier verwenden wir die Methode mazegrid() und auch die Methode randomDFS() für diese Methode,
        hier mussten wir jedoch zusätzlich das Array (marked) übergeben, da diese Klasse es nicht hat
        */
        Graph G = mazegrid();
        boolean[] marked = new boolean[G.V()];
        randomDFS(G, startnode, marked);
    }

    private void randomDFS(Graph G, int v, boolean[] marked) {
        marked[v] = true;
        List<Integer> knoten = G.adj(v);
        if (knoten != null) {
            Collections.shuffle(knoten, new Random());
            knoten.stream()
                    .filter(w -> !marked[w])
                    .forEach(w -> {
                        addEdge(v, w);
                        randomDFS(G, w, marked);
                    });
        }
    }


    /**
     * Find a path from node v to w
     * @param v start node
     * @param w end node
     * @return List<Integer> -- a list of nodes on the path from v to w (both included) in the right order.
     */
    public List<Integer> findWay(int v, int w) {
        // TODO
        return null;
    }


    /**
     * @return Graph M
     */
    public Graph M() {
        return M;
    }
}
