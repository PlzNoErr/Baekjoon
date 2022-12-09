import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] Arr = new int[n];

        int max_value = 0;
        for (int i = 0; i < n; i++) {
            Arr[i] = Integer.parseInt(br.readLine());
            max_value = Math.max(max_value, Arr[i]);
        }

        long low = 1L;
        long high = (max_value) * 1000000000L;

        while (low <= high) {
            long mid = (low + high) / 2;
            long cnt = 0;
            for (int i = 0; i < n; i++) {
                cnt += (mid / Arr[i]);
            }
            if (cnt >= m) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        System.out.println(low);
    }
}