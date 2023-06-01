import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        PriorityQueue<Route> PQ = new PriorityQueue<>((r1, r2) -> r1.start == r2.start ? r1.end - r2.end : r1.start - r2.start);
        StringTokenizer st;
        while (tc-- > 0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            PQ.add(new Route(start, end, price));
        }
        int count = 0;
        StringBuilder sb = new StringBuilder();

        while (!PQ.isEmpty()) {
            Route route = PQ.poll();
            if (PQ.isEmpty()) {
                count++;
                sb.append(route.start + " " + route.end + " " + route.price);
                break;
            }
            if (PQ.peek().start <= route.end) {
                Route route2 = PQ.poll();
                PQ.add(new Route(route.start, Math.max(route.end, route2.end), Math.min(route.price, route2.price)));
                continue;
            }
            count++;
            sb.append(route.start + " " + route.end + " " + route.price + "\n");
        }

        System.out.println(count);
        System.out.println(sb.toString());
    }

    static class Route {
        int start, end, price;

        public Route(int start, int end, int price) {
            this.start = start;
            this.end = end;
            this.price = price;
        }
    }
}
