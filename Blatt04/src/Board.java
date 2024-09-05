import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Stack;

import static java.lang.Math.abs;

/**
 * This class represents a generic TicTacToe game board.
 */
public class Board {
    private int n;
    public int[][] array;
    private int count;

    /**
     *  Creates Board object, am game board of size n * n with 1<=n<=10.
     */
    public Board(int n) {
        if (n < 1 || n > 10) {
            throw new InputMismatchException("Wrong input");
        } else {
            this.n = n;
            this.array = new int[n][n];
            this.count = n * n;
        }
    }

    /**
     *  @return length/width of the Board object
     */
    public int getN() {
        return n;
    }

    /**
     *  @return number of currently free fields
     */
    public int nFreeFields() {
        return count;
    }

    /**
     *  @return token at position pos
     */
    public int getField(Position pos) throws InputMismatchException {
        if (pos.x < 0 || pos.y < 0 || pos.y > n - 1 || pos.x > n - 1) {
            throw new InputMismatchException("Invalid position");
        }
        return array[pos.x][pos.y];
    }

    /**
     *  Sets the specified token at Position pos.
     */
    public void setField(Position pos, int token) throws InputMismatchException {
        if (token != 1 && token != -1 && token != 0) {
            throw new InputMismatchException("Invalid token");
        }
        if (pos.x < 0 || pos.y < 0 || pos.y > n - 1 || pos.x > n - 1) {
            throw new InputMismatchException("Invalid position");
        }

        if (array[pos.x][pos.y] == 0 && token != 0) {
            count--;
        }
        else if (array[pos.x][pos.y] != 0 && token == 0) {
            count++;
        } else if (array[pos.x][pos.y] != 0 && token != 0) {
            count -= 0;
        }
        array[pos.x][pos.y] = token;
    }

    /**
     *  Places the token of a player at Position pos.
     */
    public void doMove(Position pos, int player) {
        if (pos.x < 0 || pos.y < 0 || pos.y > n - 1 || pos.x > n - 1) {
            throw new InputMismatchException("Invalid position");
        }
        if (player != 1 && player != -1) {
            throw new InputMismatchException("Invalid player");
        }
        if (array[pos.x][pos.y] == 0) {
            array[pos.x][pos.y] = player;
            count--;
        } else throw new InputMismatchException("Der Wert ist schon besetzt");
    }

    /**
     *  Clears board at Position pos.
     */
    public void undoMove(Position pos) {
        if (pos.x < 0 || pos.y < 0 || pos.y > n - 1 || pos.x > n - 1) {
            throw new InputMismatchException("Invalid position");
        }
        if (array[pos.x][pos.y] != 0) {
            array[pos.x][pos.y] = 0;
            count++;
        } else throw new InputMismatchException("Der Wert ist 0, kann nicht geaendert werden");
    }

    /**
     *  @return true if game is won, false if not
     */
    public boolean isGameWon() {
        for (int i = 0; i < array.length; i++) {
            if (pruefen(array[i])) {
                return true;
            }
        }

        for (int i = 0; i < array.length; i++) {
            int[] columnarray = new int[array.length];
            for (int j = 0; j < array.length; j++) {
                columnarray[j] = array[j][i];
            }
            if (pruefen(columnarray)) {
                return true;
            }
        }


        int[] firstdiagonal = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            firstdiagonal[i] = array[i][i];
        }
        if (pruefen(firstdiagonal)) {
            return true;
        }


        int[] seconddiagonal = new int[array.length];
        for (int i = 0; i < array.length; i++) {

            seconddiagonal[i] = array[i][array.length - i - 1];
            if (pruefen(seconddiagonal)) {
                return true;
            }
        }

        return false;
    }

    private boolean pruefen(int[] check) {
        for (int i = 0; i < check.length; i++) {
            if (check[i] == 0) {
                return false;
            }
        }
        for (int i = 0; i < check.length; i++) {
            for (int j = 0; j < check.length; j++) {
                if (check[i] != check[j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     *  @return set of all free fields as some Iterable object
     */
    public Iterable<Position> validMoves() {
        ArrayList<Position> allFreePositions = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == 0) {
                    Position freePosition = new Position(i, j);
                    allFreePositions.add(freePosition);
                }
            }
        }
        return allFreePositions;
    }

    /**
     *  Outputs current state representation of the Board object.
     *  Practical for debugging.
     */
    public void print() {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

}

