import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int[] Parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 노드의 비용이 제일 저렴한 순으로 힙 정렬
        PriorityQueue<Node> PQ = new PriorityQueue<>((n1, n2) -> (n1.cost - n2.cost));

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                PQ.add(new Node(i, j, Math.min(map[i][j], map[j][i])));
            }
        }

        // 유니온 파인드
        Parent = new int[n];
        for (int i = 0; i < Parent.length; i++) {
            Parent[i] = i;
        }

        // 크루스칼 알고리즘을 활용해서 최소 비용을 도출하자.
        long min_cost = 0L;
        int count = 0;
        while (!PQ.isEmpty()) {
            Node node = PQ.poll();
            if (find(node.from) != find(node.to)) {
                union(node.to, node.from);
                min_cost += node.cost;
                count++;
            }
            if (count == n - 1) break;
        }

        System.out.println(min_cost);

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