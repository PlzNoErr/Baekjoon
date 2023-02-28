import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int[] dist;
    static int v, e;

    static ArrayList<ArrayList<Node>> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        for (int i = 0; i <= v; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list.get(from).add(new Node(to, cost));
            list.get(to).add(new Node(from, cost));
        }

        Dijkstra(1);
        System.out.println(dist[v]);
    }

    static void Dijkstra(int start) {
        dist = new int[v + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        PriorityQueue<Node> PQ = new PriorityQueue<>((n1, n2) -> n1.cost - n2.cost);
        PQ.add(new Node(start, 0));
        while (!PQ.isEmpty()) {
            Node vt = PQ.poll();
            if (vt.cost > dist[vt.to]) continue;
            for (Node want_go : list.get(vt.to)) {
                if (dist[want_go.to] > dist[vt.to] + want_go.cost) {
                    dist[want_go.to] = dist[vt.to] + want_go.cost;
                    PQ.add(new Node(want_go.to, dist[want_go.to]));
                }
            }
        }
    }//

    static class Node {
        int to, cost;

        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
}