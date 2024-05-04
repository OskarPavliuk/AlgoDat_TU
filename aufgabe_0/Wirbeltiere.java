package aufgabe_0;

public abstract class Wirbeltiere {
    boolean lebtAnLand;

    public Wirbeltiere(boolean lebtAnLand) {
        this.lebtAnLand = lebtAnLand;
    }

    public abstract void essen();
}
