package aufgabe_2;

import java.util.ArrayList;

public class Snippets {
    private final String Name;
    private final int Age;
    private final int experience;

    public Snippets(String name, int age, int experience) {
        Name = name;
        Age = age;
        this.experience = experience;
    }

    public String getName() {
        return Name;
    }

    public int getAge() {
        return Age;
    }

    public int getExperience() {
        return experience;
    }

    @Override
    public String toString() {
        return "Snippets{" +
                "Name='" + Name + '\'' +
                ", Age=" + Age +
                ", experience=" + experience +
                '}';
    }

    public static void main(String[] args) {
        ArrayList<Snippets> listOfSnippets = new ArrayList<>();
        Snippets test_1 = new Snippets("Jeremy", 25, 10);
        Snippets test_2 = new Snippets("Nico", 34, 15);
        Snippets test_3 = new Snippets("Paul", 19, 18);
        listOfSnippets.add(test_1);
        listOfSnippets.add(test_2);
        listOfSnippets.add(test_3);
        listOfSnippets.sort(new SortByAge());
        System.out.println(listOfSnippets);
    }
}
