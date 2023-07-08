import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static int[][] list;
    public static PriorityQueue<Integer>[] distQueue;
    public static int k, n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        distQueue = new PriorityQueue[n + 1];
        for (int i = 1; i < n + 1; i++) distQueue[i] = new PriorityQueue<Integer>(k, (n1, n2) -> n2 - n1);
        list = new int[n + 1][n + 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list[a][b] = c;
        }
        dijkstra();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < n + 1; i++) {
            if (distQueue[i].size() == k) sb.append(distQueue[i].peek() + "\n");
            else sb.append("-1" + "\n");
        }
        System.out.println(sb.toString());
    }

    static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> n1.time - n2.time);
        pq.add(new Node(1, 0));
        distQueue[1].add(0);
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            for (int i = 1; i < n + 1; i++) {
                if (list[now.node][i] != 0) {
                    if (distQueue[i].size() < k) {
                        distQueue[i].add(now.time + list[now.node][i]);
                        pq.add(new Node(i, now.time + list[now.node][i]));
                    } else {
                        if (distQueue[i].peek() > now.time + list[now.node][i]) {
                            distQueue[i].poll();
                            distQueue[i].add(now.time + list[now.node][i]);
                            pq.add(new Node(i, now.time + list[now.node][i]));
                        }
                    }
                }
            }
        }
    }

    static class Node {
        int node, time;

        Node(int targetNode, int time) {
            this.node = targetNode;
            this.time = time;
        }
    }//
}

