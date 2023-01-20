import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        int count = Integer.parseInt(br.readLine());

        for (int i = 0; i < n - 1; i++) {
            int max = arr[i];
            int idx = i;
            for (int j = i + 1; j < Math.min(n, i + count + 1); j++) {
                if (max < arr[j]) {
                    max = arr[j];
                    idx = j;
                }
            }
            int reverse = 0;
            for (int j = idx; i < j; j--) {
                int temp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = temp;
                reverse++;
            }
            count -= reverse;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(arr[i] + " ");
        }
        System.out.println(sb.toString());
    }
}