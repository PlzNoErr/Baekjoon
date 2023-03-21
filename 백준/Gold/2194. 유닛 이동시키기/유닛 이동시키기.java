import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int A = Integer.parseInt(st.nextToken()) - 1;
        int B = Integer.parseInt(st.nextToken()) - 1;
        int K = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        boolean[][] visit = new boolean[N][M];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = -1;
        }

        st = new StringTokenizer(br.readLine());
        Queue<int[]> Q = new LinkedList<>();
        int a = Integer.parseInt(st.nextToken()) - 1;
        int b = Integer.parseInt(st.nextToken()) - 1;
        visit[a][b] = true;
        Q.add(new int[]{a, b});

        st = new StringTokenizer(br.readLine());
        int[] end = {Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1};

        while (!Q.isEmpty()) {
            int[] vt = Q.poll();
            if (vt[0] == end[0] && vt[1] == end[1]) {
                System.out.println(map[end[0]][end[1]]);
                return;
            }
            for (int i = 0; i < 4; i++) {
                int R = vt[0] + dr[i];
                int C = vt[1] + dc[i];
                if (R < 0 || N <= R || C < 0 || M <= C || N <= R + A || M <= C + B) continue;
                if (!visit[R][C] && check(R, C, A, B, map)) {
                    visit[R][C] = true;
                    map[R][C] = map[vt[0]][vt[1]] + 1;
                    Q.add(new int[]{R, C});
                }
            }

        }//
        System.out.println(-1);
    }

    static boolean check(int r, int c, int a, int b, int[][] map) {
        for (int i = 0; i <= a; i++) {
            for (int j = 0; j <= b; j++) {
                if (map[r + i][c + j] == -1) return false;
            }
        }
        return true;
    }
}
