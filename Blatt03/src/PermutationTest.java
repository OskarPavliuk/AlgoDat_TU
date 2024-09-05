import java.util.LinkedList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;

class PermutationTest {
    PermutationVariation p1;
    PermutationVariation p2;
    public int n1;
    public int n2;
    int cases = 0;

    void initialize() {
        n1 = 4;
        n2 = 6;
        Cases c = new Cases();
        p1 = c.switchforTesting(cases, n1);
        p2 = c.switchforTesting(cases, n2);
    }


    /*
    Um nicht alles in einer Methode zu schreiben, habe ich beschlossen,
    die Hauptlogik in eine andere Methode zu verschieben
     */
    @Test
    void testPermutation() {
        initialize();
        testExactPermutation(p1, n1);
        testExactPermutation(p2, n2);
    }

    /*
    Bei dieser Methode überprüfen wir beim Erstellen des Arrays, dass wir keine
    doppelten Elemente haben, dass es die richtige Größe hat und dass allDerangements eine leere Liste ist
     */
    private void testExactPermutation(PermutationVariation variation, int givenLength) {
        assertNotNull(variation, "variation(p) can not be null"); // checking for Nullpointerexception
        assertNotNull(variation.original, "original array can not be null"); // checking for Nullpointerexception
        assertNotNull(variation.allDerangements, "allDerangements can not be null"); // checking for Nullpointerexception

    /*
    We use the assertAll method so that the tests run to the end and do not stop if there is an error somewhere.
    And also lambda expressions for convenience
     */
        assertAll("Testing for original array_length, list_size, and no double elements in original",
                () -> assertEquals(givenLength, variation.original.length),// expected vs actual
                () -> assertEquals(0, variation.allDerangements.size(), "List size must be 0"),
                () -> {
                    for (int i = 0; i < variation.original.length; i++) {
                        int currentNumber = variation.original[i];
                        for (int j = i + 1; j < variation.original.length; j++) {
                            assertNotEquals(currentNumber, variation.original[j], "We can not have two the same elements in our array");
                        }
                    }
                });
    }


    @Test
    void testDerangements() {
        initialize();
        fixConstructor();
        p1.derangements();
        p2.derangements();
        /*
        Hier füge ich einfach alles in AssertAll zusammen und übergebe zwei Parameterlose Lambdas (Funktionen),
        die nur die Methoden zurückgeben, die ich brauche
         */
        assertAll("testDerangements",
                () -> {
                    assertNotNull(p1, "p1 can not be null");
                    assertNotNull(p1.allDerangements, "p1.allDerangements can not be null");
                    testAnzahl(p1);
                    for (int[] derangement : p1.allDerangements) {
                        testExactDerangements(p1.original, derangement);
                    }
                },
                () -> {
                    assertNotNull(p2, "p2 can not be null");
                    assertNotNull(p2.allDerangements, "p2.allDerangements can not be null");
                    testAnzahl(p2);
                    for (int[] derangement : p2.allDerangements) {
                        testExactDerangements(p2.original, derangement);
                    }
                }
        );
    }

    /*
    Diese Methode prüft, ob wir die richtige Anzahl an Permutationen haben
     */
    private void testAnzahl(PermutationVariation variation) {
        assertAll("testAnzahl",
                () -> assertNotNull(variation, "not null"), //checking for Nullpointerexception
                () -> assertEquals(calculateAnzahl(variation.original.length), variation.allDerangements.size(), "Wrong numbers of permutations")
        );
    }


    // Diese Methode berechnet die Anzahl der Permutationen
    private int calculateAnzahl(int n) {
        assertNotNull(n, "length of the array can not be null"); // checking for Nullpointerexception
        /* Ich habe die Formel von dieser Seite verwendet
        https://www.geeksforgeeks.org/count-derangements-permutation-such-that-no-element-appears-in-its-original-position/
        */
        if (n == 1) return 0;
        if (n == 2) return 1;
        return (n - 1) * (calculateAnzahl(n - 1) +
                calculateAnzahl(n - 2));
    }

    // Hier überprüfe ich, ob sich die Elemente in jeder Permutation relativ zu unserem Array überhaupt bewegen
    private void testExactDerangements(int[] original, int[] derangement) {
        assertAll("TestExactDerangements",
                () -> assertNotNull(original, "original array can not be null"), // checking for Nullpointerexception
                () -> assertNotNull(derangement, "derangement array can not be null"), // checking for Nullpointerexception
                () -> assertEquals(original.length, derangement.length, "each derangement array must be the same size as the original array"),
                () -> {
                    boolean isDerangement = true;
                    for (int i = 0; i < derangement.length; i++) {
                        if (derangement[i] == original[i]) {
                            isDerangement = false;
                            break;
                        }
                    }
                    assertEquals(true, isDerangement, "Elements can not take the same place as in original array");
                }
        );
    }


    @Test
    void testsameElements() {
        initialize();
        fixConstructor();
        p1.derangements();
        p2.derangements();
        /*
        Allgemeiner Test für unsere p1, p2
         */
        assertAll("testsameElements",
                () -> checkDerangements(p1),
                () -> checkDerangements(p2)
        );
    }

    /*
    Hier überprüfe ich, ob überhaupt Permutationen erstellt werden.
    Wenn nicht, ist der Test fehlgeschlagen
     */
    private void checkDerangements(PermutationVariation permutation) {
        assertAll("checkDerangements",
                () -> assertNotNull(permutation.allDerangements, "allDerangements should not be null"),
                () -> {
                    if (permutation.allDerangements.isEmpty()) {
                        fail("We have no derangements");
                    }
                },
                () -> permutation.allDerangements.forEach(derangement -> testRealizationsameElements(permutation.original, derangement))
        );
    }

    // Funktion zum Sortieren (Bubble sort)
    private void sortingElements(int[] array) {
        assertNotNull(array, "array can not be null"); // checking for Nullpointerexception
        boolean swapped;
        for (int i = 0; i < array.length; i++) {
            swapped = false;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped)
                break;
        }
    }

    /* Hier überprüfe ich, ob die Elemente im Array und in jeder Permutation gleich sind,
    also ob die Arrays im Allgemeinen gleich sind. Die Hauptidee besteht darin,
    dass wir Arrays zuerst sortieren und danach vergleichen
     */
    private void testRealizationsameElements(int[] original, int[] derangement) {
        assertAll("testRealiyationElements",
                () -> assertNotNull(original, "original array is not null"), // checking for Nullpointerexception
                () -> assertNotNull(derangement, "derangement is not null"), // checking for Nullpointerexception
                () -> assertEquals(original.length, derangement.length), // the size of arrays must be the same
                () -> {
                    int[] originaltocompare = Arrays.copyOf(original, original.length);
                    int[] derangementtocompare = Arrays.copyOf(derangement, derangement.length);
                    sortingElements(originaltocompare);
                    sortingElements(derangementtocompare);
                    assertArrayEquals(originaltocompare, derangementtocompare, "The elements need to be the same as in original array");
                }
        );
    }


    void setCases(int c) {
        this.cases = c;
    }

    public void fixConstructor() {
        //in case there is something wrong with the constructor
        p1.allDerangements = new LinkedList<int[]>();
        for (int i = 0; i < n1; i++)
            p1.original[i] = 2 * i + 1;

        p2.allDerangements = new LinkedList<int[]>();
        for (int i = 0; i < n2; i++)
            p2.original[i] = i + 1;
    }
}




