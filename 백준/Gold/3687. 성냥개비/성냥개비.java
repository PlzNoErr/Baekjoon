import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        long[] dp = new long[101];

        // 최소값을 채우기 위한 dp
        Arrays.fill(dp, Long.MAX_VALUE);
        dp[2] = 1;
        dp[3] = 7;
        dp[4] = 4;
        dp[5] = 2;
        dp[6] = 6;
        dp[7] = 8;
        dp[8] = 10;

        String[] add = {"1", "7", "4", "2", "0", "8"};
        for (int i = 9; i <= 100; i++) {
            for (int j = 2; j <= 7; j++) {
                String line = dp[i - j] + add[j - 2];
                dp[i] = Math.min(Long.parseLong(line), dp[i]);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int M = sc.nextInt();
            sb.append(dp[M] + " ");
            int use1 = M / 2;
            if (M % 2 == 1) {
                use1--;
                sb.append(7);
            }
            for (int k = 1; k <= use1; k++) {
                sb.append(1);
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }
}