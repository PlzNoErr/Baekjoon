import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = sc.nextInt();
        int t = sc.nextInt();
        int r = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j-- > 0; ) {
                if (a[i] <= a[j] + t * 2 && a[i] >= a[j] - t * 2) r++;
            }
        }
        if (r == 0) System.out.printf("0.0");
        else System.out.printf("%.2f", r / 4.0);
    }
}
