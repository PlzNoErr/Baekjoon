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
        int limit = Integer.parseInt(st.nextToken());
        int[] info = new int[n];
        int[] dp = new int[limit + 1];
        Arrays.fill(dp, 100000000);
        dp[0] = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) info[i] = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            for (int j = limit - info[i]; j >= 0; j--) dp[j + info[i]] = Math.min(dp[j + info[i]], dp[j] + 1);
        }
        System.out.println(dp[limit] == 100000000 ? -1 : dp[limit]);
    }
}
