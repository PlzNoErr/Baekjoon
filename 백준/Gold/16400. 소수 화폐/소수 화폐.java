import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static final int MOD = 123456789;
    static int n, cnt, size;
    static List<Integer> primeList;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        primeList = new ArrayList<>();
        boolean[] prime = new boolean[40001]; 
        prime[0] = true;
        prime[1] = true;
        for (int i = 2; i <= 40000; i++) {
            if (!prime[i]) {
                for (int j = i * i; j <= 40000; j += i) {
                    prime[j] = true;
                }
            }
        }
        for (int i = 2; i <= n; i++) {
            if (!prime[i]) {
                primeList.add(i);
            }
        }
        dp = new int[40001];
        dp[0] = 1;
        for (int i = 0; i < primeList.size(); i++) {
            int tmp = primeList.get(i);
            for (int j = tmp; j <= n; j++) {
                dp[j] = (dp[j] + dp[j - tmp]) % MOD;
            }
        }
        System.out.println(dp[n]);
    }
}