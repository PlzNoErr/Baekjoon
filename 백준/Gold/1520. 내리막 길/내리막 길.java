import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int M, N;
    static int[][] arr, dp;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        arr = new int[M][N];
        dp = new int[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 모든 dp를 -1로 초기화
        for (int i = 0; i < M; i++) {
            Arrays.fill(dp[i], -1);
        }
        System.out.println(dfs(0, 0));
    }

    public static int dfs(int r, int c) {
        if (r == M - 1 && c == N - 1) {
            return 1;
        }

        if (dp[r][c] != -1) {
            return dp[r][c];
        }

        dp[r][c] = 0;
        for (int i = 0; i < 4; i++) {
            int R = r + dr[i];
            int C = c + dc[i];

            if (0 <= R && R < M && 0 <= C && C < N && arr[r][c] > arr[R][C]) {
                dp[r][c] += dfs(R, C);
            }
        }

        return dp[r][c];
    }
}