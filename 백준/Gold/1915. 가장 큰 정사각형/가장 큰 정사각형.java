import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new java.io.BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n + 1][m + 1];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        int answer = 0;

        for (int y = n - 1; y >= 0; y--) {
            for (int x = m - 1; x >= 0; x--) {
                if (map[y][x] == 1) {
                    int min = Math.min(map[y + 0][x + 1], map[y + 1][x + 0]);
                    map[y][x] += Math.min(min, map[y + 1][x + 1]);
                }
                answer = Math.max(answer, map[y][x]);
            }
        }

        System.out.println(answer * answer);


    }//
}