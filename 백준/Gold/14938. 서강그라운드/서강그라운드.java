import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<ArrayList<Vt>> list;
    static int[] dist;
    static int INF = Integer.MAX_VALUE / 10;
    static int v, m, e;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            list.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        int[] item = new int[v];
        for (int i = 0; i < v; i++) {
            item[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());
            list.get(a).add(new Vt(b, cost));
            list.get(b).add(new Vt(a, cost));
        }

        int max = 0;
        for (int i = 0; i < v; i++) {
            djkstra(i);
            int sum = 0;
            for (int j = 0; j < v; j++) {
                if (dist[j] <= m) {
                    sum += item[j];
                }
            }
            max = Math.max(max, sum);
        }
        System.out.println(max);
    }

    static void djkstra(int start) {
        dist = new int[v];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Vt> PQ = new PriorityQueue<>((v1, v2) -> v1.cost - v2.cost);
        PQ.add(new Vt(start, 0));

        while (!PQ.isEmpty()) {
            Vt now = PQ.poll();
            if (now.cost > dist[now.to]) continue;
            for (Vt want : list.get(now.to)) {
                if (dist[want.to] > dist[now.to] + want.cost) {
                    dist[want.to] = dist[now.to] + want.cost;
                    PQ.add(new Vt(want.to, dist[want.to]));
                }
            }
        }
    }

    static class Vt {
        int to, cost;

        public Vt(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
}
