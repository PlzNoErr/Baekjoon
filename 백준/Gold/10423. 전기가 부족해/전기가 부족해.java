import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Vt> PQ = new PriorityQueue<>((e1, e2) -> e1.cost - e2.cost);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int city = Integer.parseInt(st.nextToken());
        int line = Integer.parseInt(st.nextToken());
        int plant = Integer.parseInt(st.nextToken());
        parents = new int[city + 1];
        parents[0] = -1;
        for (int i = 0; i <= city; i++) parents[i] = i;

        st = new StringTokenizer(br.readLine());
        while (plant-- > 0) parents[Integer.parseInt(st.nextToken())] = -1;
        while (line-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            PQ.add(new Vt(a, b, cost));
        }

        long total_cost = 0;
        while (!PQ.isEmpty()) {
            Vt vt = PQ.poll();
            if (find(vt.from) != find(vt.to)) {
                union(vt.from, vt.to);
                total_cost += vt.cost;
            }
            if (check()) break;
        }

        System.out.println(total_cost);

    }

    static boolean check() {
        for (int child : parents) if (child != -1) return false;
        return true;
    }

    static int find(int x) {
        if (parents[x] == -1) return -1;
        if (parents[x] == x) return x;
        else return parents[x] = find(parents[x]);
    }//

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            if (x == -1) parents[y] = x;
            else if (y == -1) parents[x] = y;
            else if (x == -1 && y == -1) return;
            else parents[y] = x;
        }
    }//

    static class Vt {
        int from, to, cost;

        public Vt(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }//
}
