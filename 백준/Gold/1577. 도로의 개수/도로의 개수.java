import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K, a, b, c, d;
    static long[][] dp;
    static boolean[][][] road;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());
        road = new boolean[M + 1][N + 1][2];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            if (a < c || b < d) {
                if (a < c) road[b][a][0] = true;
                else road[b][a][1] = true;
            } else {
                if (c < a) road[d][c][0] = true;
                else road[d][c][1] = true;
            }
        }
        dp = new long[M + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            if (road[0][i - 1][0]) break;
            dp[0][i] = 1;
        }
        for (int i = 1; i <= M; i++) {
            if (road[i - 1][0][1]) break;
            dp[i][0] = 1;
        }
        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                if (!road[i][j - 1][0]) dp[i][j] += dp[i][j - 1];
                if (!road[i - 1][j][1]) dp[i][j] += dp[i - 1][j];
            }
        }
        System.out.println(dp[M][N]);
    }
}