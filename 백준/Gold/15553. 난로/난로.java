import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken()) - 1;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(br.readLine());
        int max = arr[n - 1] - arr[0] + 1;
        PriorityQueue<Integer> PQ = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 1; i < n; i++) PQ.add(arr[i] - arr[i - 1] - 1);
        while (m-- > 0 && !PQ.isEmpty()) max -= PQ.poll();
        System.out.println(max);
    }
}
