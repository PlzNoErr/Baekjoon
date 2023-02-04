import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][2];
        int[][] dp = new int[n][n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            map[i][0] = Integer.parseInt(st.nextToken());
            map[i][1] = Integer.parseInt(st.nextToken());
        }
        
        for (int k = 1; k < n; k++) {
            for (int i = 0; i + k < n; i++) {
                dp[i][i + k] = Integer.MAX_VALUE;
                for (int j = i; j < i + k; j++)
                    dp[i][i + k] = Math.min(dp[i][i + k], dp[i][j] + dp[j + 1][i + k] + map[i][0] * map[j][1] * map[i + k][1]);
            }
        }
        System.out.println(dp[0][n - 1]);


    }
}