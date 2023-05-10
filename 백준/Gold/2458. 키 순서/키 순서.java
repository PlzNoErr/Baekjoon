import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int[][] map = new int[v + 1][v + 1];
        long[][] dist = new long[v + 1][v + 1];
        while (e-- > 0) {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
        }

        for (int i = 1; i <= v; i++) {
            for (int j = 1; j <= v; j++) {
                if (i == j) continue;
                else if (map[i][j] == 1) dist[i][j] = 1;
                else dist[i][j] = Integer.MAX_VALUE / 10;
            }
        }


        for (int k = 1; k <= v; k++) {
            for (int i = 1; i <= v; i++) {
                for (int j = 1; j <= v; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        int ans = 0;
        here:
        for (int i = 1; i <= v; i++) {
            for (int j = 1; j <= v; j++) {
                if (dist[i][j] == Integer.MAX_VALUE / 10 && dist[j][i] == Integer.MAX_VALUE / 10) continue here;
            }
            ans++;
        }

        System.out.println(ans);
    }
}
