public class RegularPolygon extends ConvexPolygon {
    /* Wir fügen den Radius hinzu, damit wir ihn später an den Konstruktor übergeben
    und auch das Objekt mit ihm initialisieren können */
    private double radius;

    /* Der Konstruktor, in dem wir den Radius und auch die Variable N übergeben,
    die wir dann als Länge des Arrays übergeben, also im Allgemeinen als Anzahl
    der Eckpunkte unseres Polygons. Dann initialisieren wir den Radius und abhängig
    vom Radius initialisieren wir auch unsere Punkte. die Anzahl der Eckpunkte habe
    ich über vertices.length angegeben */
    public RegularPolygon(int N, double radius) {
        super(new Vector2D[N]);
        this.radius = radius;
        koordinatenBerechnung(radius);
    }

    /* Konstruktor, der für die Erstellung einer Kopie verantwortlich ist, wenn wir
    ihm ein bereits fertiges Objekt der Klasse RegularPolygon übergeben. Dazu rufen
    wir einfach den Basiskonstruktor ConvexPolygon auf und übergeben unser Objekt an ihn.
    Den Radius übergeben wir auch per Kopie an das neue Objekt */
    public RegularPolygon(RegularPolygon polygon) {
        super(new Vector2D[polygon.vertices.length]);
        this.radius = polygon.radius;
        /* Hier sind die letzten Zeilen wichtig. Sie werden benötigt, damit wir unsere Punkte
        kopieren können. Die Hauptidee besteht darin, für jedes Element im Array einen neuen Punkt
        mit den Koordinaten des Objekts zu erstellen. Wenn wir Änderungen am ersten Objekt vornehmen
        (z. B. den Radius ändern, werden wir entsprechend neue Koordinaten haben), dann im zweiten Objekt
        bleiben die Punkte unverändert. Wenn wir this.vertices = polygon.vertices gemacht haben,
        dann werden die Punkte von Objekten voneinander abhängen, und wenn es Änderungen im ersten Objekt
        gibt, werden diese im zweiten Objekt widergespiegelt */
        for (int i = 0; i < polygon.vertices.length; i++) {
            this.vertices[i] = new Vector2D(polygon.vertices[i].getX(), polygon.vertices[i].getY());
        }
    }

    /* Dies ist die Methode, die für die Änderung des Radius in einen neuen verantwortlich ist.
    Da wir den Radius ändern, müssen wir außerdem unsere neuen Polygonpunkte berechnen */
    public void resize(double newradius) {
        this.radius = newradius;
        koordinatenBerechnung(newradius);
    }

    // Methode, die einen String mit Werten zurückgibt
    @Override
    public String toString() {
        return "RegularPolygon{" + "N=" + vertices.length + "," + " radius=" + radius + "}";
    }

    /* Methode, die unsere Punktkoordinaten berechnet. Ich habe alles so gemacht,
    wie es in der Aufgabe gesagt wurde, ich habe zuerst den Wert des Winkels zwischen
    den Punkten eingestellt und dann für jeden Punkt durch Sinus und Cosinus die Koordinate
    berechnet und auch auf den angegebenen Radius skaliert */
    private void koordinatenBerechnung(double radius) {
        double winkel = 2 * Math.PI / vertices.length;  // 360°/#Ecken;
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = new Vector2D((radius * Math.cos(winkel * i)), (radius * Math.sin(winkel * i)));
        }
    }
}
