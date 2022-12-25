import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        int ans = 1;
        for (int i = 0; i < n; i++) {
            if (arr[i] == i + 1) continue;
            else if (arr[i] > i + 1) {
                ans = 0;
                break;
            } else {
                arr[i] += k;
                i--;
            }
            Arrays.sort(arr);
        }
        System.out.println(ans);
    }
}