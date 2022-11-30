import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Long> PQ = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            PQ.add(Long.parseLong(br.readLine()));
        }

        if (n == 1) {
            System.out.println(0);
            return;
        }

        long ans = 0;
        while (PQ.size() != 1) {
            long k = PQ.poll() + PQ.poll();
            ans += k;
            PQ.add(k);

        }
        System.out.println(ans);
    }//
}