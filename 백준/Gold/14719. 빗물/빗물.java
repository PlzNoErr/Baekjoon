import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int num = Integer.parseInt(st.nextToken());
            for (int j = n - 1; j >= n - num; j--) {
                map[j][i] = 1;
            }
        }
        int ans = 0;
        int start = -1;
        for (int i = 0; i < n; i++) {
            start = -1;
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1 && start == -1) {
                    start = j;
                    continue;
                }
                if (map[i][j] == 1) {
                    for (int k = start + 1; k < j; k++) {
                        ans++;
                    }
                    start = j;
                }
            }
        }
        System.out.println(ans);

    }//
}