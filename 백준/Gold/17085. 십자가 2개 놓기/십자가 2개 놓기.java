import java.util.Scanner;

public class Main {
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int N, M, answer;
    static char[][] map;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        M = in.nextInt();
        answer = 0;
        map = new char[N][M];
        for (int i = 0; i < N; i++) map[i] = in.next().toCharArray();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == '#') {
                    int step1 = getSize(i, j);
                    for (int k = 0; k <= step1; k++) {
                        makeCross(i, j, k, '*');

                        for (int r = 0; r < N; r++) {
                            for (int c = 0; c < M; c++) {
                                if (map[r][c] == '#') {
                                    int step2 = getSize(r, c);
                                    int width1 = 4 * k + 1;
                                    int width2 = 4 * step2 + 1;
                                    answer = Math.max(answer, width1 * width2);
                                }
                            }
                        }

                        makeCross(i, j, k, '#');
                    }
                }
            }
        }

        System.out.println(answer);
    }

    static int getSize(int r, int c) {
        int ret = 0;
        while (true) {
            boolean flag = true;
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i] * ret;
                int nc = c + dc[i] * ret;
                if (nr < 0 || nc < 0 || nr >= N || nc >= M || map[nr][nc] != '#') {
                    flag = false;
                    break;
                }
            }
            if (flag) ret++;
            else break;
        }
        return ret - 1;
    }//

    static void makeCross(int r, int c, int k, char ch) {
        for (int i = 0; i <= k; i++) {
            for (int j = 0; j < 4; j++) {
                int nr = r + dr[j] * i;
                int nc = c + dc[j] * i;
                map[nr][nc] = ch;
            }
        }
    }

}//
