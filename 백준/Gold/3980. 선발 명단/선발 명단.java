import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static boolean[] check;
    static int[] pos;
    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        while (tc-- > 0) {
            map = new int[11][11];
            check = new boolean[11];
            pos = new int[11];
            max = 0;
            for (int i = 0; i < 11; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 11; j++) map[i][j] = Integer.parseInt(st.nextToken());
            }
            dfs(0);
            sb.append(max + "\n");
        }
        System.out.println(sb.toString());
    }

    static void dfs(int depth) {
        if (depth == 11) {
            int value = 0;
            for (int i = 0; i < 11; i++) value += map[pos[i]][i];
            max = Math.max(max, value);
            return;
        }
        for (int i = 0; i < 11; i++) {
            if (!check[i] && map[i][depth] != 0) {
                check[i] = true;
                pos[depth] = i;
                dfs(depth + 1);
                check[i] = false;
            }
        }
    }
}
