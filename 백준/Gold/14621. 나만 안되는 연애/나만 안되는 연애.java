import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int[] parents;
    static String[] sex;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        sex = new String[v + 1];
        parents = new int[v + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= v; i++) {
            sex[i] = st.nextToken();
            parents[i] = i;
        }

        PriorityQueue<Node> PQ = new PriorityQueue<>((n1, n2) -> n1.cost - n2.cost);
        while (e-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            PQ.add(new Node(a, b, cost));
            PQ.add(new Node(b, a, cost));
        }

        int ans = 0;
        int count = 0;
        while (!PQ.isEmpty()) {
            Node node = PQ.poll();
            if (find(node.from) != find(node.to) && !sex[node.from].equals(sex[node.to])) {
                union(node.from, node.to);
                ans += node.cost;
                count++;
            }
        }
        System.out.println(count == v - 1 ? ans : -1);
    }

    static int find(int x) {
        if (parents[x] == x) {
            return x;
        } else {
            return parents[x] = find(parents[x]);
        }
    }// find

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
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
