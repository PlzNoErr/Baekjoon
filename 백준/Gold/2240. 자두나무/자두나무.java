import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int w = sc.nextInt();

        int[][][] dp = new int[3][1001][32];
        int[] arr = new int[1001];
        int ans = 0;

        for (int i = 1; i <= t; i++) {
            arr[i] = sc.nextInt();
        }

        for (int i = 1; i <= t; i++) {
            for (int j = 1; j <= w + 1; j++) {
                if (arr[i] == 1) {
                    dp[1][i][j] = Math.max(dp[1][i - 1][j] + 1, dp[2][i - 1][j - 1] + 1);
                    dp[2][i][j] = Math.max(dp[1][i - 1][j - 1], dp[2][i - 1][j]);
                } else {
                    if (i == 1 && j == 1) {
                        continue;
                    }
                    dp[1][i][j] = Math.max(dp[2][i - 1][j - 1], dp[1][i - 1][j]);
                    dp[2][i][j] = Math.max(dp[1][i - 1][j - 1] + 1, dp[2][i - 1][j] + 1);
                }
            }
        }
        for (int i = 1; i <= w + 1; i++) {
            ans = Math.max(ans, Math.max(dp[1][t][i], dp[2][t][i]));
        }
        System.out.println(ans);
    }
}