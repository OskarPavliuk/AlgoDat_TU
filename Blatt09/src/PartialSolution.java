import java.util.ArrayList;
import java.util.List;

/**
 * PartialSolution is a class which represents a state of the game
 * from its initial state to its solution. It includes the current
 * state of the board and the move sequence from the initial state
 * to the current state.</br>
 * For the use in the A*-algorithm, the class implements Comparable
 * wrt the cost of the number of moves + heuristic.</br>
 * For the heuristic and game functionality, the respective methods
 * of class {@link Board} are used.
 */
public class PartialSolution implements Comparable<PartialSolution> {
    private Board board;
    private List<Move> moves;
    private int cost;

    /**
     * Constructor, generates an empty solution based on the provided
     * <em>board</em> with an empty move sequence.
     *
     * @param board initial state of the board
     */
    public PartialSolution(Board board) {
        // TODO 1.2 PartialSolution(board)
        this.board = new Board(board);
        this.moves = new ArrayList<>();
        this.cost = board.manhattan();
    }

    /**
     * Copy constructor, generates a deep copy of the input
     *
     * @param that The partial solution that is to be copied
     */
    public PartialSolution(PartialSolution that) {
        // TODO 1.2 PartialSolution(PartialSolution)
        this.board = new Board(that.board);
        this.moves = new ArrayList<>(that.moves);
        this.cost = that.cost;
    }

    /**
     * Performs a move on the board of the partial solution and updates
     * the cost.
     *
     * @param move The move that is to be performed
     */
    public void doMove(Move move) {
        // TODO 1.2 doMove
        board.doMove(move);
        moves.add(move);
        // f(n) = g(n) + h(n);
        cost = moves.toArray().length + board.manhattan();
    }

    /**
     * Tests whether the solution has been reached, i.e. whether
     * current board is in the goal state.
     *
     * @return {@code true}, if the board is in goal state
     */
    public boolean isSolution() {
        // TODO 1.2 isSolution
        return board.isSolved();
    }

    /**
     * Return the sequence of moves which leads from the initial board
     * to the current state.
     *
     * @return move sequence leading to this state of solution
     */
    public Iterable<Move> moveSequence() {
        // TODO 1.2 moveSequence
        return moves;
    }

    /**
     * Generates all possible moves on the current board, <em>except</em>
     * the move which would undo the previous move (if there is one).
     *
     * @return moves to be considered in the current situation
     */
    public Iterable<Move> validMoves() {
        // TODO 1.2 validMoves
        if (moves.isEmpty()) {
            return board.validMoves();
        } else {
            return board.validMoves(moves.getLast());
        }
    }

    /**
     * Compares partial solutions based on their cost.
     * (For performance reasons, the costs should be pre-calculated
     * and stored for each partial solution, rather than computed
     * here each time anew.)
     *
     * @param that the other partial solution
     * @return result of cost comparistion between this and that
     */

    public int compareTo(PartialSolution that) {
        // TODO 1.2 compareTo
        return cost - that.cost;
    }

    public Board getBoard() {
        return board;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        StringBuilder msg = new StringBuilder("Partial solution with moves: \n");
        for (Move move : moveSequence()) {
            msg.append(move).append(", ");
        }
        return msg.substring(0, msg.length() - 2);
    }

}

