import java.io.FileInputStream;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class RicochetRobots {

    /**
     * Find the shortest move sequence for the given board situation to the goal state,
     * i.e., the designated robot has reached the target field.
     * The task is accomplished by using breadth-first-search. In order to avoid checking
     * the same situations over and over again, each investigated board is put in a hash set.
     *
     * @param board Initial configuration of the game.
     * @return The partial solution containing the the shortest move sequence to the target
     */
    public static PartialSolution bfsWithHashing(Board board) {
        /* TODO */
        HashSet<Board> visited = new HashSet<>();
        Queue<PartialSolution> queue = new LinkedList<>();
        PartialSolution partialSolution = new PartialSolution(new Board(board));

        queue.add(partialSolution);
        visited.add(new Board(board));

        while (!queue.isEmpty()) {
            PartialSolution currentSolution = queue.poll();
            Board currentBoard = currentSolution.getBoard();

            if (currentBoard.targetReached()) {
                return currentSolution;
            }

            currentBoard.validMoves().stream()
                    .map(move -> {
                        Board newBoard = new Board(currentBoard);
                        newBoard.doMove(move);
                        PartialSolution newSolution = new PartialSolution(currentSolution);
                        newSolution.addMove(move);
                        return newSolution;
                    })
                    .filter(newSolution -> visited.add(newSolution.getBoard()))
                    .forEach(queue::add);
        }
        return null;
    }

    public static void printBoardSequence(Board board, Iterable<Move> moveSequence) {
        int moveno = 0;
        for (Move move : moveSequence) {
            board.print();
            System.out.println((++moveno) + ". Move: " + move);
            board.doMove(move);
        }
        board.print();
    }

}

