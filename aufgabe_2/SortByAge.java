package aufgabe_2;

import java.util.Comparator;

public class SortByAge implements Comparator<Snippets> {
    @Override
    public int compare(Snippets o1, Snippets o2) {
        return o1.getAge() - o2.getAge();
    }
}
