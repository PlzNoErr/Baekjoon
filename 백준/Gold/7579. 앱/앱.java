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
        int[] memory = new int[n];
        int[] extra_cost = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            extra_cost[i] = Integer.parseInt(st.nextToken());
        }
        int[] dp = new int[10001];
        for (int j = 0; j < n; j++) {
            for (int i = 10000; i >= 0; i--) {
                if (i - extra_cost[j] >= 0)
                    dp[i] = Math.max(dp[i], dp[i - extra_cost[j]] + memory[j]);
            }
        }
        for (int i = 0; i <= 10000; i++) {
            if(dp[i]>=m){
                System.out.println(i);
                return;
            }
        }
    }
}