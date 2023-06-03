import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int max = 0;
        PriorityQueue<Bus> PQ = new PriorityQueue<>((b1, b2) -> b1.end - b2.end);
        PriorityQueue<Bus> PQ_start = new PriorityQueue<>((b1, b2) -> b1.start - b2.start);

        while (n-- > 0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken().replace(":", "").replace(".", ""));
            int end = Integer.parseInt(st.nextToken().replace(":", "").replace(".", ""));
            PQ_start.add(new Bus(start, end));
        }

        while (!PQ_start.isEmpty()) {
            Bus bus = PQ_start.poll();
            while (!PQ.isEmpty()) {
                if (PQ.peek().end > bus.start) break;
                PQ.poll();
            }
            PQ.add(bus);
            max = Math.max(max, PQ.size());
        }
        System.out.println(max);
    }

    static class Bus {
        int start, end;

        public Bus(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
