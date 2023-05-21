import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m, ans;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        ans = 0;
        map = new int[n + 1][m + 1];
        dfs(0);
        System.out.println(ans);
    }

    static void dfs(int cnt) {
        if (cnt == n * m) {
            ans++;
            return;
        }

        int r = cnt / m + 1;
        int c = cnt % m + 1;

        dfs(cnt + 1);
        if (map[r - 1][c] != 1 || map[r][c - 1] != 1 || map[r - 1][c - 1] != 1) {
            map[r][c] = 1;
            dfs(cnt + 1);
            map[r][c] = 0;
        }//
    }
}
