import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] bright = new int[n];
        int[] state = new int[n];
        int[] dp_off = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            bright[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            state[i] = Integer.parseInt(st.nextToken());
        }

        dp_off[0] = state[0] == 0 ? bright[0] : 0;

        for (int i = 1; i < n; i++) {
            dp_off[i] = dp_off[i - 1] + (state[i] == 0 ? bright[i] : -bright[i]);
            if (dp_off[i] < 0) dp_off[i] = 0;
        }

        int max = 0;
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (dp_off[i] > max) {
                max = dp_off[i];
                idx = i;
            }
        }

        if (max == 0) {
            int min = 5000;
            int sum = 0;
            for (int i = 0; i < n; i++) {
                sum += bright[i];
                min = Math.min(min, bright[i]);
            }
            System.out.println(sum - min);
            return;
        }

        int min_idx = idx;
        for (int i = idx; i >= 0; i--) {
            if (dp_off[i] == 0) {
                min_idx = i;
                break;
            }
            if (i == 0) min_idx = i;
        }

        int sum = 0;
        for (int i = 0; i < min_idx; i++) {
            if (state[i] == 1) {
                sum += bright[i];
            }
        }

        for (int i = idx + 1; i < n; i++) {
            if (state[i] == 1) {
                sum += bright[i];
            }
        }

        for (int i = min_idx; i <= idx; i++) {
            if (state[i] == 0) {
                sum += bright[i];
            }
        }

        if (state[min_idx] == 1) sum += bright[min_idx];

        System.out.println(sum);

    }//
}