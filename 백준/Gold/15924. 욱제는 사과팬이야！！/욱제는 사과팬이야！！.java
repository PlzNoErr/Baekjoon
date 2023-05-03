import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int MD = 1_000_000_009;
    static int[] dr = {-1, 0};
    static int[] dc = {0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        char[][] map = new char[H][W];
        long[][] dp = new long[H][W];
        for (int i = 0; i < H; i++) {
            String s = br.readLine();
            for (int j = 0; j < W; j++) {
                map[i][j] = s.charAt(j);
                dp[i][j] = 1;
            }
        }
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                for (int k = 0; k < 2; k++) {
                    int r = i + dr[k];
                    int c = j + dc[k];
                    if (r < 0 || H <= r || c < 0 || W <= c) continue;
                    if (k == 0){
                        if(map[r][c] != 'E') {
                        dp[i][j] += dp[r][c];
                        dp[i][j] %= MD;
                        }
                         continue;
                    }
                    if (k == 1 && map[r][c] != 'S') {
                        dp[i][j] += dp[r][c];
                        dp[i][j] %= MD;
                    }
                }
            }
        }
        System.out.println(dp[H - 1][W - 1]);

    }//
}
