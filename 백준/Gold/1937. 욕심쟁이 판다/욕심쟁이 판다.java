import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map, dp;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                max = Math.max(max, dfs(i, j));
            }
        }
        System.out.println(max);
    }

    public static int dfs(int r, int c) {
        if (dp[r][c] != 0) {
            return dp[r][c];
        }

        // 최소한 1년은 살 수 있기 때문에 1로 초기화
        dp[r][c] = 1;
        
        for (int i = 0; i < 4; i++) {
            int R = r + dr[i];
            int C = c + dc[i];

            if (0 <= R && R < N && 0 <= C && C < N && map[r][c] < map[R][C]) {
                // 4방을 탐색하면서 가장 오래 살아남는게 가능한 루트로 값을 업데이트
                dp[r][c] = Math.max(dp[r][c], dfs(R, C) + 1);
            }
        }
        return dp[r][c];
    }
}