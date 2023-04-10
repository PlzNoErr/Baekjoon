import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dr = {1, 0, -1, -1, -1, 0, 1, 1};
    static int[] dc = {-1, -1, -1, 0, 1, 1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][n];
        boolean[][] check = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Queue<int[]> Q = new LinkedList<>();
        Q.add(new int[]{n - 1, 0});
        Q.add(new int[]{n - 1, 1});
        Q.add(new int[]{n - 2, 0});
        Q.add(new int[]{n - 2, 1});
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int dir = Integer.parseInt(st.nextToken()) % 8;
            int k = Integer.parseInt(st.nextToken());
            int size = Q.size();

            // 구름을 이동 + 1씩 증가
            for (int i = 0; i < size; i++) {
                int[] vt = Q.poll();
                int R = (vt[0] + dr[dir] * k) % n >= 0 ? (vt[0] + dr[dir] * k) % n : n + (vt[0] + dr[dir] * k) % n;
                int C = (vt[1] + dc[dir] * k) % n >= 0 ? (vt[1] + dc[dir] * k) % n : n + (vt[1] + dc[dir] * k) % n;
                Q.add(new int[]{R, C});
                map[R][C]++;
                check[R][C] = true;
            }

            // 물복사 버그
            while (!Q.isEmpty()) {
                int[] vt = Q.poll();
                int water = 0;
                for (int j = 0; j < 8; j += 2) {
                    int R = vt[0] + dr[j];
                    int C = vt[1] + dc[j];
                    if (R < 0 || n <= R || C < 0 || n <= C) continue;
                    if (map[R][C] != 0) water++;
                }
                map[vt[0]][vt[1]] += water;
            }

            // 전체를 탐색해서 구름을 생성
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!check[i][j] && map[i][j] >= 2) {
                        Q.add(new int[]{i, j});
                        map[i][j] -= 2;
                    }
                    if (check[i][j]) check[i][j] = false;
                }
            }
        }//
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans += map[i][j];
            }
        }
        System.out.println(ans);
    }//
}
