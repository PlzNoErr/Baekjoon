import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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
        Arrays.sort(arr);

        int ans = 0;

        for (int i = 0; i < arr.length; i++) {
            int target = arr[i];

            int start = 0;
            int end = arr.length - 1;
            int sum = 0;

            while (start < end) {
                sum = arr[start] + arr[end];
                if (sum == target) {
                    if (start == i) start++;
                    else if (i == end) end--;
                    else {
                        ans++;
                        break;
                    }
                }
                if (arr[start] + arr[end] > target) end--;
                else if (arr[start] + arr[end] < target) start++;
            }//while

        }//
        System.out.println(ans);
    }
}