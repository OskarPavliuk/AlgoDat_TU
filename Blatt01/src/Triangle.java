import java.util.Arrays;

public class Triangle extends ConvexPolygon {

    /* Dies ist ein Konstruktor, in dem wir drei Variablen vom Typ Vector2D als Parameter übergeben
       und zur Initialisierung den Basiskonstruktor, also die Klasse ConvexPolygon, aufrufen
       und dort unsere Werte in Form eines Arrays übergeben */
    public Triangle(Vector2D a, Vector2D b, Vector2D c) {
        super(new Vector2D[]{a, b, c});
    }

    /* Dies ist auch ein Konstruktor, aber er funktioniert nur, wenn wir ein Triangle-Objekt erstellen
    und ihm ein weiteres Triangle-Objekt übergeben. Dementsprechend haben wir die gleichen Werte wie in dem Objekt,
    von dem wir diese Werte übernommen haben */
    public Triangle(Triangle triangle) {
        super(triangle.vertices);
    }

    /* Diese Methode berechnet die Fläche unseres Dreiecks.
    Ich habe die in der Aufgabe beschriebene Methode (Herons Formel) verwendet */
    @Override
    public double area() {
        Vector2D a = vertices[0];
        Vector2D b = vertices[1];
        Vector2D c = vertices[2];

        double side_1 = new Vector2D(b.getX() - a.getX(), b.getY() - a.getY()).length();
        double side_2 = new Vector2D(c.getX() - b.getX(), c.getY() - b.getY()).length();
        double side_3 = new Vector2D(a.getX() - c.getX(), a.getY() - c.getY()).length();

        double s = (side_1 + side_2 + side_3) / 2;
        return Math.sqrt(s * (s - side_1) * (s - side_2) * (s - side_3));
    }

    // Dies ist eine Methode, die uns einen String mit unseren Werten für das Dreieck zurückgibt
    @Override
    public String toString() {
        return "Triangle{" + Arrays.toString(vertices) + '}';
    }
}