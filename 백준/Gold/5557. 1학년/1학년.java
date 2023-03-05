import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long[][] dp = new long[len - 1][21];
        if (0 <= arr[0] - arr[1]) dp[1][arr[0] - arr[1]] += 1;
        if (arr[0] + arr[1] <= 20) dp[1][arr[0] + arr[1]] += 1;
        for (int i = 2; i < len - 1; i++) {
            for (int j = 0; j < 21; j++) {
                if (dp[i - 1][j] != 0) {
                    if (0 <= j - arr[i]) dp[i][j - arr[i]] += dp[i - 1][j];
                    if (j + arr[i] <= 20) dp[i][j + arr[i]] += dp[i - 1][j];
                }
            }
        }

        System.out.println(dp[len - 2][arr[len - 1]]);
    }
}