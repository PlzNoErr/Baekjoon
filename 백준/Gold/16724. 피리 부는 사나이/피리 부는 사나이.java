import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static char[][] map;
    static boolean[][] visit;
    static boolean[][] check;
    static int count = 0;
    // 좌 우 위 아래
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        visit = new boolean[n][m];
        check = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visit[i][j]) {
                    dfs(i, j);
                }
            }
        }
        System.out.println(count);

    }//

    static void dfs(int r, int c) {
        if (visit[r][c]) return;
        if (check[r][c]){
            check[r][c] = false;
            visit[r][c] = true;
            count++;
            return;
        }
        check[r][c] = true;

        int R = r;
        int C = c;

        if (map[r][c] == 'L') C += dc[0];
        else if (map[r][c] == 'R') C += dc[1];
        else if (map[r][c] == 'U') R += dr[2];
        else R += dr[3];
        dfs(R, C);
        visit[r][c] = true;
        check[r][c] = false;
    }
}