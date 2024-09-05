import java.util.Objects;

public class Pair<E> {
    // Wir erstellen zwei Elemente (E first und E second)
    private E first;
    private E second;

    public Pair(E first, E second) {
        /*Wir erstellen auch einen Konstruktor und übergeben ihm Elemente,
        damit das Objekt später beim Erstellen initialisiert werden kann*/
        this.first = first;
        this.second = second;
    }

    public Pair(Pair<E> other) {
        /*Hier erstellen wir einen Konstruktor,
        der ein bereits fertiges Objekt als Parameter akzeptiert.
        Das heißt, wenn wir ein neues Objekt erstellen, übergeben wir
        ein anderes bereits fertiges Objekt an die Parameter,
        und unser Objekt nimmt die gleiche Eigenschaften von diesem.*/
        this.first = other.first;
        this.second = other.second;
    }


    public void swap() {
        // Diese Methode vertauscht zwei Werte
        E temp = first;
        first = second;
        second = temp;
    }

    public E getFirst() {
        // Diese Methode gibt das erste Element zurück
        return first;
    }

    public void setFirst(E first) {
        // Diese Methode legt den Wert des ersten Elements fest
        this.first = first;
    }

    public E getSecond() {
        // Diese Methode gibt das zweite Element zurück
        return second;
    }

    public void setSecond(E second) {
        // Diese Methode legt den Wert des zweiten Elements fest
        this.second = second;
    }

    //SetFirst und setSecond
    /*Außerdem können diese Methoden nur implementiert werden, wenn unsere Elemente nicht
    "final" sind. Wenn sie "final" sind, kann der Wert nicht mehr geändert werden,
    sobald das Objekt erstellt und initialisiert wurde*/


    @Override
    public String toString() {
        // Diese Methode gibt uns einen String mit unseren Werten zurück
        return "Pair<" + first + ", " + second +
                '>';
    }

    // Diese beiden Methoden gehören vertraglich immer zusammen, sie werden zum Vergleichen von Objekten benötigt.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?> pair = (Pair<?>) o;
        return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    public static void main(String[] args) {
        Pair<Integer> pair1 = new Pair<>(1, 2);
        Pair<Integer> pair2 = new Pair<>(1, 2);
        System.out.println("Variable pair1 hat den Wert: " + pair1);
        System.out.println("Variable pair2 hat den Wert: " + pair2);
        System.out.println("Syntaktische Gleichheit von pair1 und pair2 ist: " + (pair1 == pair2));
        System.out.println("Semantische Gleichheit von pair1 und pair2 ist: " + pair1.equals(pair2));
        Pair<Integer> pair1b = pair1;
        Pair<Integer> pair2b = new Pair<>(pair2);
        pair1.swap();
        pair2.setFirst(10);
        System.out.println("Nach swap() hat Variable pair1 den Wert: " + pair1);
        System.out.println("Nach setFirst(10) hat Variable pair2 den Wert: " + pair2);
        System.out.println("Die zuvor erstellte Kopie pair1b hat den Wert: " + pair1b);
        System.out.println("Die zuvor erstellte Kopie pair2b hat den Wert: " + pair2b);
        /*
        Die erwartete Ausgabe ist:
Variable pair1 hat den Wert: Pair<1, 2>
Variable pair2 hat den Wert: Pair<1, 2>
Syntaktische Gleichheit von pair1 und pair2 ist: false
Semantische Gleichheit von pair1 und pair2 ist: true
Nach swap() hat Variable pair1 den Wert: Pair<2, 1>
Nach setFirst(10) hat Variable pair2 den Wert: Pair<10, 2>
Die zuvor erstellte Kopie pair1b hat den Wert: Pair<2, 1>
Die zuvor erstellte Kopie pair2b hat den Wert: Pair<1, 2>
         */
    }
}

