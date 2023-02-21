import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp_left = new int[n];
        int[] dp_right = new int[n];

        if (arr[0] == 1) dp_left[0] = 1;
        else dp_right[0] = 1;


        for (int i = 1; i < n; i++) {
            if (arr[i] == 1) {
                dp_left[i] = dp_left[i - 1] + 1;
                dp_right[i] = dp_right[i - 1] - 1;
            } else {
                dp_left[i] = dp_left[i - 1] - 1;
                dp_right[i] = dp_right[i - 1] + 1;
            }
            if (dp_left[i] < 0) dp_left[i] = 0;
            if (dp_right[i] < 0) dp_right[i] = 0;
        }
        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, dp_left[i]);
            max = Math.max(max, dp_right[i]);
        }
        System.out.println(max);
    }
}