import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        long sum = 0;
        int start = 1;
        for (int i = 0; i < n; i++) {
            if (arr[i] > start) {
                sum += arr[i] - start;
                start++;
            } else if (arr[i] == start) start++;
        }
        System.out.println(sum);
    }
}
