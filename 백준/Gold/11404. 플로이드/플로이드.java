import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int v = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];

        int INF = Integer.MAX_VALUE / 10;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = INF;

                if (i == j) {
                    map[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < v; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            map[from - 1][to - 1] = Math.min(map[from - 1][to - 1], cost);
        }

        // 플로이드 와샬
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != INF)
                    sb.append(map[i][j] + " ");
                else
                    sb.append(0 + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}