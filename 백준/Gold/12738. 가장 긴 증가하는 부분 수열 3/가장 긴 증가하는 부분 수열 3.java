import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] dp = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());


        dp[0] = arr[0];
        int j = 0;

        for (int i = 1; i < N; i++) {
            if (dp[j] < arr[i]) {
                dp[++j] = arr[i];
            } else {
                int ans = binarySearch(dp, 0, j, arr[i]);
                dp[ans] = Math.min(dp[ans], arr[i]);
            }
        }
        System.out.println(j + 1);
    }

    static int binarySearch(int[] dp, int startIndex, int endIndex, int key) {
        while (startIndex <= endIndex) {
            int mid = ((startIndex + endIndex) / 2);
            if (dp[mid] < key) {
                startIndex = mid + 1;
            } else {
                endIndex = mid - 1;
            }
        }
        return startIndex;
    }
}
