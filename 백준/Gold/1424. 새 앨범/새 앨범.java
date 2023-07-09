import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int l = scanner.nextInt();
        int c = scanner.nextInt();
        int ans = Integer.MAX_VALUE;
        for (int k = (c + 1) / (l + 1); k > 0; k--) {
            if (k % 13 == 0) continue;
            int r = n % k;
            if (r > 0) {
                if (r % 13 == 0 && (r + 1 == k || n / k == 0)) r = 2;
                else r = 1;
            }
            ans = Math.min(ans, n / k + r);
        }
        System.out.println(ans);
    }
}
