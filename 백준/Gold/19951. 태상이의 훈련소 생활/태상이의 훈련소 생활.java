import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int tc = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        int[] dp = new int[n + 10];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());
        while (tc-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            dp[a] += c;
            dp[b] -= c;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < dp.length; i++) dp[i] += dp[i - 1];
        for (int i = 0; i < arr.length; i++) sb.append(arr[i] + dp[i] + " ");
        System.out.println(sb.toString());
    }
}
