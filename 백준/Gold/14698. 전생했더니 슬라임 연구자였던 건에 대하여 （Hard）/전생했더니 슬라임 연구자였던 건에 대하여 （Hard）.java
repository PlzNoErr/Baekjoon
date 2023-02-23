import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (tc-- > 0) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            ArrayList<Long> list = new ArrayList<>();
            PriorityQueue<Long> PQ = new PriorityQueue<>();
            while (st.hasMoreTokens()) {
                PQ.add(Long.parseLong(st.nextToken()));
            }

            while (PQ.size() > 1) {
                long a = PQ.poll();
                long b = PQ.poll();

                list.add((a * b) % 1000000007);
                PQ.add(a * b);
            }

            long ans = 0;
            if (list.size() == 0) ans = 1;
            else ans = list.get(0);

            for (int i = 1; i < list.size(); i++) {
                ans *= list.get(i);
                ans %= 1000000007;
            }

            sb.append(ans % 1000000007);
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }//
}