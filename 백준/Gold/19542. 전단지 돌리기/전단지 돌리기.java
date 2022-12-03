import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int v, start, dep;
    static ArrayList<ArrayList<Integer>> graph;
    static int count;
    static int[] dep_info;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());
        dep = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        for (int i = 0; i <= v; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < v - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph.get(x).add(y);
            graph.get(y).add(x);
        }
        count = 0;
        dep_info = new int[v + 1];
        dfs(start, 0);
        System.out.println(2 * count);
    }

    public static int dfs(int node, int parentNode) {
        // 맨 자식까지 dfs 타고 도착하면 거기서 부터 순차적으로 개수를 채우면서 올라온다.
        // 자신의 자식노드의 최대 깊이를 구하게 한다. 거기서 꼭 들려야만 하는 count가 나올경우
        // 갔다가 돌아와야 하니까 2*count 출력. start = root노드
        for (int Node : graph.get(node)) {
            if (Node != parentNode) {
                dep_info[node] = Math.max(dep_info[node], dfs(Node, node) + 1);
            }
        }

        // 본인이 시작노드가 아니면서 dep보다 크거나 같다, 즉 무조건 여기까지는 들른다.
        if (node != start && dep_info[node] >= dep) {
            count++;
        }

        return dep_info[node];
    }//
}