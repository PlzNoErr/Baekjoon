import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());
        long max = 0;
        int left = 0;
        int right = n - 1;
        while (left <= right) {
            int min = Math.min(arr[left], arr[right]);
            max = Math.max((right - left - 1) * min, max);
            if (arr[left] < arr[right]) left++;
            else right--;
        }
        System.out.println(max);
    }//
}
