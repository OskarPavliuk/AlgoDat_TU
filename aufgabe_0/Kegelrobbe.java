package aufgabe_0;

public class Kegelrobbe extends Saugetiere {
    private final String name;
    private final int alter;
    private final double groesse;

    public Kegelrobbe(String name, int alter, double groesse) {
        super(false, "weiss");
        this.name = name;
        this.alter = alter;
        this.groesse = groesse;
    }

    public String getName() {
        return name;
    }

    public int getAlter() {
        return alter;
    }

    public double getGroesse() {
        return groesse;
    }

    public void essen(){
        System.out.println("Ich esse Fisch!");
    }

    @Override
    public String toString() {
        return "Kegelrobbe{" +
                "name='" + name + '\'' +
                ", alter=" + alter +
                ", groesse=" + groesse +
                '}';
    }
}
