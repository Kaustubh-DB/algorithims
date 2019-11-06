import java.util.*;
import java.io.*;
import java.lang.*;

class DualPivotQuickSort {
  void printArray(int[] array) {
    for (int i = 0; i < array.length; i++) {
      if (i == (array.length - 1)) {
        System.out.print(array[i]);
      } else
        System.out.print(array[i] + " ");
    }
    System.out.println();
  }
  void SwapArray(int[] a, int b, int c) {
    int temp = a[b];
    a[b] = a[c];
    a[c] = temp;
  }
  void dp(int[] A, int left, int right) {
    if (right - left >= 1) {
      int p = A[left]; int q = A[right];
      if (p < q) {int temp = p; p = q; q = temp; SwapArray(A, left, right);}
      int l = left + 1; int g = right - 1; int k = l;
      while (k <= g) {
        if (A[k] > p) {
          SwapArray(A, k, l);
          l = l + 1;
        } else {
          if (A[k] < q) {
            while (A[g] < q && k < g) {g = g - 1;}
            SwapArray(A, k, g);
            g = g - 1;
            if (A[k] > p) {
              SwapArray(A, k, l);
              l = l + 1;
            }
          }
        }
        k = k + 1;
      }
      l = l - 1; g = g + 1;
      SwapArray(A, left, l);
      SwapArray(A, right, g);
      dp(A, left, l - 1); dp(A, l + 1, g - 1); dp(A, g + 1, right);
    }
  }
  public static void main(String[] args) throws Exception{
    File file = new File(args[0]);
    Scanner sc = new Scanner(file);
    int elements = 0;
    while (sc.hasNext()) {
      elements++;
      sc.nextInt();
    }
    sc.close();
    Scanner input = new Scanner(file);
    int[] array = new int[elements];
    for (int i = 0; input.hasNext(); i++) {
      array[i] = input.nextInt();
    }
    DualPivotQuickSort qust = new DualPivotQuickSort();
    qust.dp(array, 0, elements - 1);
    qust.printArray(array);
  }
}
