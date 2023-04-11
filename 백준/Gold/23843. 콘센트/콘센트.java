import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Integer[] arr = new Integer[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, (e1, e2) -> e2 - e1);
        int[] TimeArr = new int[M];
        int idx = 0;
        for (int i = 0; i < N; i++) {
            if (idx == 0) {
                TimeArr[idx] += arr[i];
                idx = (idx + 1) % M;
                continue;
            }

            TimeArr[idx] += arr[i];
            if (TimeArr[idx] == TimeArr[idx - 1]) idx = (idx + 1) % M;
        }
        System.out.println(TimeArr[0]);
    }
}
