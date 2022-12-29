import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Queue<int[]> Q_sang = new LinkedList<>();
        Queue<int[]> Q_fire = new LinkedList<>();
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        char[][] map = new char[n][m];
        boolean[][] visit = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                if (s.charAt(j) == 'F') {
                    Q_fire.add(new int[]{i, j});
                    map[i][j] = 'F';
                } else if (s.charAt(j) == 'J') {
                    Q_sang.add(new int[]{i, j, 0});
                    visit[i][j] = true;
                    map[i][j] = '.';
                } else if (s.charAt(j) == '.') {
                    map[i][j] = '.';
                } else map[i][j] = '#';
            }
        }

        int time = 0;
        here:
        while (true) {
            int temp = Q_fire.size();
            for (int i = 0; i < temp; i++) {
                int[] fire = Q_fire.poll();
                for (int j = 0; j < 4; j++) {
                    int R = fire[0] + dr[j];
                    int C = fire[1] + dc[j];
                    if (R < 0 || n <= R || C < 0 || m <= C) continue;
                    if (map[R][C] == '.') {
                        Q_fire.add(new int[]{R, C});
                        map[R][C] = 'F';
                    }
                }
            }

            temp = Q_sang.size();
            for (int i = 0; i < temp; i++) {
                int[] sang = Q_sang.poll();
                for (int j = 0; j < 4; j++) {
                    int R = sang[0] + dr[j];
                    int C = sang[1] + dc[j];
                    if (R < 0 || n <= R || C < 0 || m <= C) {
                        time = sang[2] + 1;
                        break here;
                    }
                    if (map[R][C] == '.' && !visit[R][C]) {
                        visit[R][C] = true;
                        Q_sang.add(new int[]{R, C, sang[2] + 1});
                    }
                }
            }
            if (Q_sang.isEmpty()) break;

        }
        System.out.println(time == 0 ? "IMPOSSIBLE" : time);
    }
}
