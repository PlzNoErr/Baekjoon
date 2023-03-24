import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextInt();
        long k = sc.nextInt();
        if (n <= k) {
            System.out.println(2 * n - 1);
            return;
        }
        long ans = 0;
        long mid = n * n - k * (k + 1);
        ans += k * 2;
        ans += (mid) / k;
        if (mid % k != 0) ans++;
        System.out.println(ans);
    }
}
