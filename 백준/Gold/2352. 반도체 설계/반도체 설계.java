import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n];
        dp[0] = arr[0];

        int idx = 1;
        int temp = 0;

        for (int i = 1; i < n; i++) {
            if (dp[idx - 1] < arr[i]) dp[idx++] = arr[i];
            else if (dp[0] > arr[i]) dp[0] = arr[i];
            else {
                temp = Arrays.binarySearch(dp, 0, idx, arr[i]);
                dp[temp < 0 ? (-temp - 1) : temp] = arr[i];
            }
        }
        System.out.println(idx);
    }//
}