import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    static int min = Integer.MAX_VALUE;
    static List<int[]> possible_point;
    static int n;
    static int m;
    static int[][] map;
    static int[][] copy_map;
    static int[][] choosen_point;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        possible_point = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        choosen_point = new int[m][2];
        for (int i = 0; i < map.length; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < map.length; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) possible_point.add(new int[]{i, j});
            }
        }

        Combination(0, 0);

        if (min == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(min);
    }// main

    static void Combination(int depth, int idx) {
        if (depth == m) {
            // 깊은복사
            copy_map = new int[map.length][map.length];
            for (int i = 0; i < copy_map.length; i++) {
                for (int j = 0; j < copy_map.length; j++) {
                    copy_map[i][j] = map[i][j];
                }
            }
            // 시뮬레이션 시행
            simulation(copy_map);
            return;
        }

        for (int i = idx; i < possible_point.size(); i++) {
            choosen_point[depth][0] = possible_point.get(i)[0];
            choosen_point[depth][1] = possible_point.get(i)[1];
            Combination(depth + 1, i + 1);
        }
    }// Combination

    static void simulation(int[][] map) {
        Queue<int[]> Q = new LinkedList<>();
        for (int i = 0; i < choosen_point.length; i++) {
            Q.add(new int[]{choosen_point[i][0], choosen_point[i][1]});
            map[choosen_point[i][0]][choosen_point[i][1]] = 3;
        }
        while (!Q.isEmpty()) {
            int[] vt = Q.poll();
            for (int i = 0; i < 4; i++) {
                int R = vt[0] + dr[i];
                int C = vt[1] + dc[i];
                if (0 <= R && R < n && 0 <= C && C < n && (map[R][C] == 0 || map[R][C] == 2)) {
                    map[R][C] = map[vt[0]][vt[1]] + 1;
                    Q.add(new int[]{R, C});
                }
            }
        }

        int max = -1;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] == 0 || map[i][j] == 2) return;
                max = Math.max(max, map[i][j]);
            }
        }
        max -= 3;
        min = Math.min(min, max);
    }//
}
