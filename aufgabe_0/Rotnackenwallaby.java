package aufgabe_0;

public class Rotnackenwallaby extends Saugetiere {
    public Rotnackenwallaby() {
        super(true, "rot");
    }

    @Override
    public void essen() {
        System.out.println("Ich esse Pflanzen");
    }
}
