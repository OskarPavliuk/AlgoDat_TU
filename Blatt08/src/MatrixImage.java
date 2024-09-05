import java.io.*;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MatrixImage implements Image {
    public int[][] field;

    /**
     * A Constructor for the MatrixImage class
     * @param sx the size on the x-axis
     * @param sy the size on the y-axis
     */
    public MatrixImage(int sx, int sy) {
        field = new int[sx][sy];
    }

    /**
     * A deep dopy constructor for a given MatrixImage that
     * @param that the to be copied MatrixImage
     */
    public MatrixImage(MatrixImage that) {
        this(that.sizeX(), that.sizeY());
        for (int x = 0; x < sizeX(); x++) {
            field[x] = that.field[x].clone();
        }
    }

    /**
     * Initializes a MatrixImage from a given file
     * @param filename the file
     * @throws FileNotFoundException if there is no such file
     */
    public MatrixImage(String filename) throws FileNotFoundException {
        System.setIn(new FileInputStream(filename));
        Scanner in = new Scanner(System.in);
        int sx = in.nextInt();
        int sy = in.nextInt();
        field = new int[sx][sy];
        for (int y = 0; y < sy; y++) {
            for (int x = 0; x < sx; x++) {
                field[x][y] = in.nextInt();
            }
        }
    }

    /**
     * @return the size on the x-axis
     */
    @Override
    public int sizeX() {
        return field.length;
    }

    /**
     * @return the size on the y-axis
     */
    @Override
    public int sizeY() {
        return field[0].length;
    }

    /**
     * Calculates the contrast between two coordinates/nodes
     * @param p0 first coordinate
     * @param p1 second coordinate
     * @return the absolute value of the contrast
     * @throws InputMismatchException if the coordinates are not in the image
     */
    @Override
    public double contrast(Coordinate p0, Coordinate p1) throws InputMismatchException {
        // TODO
        /*
        Wir prüfen, ob die Koordinaten nicht außerhalb des Arrays liegen
        */
        if (p0.x < 0 || p1.x < 0 || p0.y < 0 || p1.y < 0 || p0.x > field.length - 1 || p0.y > field[0].length - 1 ||
                p1.x > field.length - 1 || p1.y > field[0].length - 1) {
            throw new InputMismatchException("Wrong coordinates");
        } else return Math.abs(field[p0.x][p0.y] - field[p1.x][p1.y]);
    }

    /**
     * Removes the given vertical path from the image.
     * Create a deep copy of the image with the correct new Matrix size.
     * @param path the do be deleted vertical path
     */
    @Override
    public void removeVPath(int[] path) {
        // TODO
        /*
        Wir erstellen zunächst ein neues Array newArray, das eine Spalte weniger hat,
        d.h. die Länge dieses Arrays ist kürzer. Als nächstes erhalten wir den Wert,
        den wir aus dem Array löschen müssen, anhand des Index und des Werts an diesem Index.
        Und dann füllen wir unser neues Array ohne diese Werte
        */
        int[][] newArray = new int[field.length - 1][field[0].length];
        IntStream.range(0, field[0].length).forEach(y -> {
            int valueToDelete = path[y];
            List<Integer> newRow = IntStream.range(0, field.length)
                    .filter(x -> x != valueToDelete)
                    .mapToObj(x -> field[x][y])
                    .toList();
            IntStream.range(0, field.length - 1).forEach(x -> newArray[x][y] = newRow.get(x));
        });
        field = newArray;
    }

    @Override
    public String toString() {
        String str = "";
        for (int y = 0; y < sizeY(); y++) {
            for (int x = 0; x < sizeX(); x++) {
                str += field[x][y] + " ";
            }
            str += "\n";
        }
        return str;
    }

    @Override
    public void render() {
        System.out.println(toString());
    }

}

