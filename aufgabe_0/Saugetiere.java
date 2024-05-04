package aufgabe_0;

public abstract class Saugetiere extends Wirbeltiere {
    private String fellfarbe;

    public Saugetiere(boolean lebtAnLand, String fellfarbe) {
        super(lebtAnLand);
        this.fellfarbe = fellfarbe;
    }
}
