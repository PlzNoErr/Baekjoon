import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    static Queue<int[]> Q;
    static Queue<int[]> resetQ;
    static char[][] map;
    static boolean[][] visit;
    static int max, n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        max = 0;
        map = new char[n][m];
        visit = new boolean[n][m];
        Q = new LinkedList<>();
        resetQ = new LinkedList<>();
        for (int i = 0; i < n; i++)
            map[i] = br.readLine().toCharArray();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 'L') bfs(i, j);
            }
        }
        System.out.println(max);
    }

    static void bfs(int a, int b) {
        Q.add(new int[]{a, b, 0});
        visit[a][b] = true;
        while (!Q.isEmpty()) {
            int[] loc = Q.poll();
            max = Math.max(max, loc[2]);
            for (int i = 0; i < 4; i++) {
                int R = loc[0] + dr[i];
                int C = loc[1] + dc[i];
                if (0 <= R && R < n && 0 <= C && C < m && !visit[R][C] && map[R][C] == 'L') {
                    visit[R][C] = true;
                    Q.add(new int[]{R, C, loc[2] + 1});
                }
            }
            resetQ.add(loc);
        }
        visit[a][b] = false;
        while (!resetQ.isEmpty()) {
            int[] loc = resetQ.poll();
            visit[loc[0]][loc[1]] = false;
        }
    }//
}
