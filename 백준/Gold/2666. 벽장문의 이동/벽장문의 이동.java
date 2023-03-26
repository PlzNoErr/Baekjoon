import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static int n, k, open[], dp[][][];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        int x = sc.nextInt(), y = sc.nextInt();
        k = sc.nextInt();
        open = new int[k + 1];
        dp = new int[k + 1][n + 1][n + 1];

        for (int i = 0; i <= k; i++)
            for (int j = 0; j <= n; j++)
                Arrays.fill(dp[i][j], -1);

        for (int i = 1; i <= k; i++) open[i] = sc.nextInt();
        System.out.println(move(1, x, y));
    }

    private static int move(int step, int x, int y) {
        if (step > k) return 0;
        if (dp[step][x][y] != -1) return dp[step][x][y];
        return dp[step][x][y] = Math.min(
                Math.abs(y - open[step]) + move(step + 1, x, open[step]),
                Math.abs(x - open[step]) + move(step + 1, y, open[step]));
    }
}