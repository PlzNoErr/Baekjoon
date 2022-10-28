import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int machine_r1 = -1, machine_r2;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) {
                    if (machine_r1 == -1) machine_r1 = i;
                    else machine_r2 = i;
                }
            }
        }
        int time = 0;
        while (time < T) {
            // 확산을 위한 미세먼지의 정보를 Q에 입력한다.
            Queue<Dust> Q = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] != -1) {
                        int count = 0;
                        for (int k = 0; k < 4; k++) {
                            int R = i + dr[k];
                            int C = j + dc[k];
                            if (0 <= R && R < n && 0 <= C && C < m && map[R][C] != -1) {
                                Q.add(new Dust(R, C, map[i][j] / 5));
                                count++;
                            }
                        }
                        map[i][j] -= (map[i][j] / 5) * count;
                    }
                }
            }

            // 미세먼지 확산
            while (!Q.isEmpty()) {
                Dust dust = Q.poll();
                map[dust.r][dust.c] += dust.dust;
            }

            // 공기 청정기 순환
            for (int i = machine_r1; i > 0; i--) {
                map[i][0] = map[i - 1][0];
            }
            for (int j = 0; j < m - 1; j++) {
                map[0][j] = map[0][j + 1];
            }
            for (int i = 0; i < machine_r1; i++) {
                map[i][m - 1] = map[i + 1][m - 1];
            }
            for (int j = m - 1; j > 1; j--) {
                map[machine_r1][j] = map[machine_r1][j - 1];
            }
            map[machine_r1][1] = 0;
            map[machine_r1][0] = -1;
            for (int i = machine_r2; i < n - 1; i++) {
                map[i][0] = map[i + 1][0];
            }
            for (int j = 0; j < m - 1; j++) {
                map[n - 1][j] = map[n - 1][j + 1];
            }
            for (int i = n - 1; i > machine_r2; i--) {
                map[i][m - 1] = map[i - 1][m - 1];
            }
            for (int j = m - 1; j > 1; j--) {
                map[machine_r2][j] = map[machine_r2][j - 1];
            }
            map[machine_r2][1] = 0;
            map[machine_r2][0] = -1;

            time++;
        }//

        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != -1) sum += map[i][j];
            }
        }
        System.out.println(sum);

    }//

    static class Dust {
        int r, c, dust;

        public Dust(int r, int c, int dust) {
            this.r = r;
            this.c = c;
            this.dust = dust;
        }
    }
}