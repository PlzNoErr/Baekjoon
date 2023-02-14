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

        int i = 0;
        int j = arr.length - 1;

        Arrays.sort(arr);

        long gap = Long.MAX_VALUE;
        long ans1 = 0;
        long ans2 = 0;

        long temp;
        long sum;
        while (i < j) {
            sum = arr[i] + arr[j];
            temp = Math.abs(sum);
            if (temp < gap) {
                gap = temp;
                ans1 = arr[i];
                ans2 = arr[j];
            }
            if (sum > 0)
                j--;
            else
                i++;
        }

        System.out.println(ans1 + ans2);
    }
}