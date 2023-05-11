import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int[][] dp = new int[n + 1][n + 1];
        int[][] dp2 = new int[n + 1][n + 1];
        StringTokenizer st;
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            dp[a][b] = 1;
            dp2[b][a] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) continue;
                if (dp[i][j] != 0) continue;
                dp[i][j] = Integer.MAX_VALUE / 10;
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) continue;
                if (dp2[i][j] != 0) continue;
                dp2[i][j] = Integer.MAX_VALUE / 10;
            }
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                    dp2[i][j] = Math.min(dp2[i][j], dp2[i][k] + dp2[k][j]);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            int count = 0;
            for (int j = 1; j <= n; j++) {
                if (dp[i][j] == Integer.MAX_VALUE / 10 && dp2[i][j] == Integer.MAX_VALUE / 10) count++;
            }
            sb.append(count + "\n");
        }
        System.out.println(sb);
    }
}
