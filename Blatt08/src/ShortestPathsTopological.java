import java.util.Arrays;
import java.util.Stack;

public class ShortestPathsTopological {
    private int[] parent;
    private int s;
    private double[] dist;

    /*
    Initialisierung der Anfangswerte
    */
    public ShortestPathsTopological(WeightedDigraph G, int s) {
        // TODO
        this.s = s;
        parent = new int[G.V()];
        dist = new double[G.V()];
        Arrays.fill(parent, -1);
        Arrays.fill(dist, Double.MAX_VALUE);
        dist[s] = 0.0;
        topologicalStruct(G, s);
    }

    /*
    Grundlegende Logik
    */
    private void topologicalStruct(WeightedDigraph G, int s) {
        TopologicalWD topological = new TopologicalWD(G);
        topological.dfs(s);
        Stack<Integer> order = topological.order();
        while (!order.isEmpty()) {
            int knote = order.pop();
            G.incident(knote).forEach(this::relax);
        }
    }

    /*
    Hier haben wir eine Implementierung von relax, ich habe sie aus einem (Isis Blatt08) Video ausgeliehen,
    in dem der Dijkstra-Algorithmus erklärt wurde
    */
    public void relax(DirectedEdge e) {
        // TODO
        int v = e.from();
        int w = e.to();
        if (dist[w] > dist[v] + e.weight()) {
            dist[w] = dist[v] + e.weight();
            parent[w] = v;
        }
    }

    public boolean hasPathTo(int v) {
        return parent[v] >= 0;
    }

    public Stack<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> path = new Stack<>();
        for (int w = v; w != s; w = parent[w]) {
            path.push(w);
        }
        path.push(s);
        return path;
    }
}

