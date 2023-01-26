import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static List<ArrayList<Node>> list;
    static int[] dist;
    static int time, v, e;
    final static int INF = Integer.MAX_VALUE / 10;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = v - 1;
        time = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for (int i = 0; i <= v; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list.get(x).add(new Node(y, cost));
            list.get(y).add(new Node(x, cost));
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < time; i++) {
            st = new StringTokenizer(br.readLine());
            dijkstra(Integer.parseInt(st.nextToken()));
            sb.append(dist[Integer.parseInt(st.nextToken())] + "\n");
        }
        System.out.printf(sb.toString());


    }//

    static void dijkstra(int start) {
        // dist배열을 초기화 & 시작지점 0으로 초기화
        dist = new int[v + 1];
        Arrays.fill(dist, INF);

        dist[start] = 0;
        PriorityQueue<Node> PQ = new PriorityQueue<>((n1, n2) -> n1.cost - n2.cost);
        PQ.add(new Node(start, 0));
        while (!PQ.isEmpty()) {
            Node node = PQ.poll();
            if (dist[node.vt] < node.cost) continue;

            for (Node target : list.get(node.vt)) {
                if (dist[target.vt] > dist[node.vt] + target.cost) {
                    dist[target.vt] = dist[node.vt] + target.cost;
                    PQ.add(new Node(target.vt, dist[target.vt]));
                }
            }
        }
    }//

    static class Node {
        int vt, cost;

        public Node(int vt, int cost) {
            this.vt = vt;
            this.cost = cost;
        }
    }//
}
