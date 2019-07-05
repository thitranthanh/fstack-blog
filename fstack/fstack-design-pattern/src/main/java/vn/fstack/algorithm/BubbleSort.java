package vn.fstack.algorithm;

public class BubbleSort {

    private static void sort(int a[], int n) {
        int step = 1;
        int i, j, temp;
        boolean swapped;
        for (i = 0; i < n - 1; i++) {
            swapped = false;
            for (j = 0; j < n - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    swapped = true;
                }
                show(a, n, step++);
            }
            
            if (swapped == false) {
                break;
            }
        }
    }

    private static void show(int a[], int size, int step) {
        int i;
        System.out.print("Step " + (step < 10 ? "0" + step : step) + ": ");
        for (i = 0; i < size; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static void main(String args[]) {
        int a[] = { 6, 5, 3, 1, 8, 7, 2, 4 };
        int n = a.length;
        sort(a, n);
    }

}