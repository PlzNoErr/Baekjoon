import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] info = new int[n][m];
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) info[i][j] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i < n; i++) Arrays.fill(dp[i], Integer.MAX_VALUE);
        for (int i = 0; i < m; i++) dp[0][i] = info[0][i];

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < m; k++) {
                    if (k != j) dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + info[i][j]);
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) min = Math.min(min, dp[n - 1][i]);
        System.out.println(min);
    }//
}
