package vn.fstack.algorithm;

public class InsertionSort {

    void sort(int a[]) {
        int n = a.length;
        int step = 1;
        for (int i = 1; i < n; ++i) {
            int key = a[i];
            int j = i - 1;

            while (j >= 0 && a[j] > key) {
                a[j + 1] = a[j];
                j = j - 1;
            }
            a[j + 1] = key;
            show(a, step++);
        }
    }

    static void show(int a[], int step) {
        int n = a.length;
        System.out.print("Step " + (step < 10 ? "0" + step : step) + ": ");
        for (int i = 0; i < n; ++i)
            System.out.print(a[i] + " ");

        System.out.println();
    }

    public static void main(String args[]) {
        int a[] = { 6, 5, 3, 1, 8, 7, 2, 4 };
        InsertionSort is = new InsertionSort();
        is.sort(a);
    }

}
