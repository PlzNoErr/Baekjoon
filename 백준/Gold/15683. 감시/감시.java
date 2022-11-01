import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int min = Integer.MAX_VALUE;
    static int n;
    static int m;
    static ArrayList<int[]> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][m];
        list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 6 && map[i][j] != 0)
                    list.add(new int[]{i, j, map[i][j]});
            }
        }
        dfs(0, map);
        System.out.println(min);
    }//

    static void dfs(int depth, int[][] map) {
        if (depth == list.size()) {
            // 0의 개수를 새고 min을 업데이트
            int count = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == 0) count++;
                }
            }
            min = Math.min(min, count);
            return;
        }
        if (list.get(depth)[2] == 1) {
            for (int i = 0; i < 4; i++) {
                int[][] copy = deep_copy(map);
                paint(list.get(depth)[0], list.get(depth)[1], i, copy);
                dfs(depth + 1, copy);
            }
        }

        if (list.get(depth)[2] == 2) {
            for (int i = 0; i < 2; i++) {
                int[][] copy = deep_copy(map);
                paint(list.get(depth)[0], list.get(depth)[1], i, copy);
                paint(list.get(depth)[0], list.get(depth)[1], i + 2, copy);
                dfs(depth + 1, copy);
            }
        }

        if (list.get(depth)[2] == 3) {
            for (int i = 0; i < 4; i++) {
                int[][] copy = deep_copy(map);
                paint(list.get(depth)[0], list.get(depth)[1], i, copy);
                paint(list.get(depth)[0], list.get(depth)[1], (i + 1) % 4, copy);
                dfs(depth + 1, copy);
            }
        }

        if (list.get(depth)[2] == 4) {
            for (int i = 0; i < 4; i++) {
                int[][] copy = deep_copy(map);
                paint(list.get(depth)[0], list.get(depth)[1], i, copy);
                paint(list.get(depth)[0], list.get(depth)[1], (i + 1) % 4, copy);
                paint(list.get(depth)[0], list.get(depth)[1], (i + 2) % 4, copy);
                dfs(depth + 1, copy);
            }
        }

        if (list.get(depth)[2] == 5) {
            int[][] copy = deep_copy(map);
            paint(list.get(depth)[0], list.get(depth)[1], 0, copy);
            paint(list.get(depth)[0], list.get(depth)[1], 1, copy);
            paint(list.get(depth)[0], list.get(depth)[1], 2, copy);
            paint(list.get(depth)[0], list.get(depth)[1], 3, copy);
            dfs(depth + 1, copy);
        }
    }//

    static int[][] deep_copy(int[][] map) {
        int[][] copy = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                copy[i][j] = map[i][j];
            }
        }
        return copy;
    }//

    static void paint(int r, int c, int direction, int[][] map) {
        int k = 1;
        while (true) {
            int R = r + dr[direction] * k;
            int C = c + dc[direction] * k;
            if (0 > R || R >= n || 0 > C || C >= m) break;
            if (map[R][C] == 6) break;
            if (map[R][C] == 0) map[R][C] = 7;
            k++;
        }
    }//
}
