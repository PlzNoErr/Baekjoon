import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int[][] dist;
    static int[][] cost;
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    static int n;
    final static int INF = Integer.MAX_VALUE / 10;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int tc = 1;
        while (true) {
            n = Integer.parseInt(br.readLine());
            if (n == 0) break;
            cost = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    cost[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            dijkstra();
            sb.append("Problem " + tc + ": " + dist[n - 1][n - 1] + "\n");
            tc++;
        }
        System.out.println(sb.toString());

    }//

    static void dijkstra() {
        // dist배열을 초기화 & 시작지점 0으로 초기화
        dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
        }

        dist[0][0] = cost[0][0];
        PriorityQueue<int[]> PQ = new PriorityQueue<>((v1, v2) -> v1[2] - v2[2]);
        PQ.add(new int[]{0, 0, cost[0][0]});
        while (!PQ.isEmpty()) {
            int[] node = PQ.poll();
            if (dist[node[0]][node[1]] < node[2]) continue;

            for (int i = 0; i < 4; i++) {
                int r = node[0] + dr[i];
                int c = node[1] + dc[i];
                if (r < 0 || n <= r || c < 0 || n <= c) continue;
                if (dist[r][c] > node[2] + cost[r][c]) {
                    dist[r][c] = node[2] + cost[r][c];
                    PQ.add(new int[]{r, c, node[2] + cost[r][c]});
                }
            }
        }
    }//

}