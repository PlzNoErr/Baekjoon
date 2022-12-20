import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static List<ArrayList<Node>> list;
    static int[][] dist;
    static int n, v, e;
    final static int INF = Integer.MAX_VALUE / 10;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int finish = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for (int i = 0; i <= v; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list.get(y).add(new Node(x, cost, 0));
            list.get(x).add(new Node(y, cost, 0));
        }

        // 세금의 누적합
        int[] tax = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            tax[i] = Integer.parseInt(br.readLine()) + tax[i - 1];
        }

        dijkstra(start);
        int min;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= n; i++) {
            min = INF;
            for (int j = 0; j < v; j++) {
                min = Math.min(min, dist[j][finish] + j * tax[i]);
            }
            sb.append(min + "\n");
        }
        System.out.println(sb.toString());

    }//

    static void dijkstra(int start) {
        dist = new int[v + 1][v + 1];
        for (int[] arr : dist) Arrays.fill(arr, INF);
        for (int i = 0; i < v; i++) dist[i][start] = 0;
        PriorityQueue<Node> PQ = new PriorityQueue<>((n1, n2) -> n1.cost - n2.cost);
        PQ.add(new Node(start, 0, 0));
        while (!PQ.isEmpty()) {
            Node node = PQ.poll();
            if (dist[node.time][node.vt] < node.cost || node.time == v) continue;

            for (Node target : list.get(node.vt)) {
                if (dist[node.time + 1][target.vt] > dist[node.time][node.vt] + target.cost) {
                    dist[node.time + 1][target.vt] = dist[node.time][node.vt] + target.cost;
                    PQ.add(new Node(target.vt, dist[node.time + 1][target.vt], node.time + 1));
                }
            }
        }
    }//

    static class Node {
        int vt, cost, time;

        public Node(int vt, int cost, int time) {
            this.vt = vt;
            this.cost = cost;
            this.time = time;
        }
    }//
}