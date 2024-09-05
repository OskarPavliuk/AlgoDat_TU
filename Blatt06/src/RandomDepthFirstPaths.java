import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class RandomDepthFirstPaths {
    private boolean[] marked;    // marked[v] = is there an s-v path?
    private int[] edgeTo;        // edgeTo[v] = last edge on s-v path
    private final int s;         // source vertex

    /**
     * Computes a path between {@code s} and every other vertex in graph {@code G}.
     * @param G the graph
     * @param s the source vertex
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
     */
    public RandomDepthFirstPaths(Graph G, int s) {
        this.s = s;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        validateVertex(s);
    }

    public void randomDFS(Graph G) {
        randomDFS(G, s);
    }

    // depth first search from v
    private void randomDFS(Graph G, int v) {
        // TODO
        /*
        Diese Methode unterscheidet sich von der üblichen darin, dass wir den Baum nicht unbedingt gemäß der von
        uns definierten Liste durchgehen, sondern in zufälliger Reihenfolge (random)
         */
        marked[v] = true;
        List<Integer> knoten = G.adj(v);
        if (knoten != null) {
            Collections.shuffle(knoten, new Random());
            knoten.stream()
                    .filter(w -> !marked[w])
                    .forEach(w -> {
                        edgeTo[w] = v;
                        randomDFS(G, w);
                    });
        }
    }

    /*
    Hier ist es dasselbe, willkürliche Durchlauf Reihenfolge, aber ohne Rekursion
     */
    public void randomNonrecursiveDFS(Graph G) {
        // TODO
        marked = new boolean[G.V()];
        Iterator<Integer>[] adj = IntStream.range(0, G.V())
                .mapToObj(v -> {
                    List<Integer> knoten = new ArrayList<>(G.adj(v));
                    Collections.shuffle(knoten, new Random());
                    return knoten.iterator();
                })
                .toArray(Iterator[]::new);

        Stack<Integer> stack = new Stack<>();
        marked[s] = true;
        stack.push(s);

        while (!stack.isEmpty()) {
            int v = stack.peek();
            if (adj[v].hasNext()) {
                int w = adj[v].next();
                if (!marked[w]) {
                    marked[w] = true;
                    edgeTo[w] = v;
                    stack.push(w);
                }
            } else {
                stack.pop();
            }
        }
    }

    /**
     * Is there a path between the source vertex {@code s} and vertex {@code v}?
     * @param v the vertex
     * @return {@code true} if there is a path, {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v];
    }

    /**
     * Returns a path between the source vertex {@code s} and vertex {@code v}, or
     * {@code null} if no such path.
     * @param  v the vertex
     * @return the sequence of vertices on a path between the source vertex
     *         {@code s} and vertex {@code v}, as an Iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     *
     * This method is different compared to the original one.
     */
    public List<Integer> pathTo(int v) {
        // TODO
        /*
        Hier prüfen wir zunächst, ob es überhaupt einen Pfad vom Anfangsscheitelpunkt zu unserem gibt.
        Wenn dies der Fall ist, erstellen wir eine Warteschlange und verwenden sie jedes Mal,
        wenn wir einen übergeordneten Scheitelpunkt hinzufügen, bis der übergeordnete Scheitelpunkt
        unser erster Scheitelpunkt wird, und dann können wir unsere Liste zurückgeben
         */
        if (hasPathTo(v) == false) {
            return null;
        }
        Deque<Integer> path = new LinkedList<>();
        Stream.iterate(v, w -> w != s, w -> edgeTo[w])
                .forEach(path::add);
        path.add(s);
        return new LinkedList<>(path);
    }

    public int[] edge() {
        return edgeTo;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

}
