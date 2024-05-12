package aufgabe_3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StringGenomeTest {

    private StringGenome genome;

    @BeforeEach
    public void setUp() {
        // Initialisiert das StringGenome Objekt vor jedem Test
        genome = new StringGenome();
    }

    @Test
    public void testAddNucleotide() {
        genome.addNucleotide('A');
        assertEquals("A", genome.toString());
    }

    @Test
    public void testNucleotideAt() {
        assertEquals('A', genome.nucleotideAt(1));
    }

    @Test
    public void testLength() {
        assertEquals(1, genome.length());
    }

    @Test
    public void testToString() {
        assertEquals("A", genome.toString());
    }

    @Test
    public void testEqualsObject() {
       StringGenome genome1 = new StringGenome();
       StringGenome genome2 = new StringGenome();
       genome1.addNucleotide('A');
       genome2.addNucleotide('B');
       assertFalse(genome1.equals(genome2), "Objects are not the same");
    }
}
