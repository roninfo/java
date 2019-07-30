package hackerrank;

import org.junit.Test;

public class BubbleSort {

    @Test
    public void solution() {
        int[] a = {6,4,1};

        int countSwaps = 0;
        for (int i = 0; i < a.length; i++) {

            for (int j = 0; j < a.length - 1; j++) {
                int elementActual = a[j];
                int elementNext = a[j + 1];

                if (elementNext < elementActual) {
                    a[j] = elementNext;
                    a[j+1] = elementActual;
                    countSwaps++;
                }
            }
        }
        System.out.println("Array is sorted in " + countSwaps + " swaps.");
        System.out.println("First Element: " + a[0]);
        System.out.println("Last Element: " + a[a.length-1]);
    }
}
