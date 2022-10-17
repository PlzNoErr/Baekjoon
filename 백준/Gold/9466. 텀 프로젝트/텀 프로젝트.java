import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] graph;
    static boolean[] visit;
    static boolean[] check;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        while (tc-- > 0) {
            int n = Integer.parseInt(br.readLine());
            graph = new int[n + 1];
            check = new boolean[n + 1];
            visit = new boolean[n + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                graph[i] = Integer.parseInt(st.nextToken());
            }
            count = 0;
            for (int i = 1; i <= n; i++)
                dfs(i);
            System.out.println(n - count);
        }
    }//

    static void dfs(int now) {
        if (visit[now])
            return;

        visit[now] = true;
        int next = graph[now];
        if (!visit[next])
            dfs(next);
        else {
            if (!check[next]) {
                count++;
                for (int i = next; i != now; i = graph[i]) {
                    count++;
                }
            }
        }
        check[now] = true;
    }//
}
