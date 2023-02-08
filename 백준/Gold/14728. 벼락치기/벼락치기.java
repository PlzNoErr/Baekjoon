import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        // 총 공부시간
        int limit = Integer.parseInt(st.nextToken());
        int[] dp = new int[limit + 1];
        int[][] info = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            //0: 공부시간 1: 배점
            info[i][0] = Integer.parseInt(st.nextToken());
            info[i][1] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < n; i++) {
            for (int j = limit; j - info[i][0] >= 0; j--) {
                dp[j] = Math.max(dp[j], dp[j - info[i][0]] + info[i][1]);
            }
        }
        System.out.println(dp[limit]);
    }
}