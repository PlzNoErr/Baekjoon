import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        int dp[][] = new int[k + 1][T + 1];

        dp[k][0] = 1;
        for (int i = 1; i < k + 1; i++) {
            dp[i - 1][0] = 1;
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] coin = {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
            for (int j = 1; j < T + 1; j++)
                for (int m = 0; m <= coin[1] && m * coin[0] <= j; m++)
                    dp[i][j] += dp[i - 1][j - m * coin[0]];
        }
        System.out.println(dp[k][T]);
    }

}
