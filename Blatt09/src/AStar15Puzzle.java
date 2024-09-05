import java.util.PriorityQueue;
import java.util.stream.StreamSupport;

public class AStar15Puzzle {

    /**
     * Finds a shortest solution to a given sliding puzzle using the A* algorithm with the
     * Manhattan distance (aka taxicab metric) between the current and the goal configuration
     * as heuristic.
     *
     * @param board The initial puzzle configuration that should be solved
     * @return a {@link PartialSolution} object which holds a shortest solution in its <em>moveSequence</em>
     */
    /*
    Den Pseudocode habe ich mir aus der Vorlesung ausgeliehen (Seite 31)
    */
    public static PartialSolution solveByAStar(Board board) {
        // TODO 3.3 solveByAStar
        PriorityQueue<PartialSolution> Q = new PriorityQueue<PartialSolution>();
        PartialSolution esol = new PartialSolution(board);
        Q.add(esol);
        while (!Q.isEmpty()) {
            PartialSolution psol = Q.poll();
            if (psol.isSolution()) {
                return psol;
            }
            Q.addAll(
                    StreamSupport.stream(psol.validMoves().spliterator(), false)
                            .map(move -> {
                                PartialSolution psolnext = new PartialSolution(psol); // Створюємо копію часткового рішення
                                psolnext.doMove(move); // Виконуємо хід
                                return psolnext;
                            })
                            .toList()
            );
        }
        return null;
    }


    public static void printBoardSequence(Board board, Iterable<Move> moveSequence) {
        int moveno = 0;
        for (Move move : moveSequence) {
            System.out.println("Manhattan metric: " + board.manhattan() + " -> cost = " + (moveno + board.manhattan()));
            System.out.println(board);
            System.out.println((++moveno) + ". Move: " + move);
            board.doMove(move);
        }
        System.out.println("Solved board:");
        System.out.println(board);
    }

}

