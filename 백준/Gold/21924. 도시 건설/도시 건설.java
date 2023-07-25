import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int[] Parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 정점의 개수 v, 간선의 개수 e
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        PriorityQueue<Node> PQ = new PriorityQueue<>((e1, e2) -> e1.cost - e2.cost);
        long total = 0L;
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            total += cost;
            PQ.add(new Node(from, to, cost));
            PQ.add(new Node(to, from, cost));
        }

        // 유니온 파인드
        Parent = new int[v + 1];
        for (int i = 1; i < Parent.length; i++) Parent[i] = i;

        // 크루스칼 알고리즘을 활용해서 최소 비용을 도출하자.
        long min_cost = 0;
        int connect = 0;
        while (!PQ.isEmpty()) {
            Node node = PQ.poll();
            if (find(node.from) != find(node.to)) {
                connect++;
                union(node.to, node.from);
                min_cost += node.cost;
            }
        }
        if (connect != v - 1) System.out.println(-1);
        else System.out.println(total - min_cost);
    }// main

    static int find(int x) {
        if (Parent[x] == x) return x;
        else return find(Parent[x]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a > b) Parent[a] = b;
        else Parent[b] = a;
    }

    static class Node {
        int from, to, cost;

        public Node(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

    }// Node
}