package aufgabe_0;

public abstract class Reptilien extends Wirbeltiere {
    private boolean giftig;

    public Reptilien(boolean lebtAnLand, boolean giftig) {
        super(lebtAnLand);
        this.giftig = giftig;
    }

}
