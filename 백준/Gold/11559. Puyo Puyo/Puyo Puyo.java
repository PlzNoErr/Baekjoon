import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    static boolean[][] check;
    static char[][] map;
    static Queue<Info> Q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[12][6];
        for (int i = 0; i < 12; i++) {
            String s = br.readLine();
            for (int j = 0; j < 6; j++) map[i][j] = s.charAt(j);
        }

        int time = 0;

        // 시뮬레이션 시작
        while (true) {
            check = new boolean[12][6];
            Q = new LinkedList<>();
            // map을 순회하면서 dfs 실행 + dfs 실행 중 4개가 넘어가면 Q에 담는다.
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (map[i][j] != '.' && !check[i][j]) {
                        check[i][j] = true;
                        dfs(i, j, 1, map[i][j]);
                    }
                }
            }

            // Q.size가 0이라면 순회 종료 + 시간추가
            if (Q.size() == 0) break;
            time++;

            // Q가 빌때까지 다시 dfs를 돌면서 자기에 해당되는 친구는 모두 지운다.
            while (!Q.isEmpty()) {
                Info info = Q.poll();
                dfs2(info.r, info.c, info.color);
            }

            // 폭파가 한차례 끝나고 빈자리를 끌어당겨서 채운다.
            for (int j = 0; j < 6; j++) {
                Queue<Character> puyo = new LinkedList<>();
                for (int i = 11; i >= 0; i--) {
                    if (map[i][j] != '.') puyo.add(map[i][j]);
                    map[i][j] = '.';
                }
                for (int i = 11; i >= 0 && !puyo.isEmpty(); i--)
                    map[i][j] = puyo.poll();
            }
        }//
        System.out.println(time);
    }

    static void dfs(int r, int c, int count, char color) {
        if (count >= 4) Q.add(new Info(r, c, map[r][c]));
        int num = 0;
        for (int i = 0; i < 4; i++) {
            int R = r + dr[i];
            int C = c + dc[i];
            if (R < 0 || 12 <= R || C < 0 || 6 <= C || check[R][C] || map[R][C] == '.' || map[R][C] != color) continue;
            num++;
        }

        for (int i = 0; i < 4; i++) {
            int R = r + dr[i];
            int C = c + dc[i];
            if (R < 0 || 12 <= R || C < 0 || 6 <= C || check[R][C] || map[R][C] == '.' || map[R][C] != color) continue;
            check[R][C] = true;
            dfs(R, C, count + num, color);
        }
    }

    static void dfs2(int r, int c, char color) {
        for (int i = 0; i < 4; i++) {
            int R = r + dr[i];
            int C = c + dc[i];
            if (R < 0 || 12 <= R || C < 0 || 6 <= C || map[R][C] == '.') continue;
            if (map[R][C] == color) {
                map[R][C] = '.';
                dfs2(R, C, color);
            }
        }
    }

    static class Info {
        int r, c;
        char color;

        public Info(int r, int c, char color) {
            this.r = r;
            this.c = c;
            this.color = color;
        }
    }
}