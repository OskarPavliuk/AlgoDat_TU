package aufgabe_1;

import java.util.Arrays;

public class Aufgabe1 {
    /* Two-Sum Problem: Es sind ein Array und eine Zahl k gegeben, beide vom Typ int. Schreiben Sie eine Methode,
    welche prüft, ob es in dem Array ein Paar gibt, welches in der Summe k ergibt;
     */

    // Laufzeit: N^2
    private static boolean sumToTarget(int k, int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] + array[j] == k) {
                    System.out.println(array[i] + " + " + array[j] + " = " + k);
                    return true;
                }
            }
        }
        return false;
    }

    // Laufzeit: N*log(N);
    private static boolean sumToTarget2(int k, int[] array) {
        Arrays.sort(array);
        int i = 0;
        int j = array.length - 1;
        while (i < j) {
            int sum = array[i] + array[j];
            if (sum == k) {
                System.out.println(array[i] + " + " + array[j] + " = " + k);
                return true;
            } else if (sum > k) j--;
            else i++;
        }
        return false;
    }


    public static void main(String[] args) {
        int k = 3;
        int[] test1 = {0, 1, 2, 3, 4};
        int[] test2 = new int[5];
        for (int i = 0; i < 5; i++) {
            test2[i] = 1;
        }
        sumToTarget(k, test1);
        System.out.println(sumToTarget(k, test2));
    }
}
