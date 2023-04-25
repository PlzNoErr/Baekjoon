import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken()) + 1;
        int[] parents1 = new int[v + 1];
        int[] parents2 = new int[v + 1];
        for (int i = 1; i <= v; i++) {
            parents1[i] = i;
            parents2[i] = i;
        }
        PriorityQueue<Node> PQ1 = new PriorityQueue<>((n1, n2) -> n1.cost - n2.cost);
        PriorityQueue<Node> PQ2 = new PriorityQueue<>((n1, n2) -> n2.cost - n1.cost);
        while (e-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            PQ1.add(new Node(a, b, (cost + 1) % 2));
            PQ1.add(new Node(b, a, (cost + 1) % 2));
            PQ2.add(new Node(a, b, (cost + 1) % 2));
            PQ2.add(new Node(b, a, (cost + 1) % 2));
        }
        int ans1 = 0;
        while (!PQ1.isEmpty()) {
            Node node = PQ1.poll();
            if (find(node.from, parents1) != find(node.to, parents1)) {
                union(node.from, node.to, parents1);
                ans1 += node.cost;
            }
        }

        int ans2 = 0;
        while (!PQ2.isEmpty()) {
            Node node = PQ2.poll();
            if (find(node.from, parents2) != find(node.to, parents2)) {
                union(node.from, node.to, parents2);
                ans2 += node.cost;
            }
        }
        System.out.println(ans2 * ans2 - ans1 * ans1);
    }

    static int find(int x, int[] parents) {
        if (parents[x] == x) {
            return x;
        } else {
            return parents[x] = find(parents[x], parents);
        }
    }// find

    static void union(int a, int b, int[] parents) {
        a = find(a, parents);
        b = find(b, parents);
        if (a > b) {
            parents[a] = b;
        } else {
            parents[b] = a;
        }
    }// union

    static class Node {
        int to, from, cost;

        public Node(int to, int from, int cost) {
            this.to = to;
            this.from = from;
            this.cost = cost;
        }
    }
}
