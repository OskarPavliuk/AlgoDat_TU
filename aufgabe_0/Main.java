package aufgabe_0;

public class Main {
    public static void main(String[] args) {
        Kegelrobbe Paula = new Kegelrobbe("Oskar", 5, 10);
        System.out.println(Paula);
        Kegelrobbe[] Gruppe = new Kegelrobbe[3];
        Gruppe[0] = Paula;
        Gruppe[1] = new Kegelrobbe("Klaus", 5, 2.3);
        Gruppe[2] = new Kegelrobbe("Leo", 3, 2.0);
        for (Kegelrobbe kegelrobbe : Gruppe) {
            kegelrobbe.essen();
        }

       Wirbeltiere[] Tiere = new Wirbeltiere[3];
        Tiere[0] = new Rotnackenwallaby();
        Tiere[1] = new Kegelrobbe("Paula", 3, 1.7);
        Tiere[2] = new Kreuzotter();
        for (Wirbeltiere wirbeltiere : Tiere) {
            System.out.println(wirbeltiere.lebtAnLand);
            wirbeltiere.essen();
        }
    }
}
