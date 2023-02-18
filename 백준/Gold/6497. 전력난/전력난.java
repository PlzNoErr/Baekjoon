import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int[] Parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            // 정점의 개수 v, 간선의 개수 e
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            if (v == 0 && e == 0) break;

            // 노드의 비용이 제일 저렴한 순으로 힙 정렬
            PriorityQueue<Node> PQ = new PriorityQueue<>((n1, n2) -> n1.cost - n2.cost);

            int total_cost = 0;
            for (int i = 0; i < e; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                total_cost += cost;
                PQ.add(new Node(from, to, cost));
                PQ.add(new Node(to, from, cost));
            }

            // 유니온 파인드
            Parent = new int[v];
            for (int i = 1; i < v; i++) {
                Parent[i] = i;
            }

            // 크루스칼 알고리즘을 활용해서 최소 비용을 도출하자.
            int min_cost = 0;
            while (!PQ.isEmpty()) {
                Node node = PQ.poll();
                if (find(node.from) != find(node.to)) {
                    union(node.to, node.from);
                    min_cost += node.cost;
                }
            }
            System.out.println(total_cost - min_cost);
        }
    }// main

    static int find(int x) {
        if (Parent[x] == x) {
            return x;
        } else {
            return Parent[x] = find(Parent[x]);
        }
    }// find

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a > b) {
            Parent[a] = b;
        } else {
            Parent[b] = a;
        }
    }// union

    static class Node {
        int from, to, cost;

        public Node(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

    }// Node

}