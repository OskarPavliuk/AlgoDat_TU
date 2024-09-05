import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * This class implements and evaluates game situations of a TicTacToe game.
 */
public class TicTacToe {

    /**
     * Returns an evaluation for player at the current board state.
     * Arbeitet nach dem Prinzip der Alphabeta-Suche. Works with the principle of Alpha-Beta-Pruning.
     *
     * @param board     current Board object for game situation
     * @param player    player who has a turn
     * @return rating of game situation from player's point of view
     **/
    /*
    Hier multiplizieren wir das Ergebnis mit dem Zeichen des Spielers, um die Bewertung anzupassen
     */
    public static int alphaBeta(Board board, int player) {
        return player * alphaBeta(board, player, Integer.MIN_VALUE, Integer.MAX_VALUE, board.nFreeFields());
    }

    public static int alphaBeta(Board board, int player, int alpha, int beta, int depth) {
        /*
        // Wir überprüfen, ob das Spiel gewonnen ist oder ob die maximale
        Tiefe erreicht wurde, und wenden die Methode (bewertung) an
         */
        if (board.isGameWon() == true || depth == 0) {
            return bewertung(board, player);
        }
        // Unentschieden
        if (board.nFreeFields() == 0) {
            return 0;
        }
        /*
        // Wir entscheiden hier, ob die Funktion spielerMax oder spielerMin
        aufgerufen wird, basierend auf dem aktuellen Spieler.
         */
        if (player == 1) {
            return spielerMax(board, player, alpha, beta, depth);
        } else {
            return spielerMin(board, player, alpha, beta, depth);
        }
    }

    /*
    Als Grundlage für die Spielermax- und Spielermin-Methoden habe ich mir den Algorithmus aus dem
    Trainingsvideo (Isis Blatt4) ausgeliehen und ihn für meine Implementierung optimiert
     */
    private static int spielerMax(Board board, int player, int alpha, int beta, int depth) {
        if ((board.isGameWon() == true) || (depth == 0)) {
            return bewertung(board, player);
        }

        int max_value = Integer.MIN_VALUE;
        ArrayList<Position> moves = (ArrayList<Position>) board.validMoves();

        for (Position move : moves) {
            board.doMove(move, player);
            int value = alphaBeta(board, -player, alpha, beta, depth - 1);
            board.undoMove(move);

            if (value > max_value) {
                max_value = value;
            }
            if (value > alpha) {
                alpha = value;
            }
            if (alpha >= beta) {
                break;
            }
        }
        return max_value;
    }

    private static int spielerMin(Board board, int player, int alpha, int beta, int depth) {
        if ((board.isGameWon() == true) || (depth == 0)) {
            return bewertung(board, player);
        }

        int min_value = Integer.MAX_VALUE;
        ArrayList<Position> moves = (ArrayList<Position>) board.validMoves();

        for (Position move : moves) {
            board.doMove(move, player);
            int value = alphaBeta(board, -player, alpha, beta, depth - 1);
            board.undoMove(move);

            if (value < min_value) {
                min_value = value;
            }
            if (value < beta) {
                beta = value;
            }
            if (alpha >= beta) {
                break;
            }
        }
        return min_value;
    }

    // Hier evaluieren wir das Ende des Spiels in puncto des Spielers
    private static int bewertung(Board board, int player) {
        if (board.isGameWon() == true) {
            if (player == 1) {
                return -board.nFreeFields() - 1;
            } else {
                return board.nFreeFields() + 1;
            }
        } else {
            return 0;
        }
    }


    /**
     * Vividly prints a rating for each currently possible move out at System.out.
     * (from player's point of view)
     * Uses Alpha-Beta-Pruning to rate the possible moves.
     * formatting: See "Beispiel 1: Bewertung aller Zugmöglichkeiten" (Aufgabenblatt 4).
     *
     * @param board     current Board object for game situation
     * @param player    player who has a turn
     **/
    public static void evaluatePossibleMoves(Board board, int player) {
        /*
        Wir deklarieren eine Variable „playerSymbol“,
        die zur Anzeige des Symbols des aktuellen Spielers („x“ oder „o“) verwendet wird
         */
        char playerSymbol;
        /*
        Je nach Spieler zeigen wir die entsprechende Meldung an
        */
        if (player == 1) {
            playerSymbol = 'x';
            System.out.println("Evaluation for player '" + playerSymbol + "':");
        } else if (player == -1) {
            playerSymbol = 'o';
            System.out.println("Evaluation for player '" + playerSymbol + "':");
        }

        for (int y = 0; y < board.getN(); y++) {
            for (int x = 0; x < board.getN(); x++) {
                Position pos = new Position(x, y);
                int value = board.getField(pos);

                if (value == 0) {
                    board.doMove(pos, player);
                    int evaluation = (-1) * alphaBeta(board, -player);
                    board.undoMove(pos);
                    System.out.print(evaluation);
                    /*
                    Wir fügen nach jeder Zelle ein Leerzeichen ein, außer der letzten Zelle in einer Zeile
                     */
                    if (x < board.getN() - 1) {
                        System.out.print(" ");
                    }
                } else {
                    char symbol;
                    if (value == 1) {
                        symbol = 'x';
                        System.out.print(symbol);
                    } else if (value == -1) {
                        symbol = 'o';
                        System.out.print(symbol);
                    }
                    /*
                    Wir fügen nach jeder Zelle ein Leerzeichen ein, außer der letzten Zelle in einer Zeile
                     */
                    if (x < board.getN() - 1) {
                        System.out.print(" ");
                    }
                }
            }
            /*
            Wir wechseln zu einer neuen Zeile, nachdem wir jede Zeile außer der letzten abgeschlossen haben
             */
            if (y < board.getN() - 1) {
                System.out.print("\n");
            }
        }
        System.out.print("\n");
    }
}
