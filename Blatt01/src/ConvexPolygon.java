import java.util.Arrays;

public class ConvexPolygon extends Polygon {

    public ConvexPolygon(Vector2D[] vertices) {
        // Dies ist unser Konstruktor, der ein Array vom Typ Vector2D als Parameter akzeptiert und es initialisiert
        this.vertices = vertices;
    }

    // Das ist eine Methode zur Ausgabe eines Strings
    @Override
    public String toString() {
        return "ConvexPolygon(" + Arrays.toString(vertices) + ')';
    }

    /* Das ist eine Methode, die ein Array vom Typ Polygon zurückgibt.
    In unserer Aufgabe mussten wir ein Array der Größe 4 erstellen
    und jede Stelle des Arrays ist ein Objekt irgendeines Typs
    und am Ende geben wir das Array entsprechend zurück */
    public static Polygon[] somePolygons() {
        Polygon[] array = new Polygon[4];
        array[0] = new Triangle(new Vector2D(0, 0), new Vector2D(10, 0), new Vector2D(5, 5));
        array[1] = new Tetragon(new Vector2D(0, 0), new Vector2D(10, -5), new Vector2D(12, 2), new Vector2D(3, 17));
        array[2] = new RegularPolygon(5, 1);
        array[3] = new RegularPolygon(6, 1);
        return array;
    }

    /* Diese Methode gibt uns die Gesamtsumme aller Elemente unseres Arrays zurück.
    Dazu durchlaufen wir einfach eine foreach-Schleife für jedes Element und summieren deren Flächen */
    public static double totalArea(Polygon[] polygons) {
        double totalArea = 0.0;
        for (Polygon p : polygons) {
            totalArea = totalArea + p.area();
        }
        return totalArea;
    }

    /* Diese Methode berechnet den Umfang: Die Idee besteht darin,
    von jedem Punkt zum nächsten zu gehen und so weiter, bis wir
    den Ausgangspunkt erreichen, an dem wir angefangen haben, und
    so die Längen der Seiten zwischen diesen Punkten zu berechnen
    und sie dann alle zu addieren */
    @Override
    public double perimeter() {
        double perimeter = 0;
        for (int i = 0; i < vertices.length; i++) {
            Vector2D current_position = vertices[i];
            Vector2D next_position = vertices[(i + 1) % vertices.length];
            double differenz_x = next_position.getX() - current_position.getX();
            double differenz_y = next_position.getY() - current_position.getY();
            Vector2D differenz_vector = new Vector2D(differenz_x, differenz_y);
            perimeter = perimeter + differenz_vector.length();
        }
        return perimeter;
    }

    /* Diese Methode berechnet die Fläche unseres Polygons und in der
    Aufgabe wurde vorgeschlagen, unser Polygon in Dreiecke zu zerlegen,
    ihre Flächen zu berechnen und sie dann zu addieren, also habe ich
    das getan. Für den Hauptscheitelpunkt, der allen Dreiecken gemeinsam
    ist, habe ich den letzten Scheitelpunkt genommen, wie in der Abbildung
    gezeigt. Dann generieren wir die benötigten Dreiecke und verwenden dann
    die Flächenmethode, die wir bereits in der Triangle-Klasse implementiert haben */
    @Override
    public double area() {
        double area = 0;
        Vector2D knote = vertices[vertices.length - 1];
        for (int i = 0; i < vertices.length; i++) {
            Vector2D current_position = vertices[i];
            Vector2D next_position = vertices[(i + 1) % vertices.length];
            Triangle triangle = new Triangle(knote, current_position, next_position);
            area = area + triangle.area();
        }
        return area;
    }
}
