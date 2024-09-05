import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.IntStream;

/**
 * This class implements a game of Row of Bowls.
 * For the games rules see Blatt05. The goal is to find an optimal strategy.
 */
public class RowOfBowls {
    int[][] matrix;
    int[] values;

    public RowOfBowls() {
    }

    /**
     * Implements an optimal game using dynamic programming
     * @param values array of the number of marbles in each bowl
     * @return number of game points that the first player gets, provided both parties play optimally
     */
    public int maxGain(int[] values) {
        /*
        Wir erstellen eine Matrix, die unsere Berechnungen, also Zwischenergebnisse, speichert.
        Es wird nur der rechte Teil der Matrix von der Diagonale verwendet
         */
        this.values = values;
        matrix = new int[values.length][values.length];
        if (values.length == 0) {
            return 0;
        }
        /*
        Hier füllen wir die Diagonale mit unseren Werten der Anzahl der Kugeln in jeder Schüssel
         */
        IntStream.range(0, values.length).forEach(i -> matrix[i][i] = values[i]);
        /*
        Hier haben wir zwei Schleifen. Der erste Zyklus ist für die Auswahl der Paare verantwortlich,
        also je 2 Schalen, je 3 Schalen usw. bis zu 6. Wenn wir zum Beispiel noch 3 Schalen mit Kugeln übrig haben,
        wählen die Spieler aus den 3 Schalen die optimalen aus.

        Der zweite Zyklus ist dafür verantwortlich, je nach Situation genau die richtige Schüssel auszuwählen,
        entweder links oder rechts. Wenn wir zum Beispiel alle 6 Schüsseln haben, dann hat die Schüssel ganz
        links einen Index von 0 und die rechte Schüssel sollte einen Index von 5 haben, und auf die gleiche Weise,
        je nach Situation, wie viele Schüsseln uns zur Verfügung stehen
         */
        IntStream.range(2, values.length + 1).forEach(pars ->
                IntStream.range(0, values.length - pars + 1).forEach(left -> {
                    int right = left + pars - 1;
                    matrix[left][right] = Math.max(values[left] - matrix[left + 1][right],
                            values[right] - matrix[left][right - 1]);
                })
        );
        /*
        Hier geben wir die optimale Differenz zurück, wenn wir jedes Mal den optimalen Schritt wählen.
        Dieser Wert befindet sich oben rechts in unserer Matrix
         */
        return matrix[0][values.length - 1];
    }


    /**
     * Implements an optimal game recursively.
     *
     * @param values array of the number of marbles in each bowl
     * @return number of game points that the first player gets, provided both parties play optimally
     */

    /*
    Wir wählen aus den Schritten die optimale Lösung
    aus und tun dies auch, bis wir nur noch eine Schüssel übrig haben, das heißt,
    wenn die linke und die rechte Schüssel gleich sind
     */
    public int maxGainRecursive(int[] values) {
        BiFunction<Integer, Integer, Integer> maxGainRecursive = new BiFunction<>() {
            @Override
            public Integer apply(Integer left, Integer right) {
                if (values.length == 0) {
                    return 0;
                }
                if (left.equals(right)) {
                    return values[left];
                }
                return Math.max(values[left] - this.apply(left + 1, right),
                        values[right] - this.apply(left, right - 1));
            }
        };
        return maxGainRecursive.apply(0, values.length - 1);
    }


    /**
     * Calculates an optimal sequence of bowls using the partial solutions found in maxGain(int values)
     * @return optimal sequence of chosen bowls (represented by the index in the values array)
     */
    public Iterable<Integer> optimalSequence() {
        ArrayList<Integer> sequence = new ArrayList<>();
        final int[] chooseLeft = new int[1];
        BiFunction<Integer, Integer, Void> lookForSequence = new BiFunction<>() {
            @Override
            public Void apply(Integer left, Integer right) {
                if (left > right) {
                    return null;
                }

                if (left.equals(right)) {
                    sequence.add(left);
                    return null;
                }

                if (left + 1 <= right) {
                    chooseLeft[0] = values[left] - matrix[left + 1][right];
                } else {
                    chooseLeft[0] = values[left];
                }

                if (matrix[left][right] == chooseLeft[0]) {
                    sequence.add(left);
                    this.apply(left + 1, right);
                } else {
                    sequence.add(right);
                    this.apply(left, right - 1);
                }
                return null;
            }
        };
        lookForSequence.apply(0, values.length - 1);
        return sequence;
    }
}


