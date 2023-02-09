import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int required_people = Integer.parseInt(st.nextToken());
        int choices = Integer.parseInt(st.nextToken());
        int[][] arr = new int[choices][2];
        for (int i = 0; i < choices; i++) {
            st = new StringTokenizer(br.readLine());
            // 비용
            arr[i][0] = Integer.parseInt(st.nextToken());
            // 고객수
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        int[] dp = new int[required_people + 101];
        Arrays.fill(dp, Integer.MAX_VALUE / 100);
        dp[0] = 0;
        for (int i = 0; i < choices; i++) {
            for (int j = 0; j + arr[i][1] <= required_people + 100; j++) {
                dp[j + arr[i][1]] = Math.min(dp[j + arr[i][1]], dp[j] + arr[i][0]);
            }
        }
        int min = dp[required_people];
        for (int i = required_people; i <= required_people + 100; i++) {
            min = Math.min(dp[i], min);
        }
        System.out.println(min);

    }
}
