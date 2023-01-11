import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int virus = Integer.parseInt(st.nextToken());
        PriorityQueue<int[]> PQ = new PriorityQueue<>((v1, v2) -> (v1[0] - v2[0]));
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0) PQ.add(new int[]{map[i][j], i, j});
            }
        }
        st = new StringTokenizer(br.readLine());
        int time = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        Queue<int[]> Q = new LinkedList<>();
        while (!PQ.isEmpty()) {
            Q.add(PQ.poll());
        }
        while (!Q.isEmpty() && time-- > 0) {
            int size = Q.size();
            for (int k = 0; k < size; k++) {
                int[] loc = Q.poll();
                for (int i = 0; i < 4; i++) {
                    int R = loc[1] + dr[i];
                    int C = loc[2] + dc[i];
                    if (0 <= R && R < n && 0 <= C && C < n && map[R][C] == 0) {
                        map[R][C] = loc[0];
                        Q.add(new int[]{loc[0], R, C});
                    }
                }
            }
        }
        System.out.println(map[x - 1][y - 1]);
    }
}