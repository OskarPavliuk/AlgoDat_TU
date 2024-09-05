import java.util.Stack;

/**
 * A class for constructing a Decimal-to-Binary Number- Converter; * contains a main method for demonstration.
 */
public class Dec2Bin {

    public Stack<Integer> binStack;  // We make it public to modify it in our tests.
    private int N;

    /**
     * Constructor of an empty object. Use method {@code convert()} to convert a number.
     */
    public Dec2Bin() {
        binStack = new Stack<>();
    }

    /**
     * Returns the number that is converted as {@code int}.
     *
     * @return the converted number
     */
    public int getN() {
        return N;
    }

    /**
     * Converts the given number into binary format, with each digit being represented in a
     * stack of {@code int}.
     *
     * @param N the number that is to be converted.
     */
    /* Hier haben wir eine Schleife und sie wird funktionieren, bis unsere Zahl größer als 0 ist.
    Wir werden Reste aus der Division durch 2 und ganze Teile haben. Der Rest wird sofort auf den
    Stapel gelegt, der gesamte Teil wird aktualisiert und der Zyklus wird wiederholt, bis die Zahl 0 wird */
    public void convert(int N) {
        this.N = N;
        binStack.clear();
        int rest;
        while (N > 0) {
            rest = N % 2;
            N = N / 2;
            binStack.push(rest);
        }
    }

    /**
     * Returns the digits that are stored in {@code binStack} as a string. To is the binary format of the
     * converted number.
     * For testing purpose, we require that the function works also, if the variable {@code binStack} is
     * modified externally.
     *
     * @return a string representation of the number in binary format.
     */
    /* Hier müssen wir die Elemente vom Stapel irgendwie als String ausgeben. Also habe ich mich für StringBuffer
    entschieden. Der Hauptunterschied zwischen StringBuffer und String ist (mutable/immutable). Die Idee
    besteht darin, Elemente aus dem Stapel zu entfernen und sie über die append-Methode an unser Objekt der
    StringBuffer-Klasse zu übergeben. Wenn wir hier außerdem keinen zusätzlichen Stapel anwenden, der Elemente
    von unserem Hauptstapel übernimmt, wird unser Stapel nach dem Aufruf von toString() einfach gelöscht */
    @Override
    public String toString() {
        if (binStack.isEmpty()) {
            return "0";
        }
        Stack<Integer> hilfsStapel = new Stack<>();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < binStack.size(); i++) {
            hilfsStapel.push(binStack.elementAt(i));
        }
        while (!hilfsStapel.isEmpty()) {
            stringBuffer.append(hilfsStapel.pop());
        }
        return stringBuffer.toString();
    }
}

