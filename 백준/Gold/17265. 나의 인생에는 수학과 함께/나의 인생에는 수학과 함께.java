import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] dr = {1, 0};
    static int[] dc = {0, 1};
    static String[] print;
    static String[][] map;
    static int n, max, min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        map = new String[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = st.nextToken();
            }
        }
        print = new String[2 * n - 1];
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;

        dfs(0, 0, 0);

        System.out.print(max + " " + min);
    }//

    static void dfs(int r, int c, int depth) {
        print[depth] = map[r][c];

        if (r == n - 1 && c == n - 1) {
            int num = Integer.parseInt(print[0]);
            for (int i = 1; i < 2 * n - 1; i += 2) {
                if (print[i].equals("+")) num += Integer.parseInt(print[i + 1]);
                if (print[i].equals("*")) num *= Integer.parseInt(print[i + 1]);
                if (print[i].equals("-")) num -= Integer.parseInt(print[i + 1]);
            }
            max = Math.max(max, num);
            min = Math.min(min, num);
            return;
        }

        for (int i = 0; i < 2; i++) {
            int R = r + dr[i];
            int C = c + dc[i];
            if (R < 0 || R >= n || C < 0 || C >= n) continue;
            dfs(R, C, depth + 1);
        }
    }
}