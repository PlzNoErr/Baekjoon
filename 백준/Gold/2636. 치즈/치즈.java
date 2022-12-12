import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    static int n, m;
    static Queue<int[]> Q;
    static int[][] map;
    static boolean[][] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        check = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int last_size = 0;
        int time = 0;
        Q = new LinkedList<>();
        dfs(0, 0);


        while (true) {
            if (Q.size() == 0) break;
            last_size = Q.size();
            time++;

            // 치즈 녹이기
            for (int k = 0; k < last_size; k++) {
                int[] vt = Q.poll();
                map[vt[0]][vt[1]] = 0;
                dfs(vt[0], vt[1]);
            }
        }
        System.out.println(time);
        System.out.println(last_size);

    }//

    static void dfs(int r, int c) {
        check[r][c] = true;
        for (int i = 0; i < 4; i++) {
            int R = r + dr[i];
            int C = c + dc[i];
            if (0 <= R && R < n && 0 <= C && C < m && !check[R][C]) {
                if (map[R][C] == 0) {
                    dfs(R, C);
                } else {
                    check[R][C] = true;
                    Q.add(new int[]{R, C});
                }
            }
        }
    }//

}