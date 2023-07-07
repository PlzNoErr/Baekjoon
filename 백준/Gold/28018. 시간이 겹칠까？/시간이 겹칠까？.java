import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = new int[1000005];
        int tc = Integer.parseInt(br.readLine());
        StringTokenizer st;
        while (tc-- > 0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            arr[start]++;
            arr[end + 1]--;
        }
        for (int i = 1; i <= 1000003; i++) arr[i] += arr[i - 1];
        tc = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (tc-- > 0) {
            int ask = Integer.parseInt(st.nextToken());
            sb.append(arr[ask] + "\n");
        }
        System.out.println(sb.toString());
    }
}
