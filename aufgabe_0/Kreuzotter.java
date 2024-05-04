package aufgabe_0;

public class Kreuzotter extends Reptilien {
    public Kreuzotter() {
        super(true, true);
    }

    @Override
    public void essen() {
        System.out.println("Ich esse Froesche");
    }
}
