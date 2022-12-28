import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] dr = {-1, 0, 1};
    static int[] dc = {1, 1, 1};
    static char[][] map;
    static boolean[][] visit;
    static int ans = 0;
    static boolean check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        visit = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            map[i] = br.readLine().toCharArray();
        }
        for (int i = 0; i < r; i++) {
            check = false;
            dfs(i, 0);
        }
        System.out.println(ans);
    }

    static void dfs(int r, int c) {
        visit[r][c] = true;
        if (c == map[0].length - 1) {
            check = true;
            ans++;
            return;
        }

        for (int i = 0; i < 3; i++) {
            int R = r + dr[i];
            int C = c + dc[i];
            if (0 <= R && R < map.length && 0 <= C && C < map[0].length && map[R][C] == '.' && !visit[R][C]) {
                dfs(R, C);
                if (check) return;
            }

        }
    }//
}
