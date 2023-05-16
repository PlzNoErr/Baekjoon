import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static ArrayList<ArrayList<Vt>> list;
    static long[] dist;
    static long INF = Integer.MAX_VALUE * 1000L;
    static int v, e;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        Set<Integer> set = new HashSet<>();
        st = new StringTokenizer(br.readLine());
        int idx = 0;
        while (st.hasMoreTokens()) {
            if (st.nextToken().equals("1") && idx != v - 1) set.add(idx);
            idx++;
        }
        list = new ArrayList<>();
        for (int i = 0; i < v; i++) list.add(new ArrayList<>());

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            if (set.contains(a) || set.contains(b)) continue;
            list.get(a).add(new Vt(b, cost));
            list.get(b).add(new Vt(a, cost));
        }
        djkstra(0);
        System.out.println(dist[v - 1] == INF ? -1 : dist[v - 1]);

    }

    static void djkstra(int start) {
        dist = new long[v];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Vt> PQ = new PriorityQueue<>((v1, v2) -> Long.compare(v1.cost, v2.cost));
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
        int to;
        long cost;

        public Vt(int to, long cost) {
            this.to = to;
            this.cost = cost;
        }
    }
}
