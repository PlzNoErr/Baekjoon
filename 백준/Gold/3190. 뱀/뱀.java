import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] dr = {0, -1, 1, 0};
    static int[] dc = {1, 0, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int num_apple = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];
        for (int i = 0; i < num_apple; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            map[r][c] = -1;
        }

        int head_r = 0;
        int head_c = 0;
        int tail_r = 0;
        int tail_c = 0;
        map[0][0] = 1;
        int turn = 0;
        int direction = 0;
        int Dr = 0;
        int Dc = 1;

        int num_command = Integer.parseInt(br.readLine());
        here:
        for (int i = 0; i < num_command; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int next_directionR = 0;
            int next_directionC = 0;
            String s = st.nextToken();
            if (s.equals("D")) {
                if (Dr == 0) {
                    if (Dc == -1)
                        next_directionR = -1;
                    else
                        next_directionR = 1;
                    next_directionC = 0;
                }
                if (Dc == 0) {
                    if (Dr == -1)
                        next_directionC = 1;
                    else
                        next_directionC = -1;
                    next_directionR = 0;
                }
            }
            if (s.equals("L")) {
                if (Dr == 0) {
                    if (Dc == -1)
                        next_directionR = 1;
                    else
                        next_directionR = -1;
                    next_directionC = 0;
                }
                if (Dc == 0) {
                    if (Dr == -1)
                        next_directionC = -1;
                    else
                        next_directionC = 1;
                    next_directionR = 0;
                }
            }
            while (time > turn) {
                turn++;
                int R = head_r + Dr;
                int C = head_c + Dc;
                // 뱀 == 1
                if (0 > R || R >= n || 0 > C || C >= n || (map[R][C] != 0 && map[R][C] != -1)) {
                    break here;
                }
                if (map[R][C] == 0) {
                    map[R][C] = map[head_r][head_c] + 1;
                    head_r = R;
                    head_c = C;
                    for (int j = 0; j < 4; j++) {
                        int R1 = tail_r + dr[j];
                        int C1 = tail_c + dc[j];
                        if (0 <= R1 && R1 < n && 0 <= C1 && C1 < n && map[R1][C1] == map[tail_r][tail_c] + 1) {
                            map[tail_r][tail_c] = 0;
                            tail_r = R1;
                            tail_c = C1;
                            break;
                        }
                    }
                }
                // 사과 == 2
                if (map[R][C] == -1) {
                    map[R][C] = map[head_r][head_c] + 1;
                    head_r = R;
                    head_c = C;
                }
            }//
            Dr = next_directionR;
            Dc = next_directionC;
            if (i == num_command - 1) {
                while (true) {
                    turn++;
                    int R = head_r + Dr;
                    int C = head_c + Dc;
                    // 뱀 == 1
                    if (0 > R || R >= n || 0 > C || C >= n || (map[R][C] != 0 && map[R][C] != -1)) {
                        break here;
                    }
                    if (map[R][C] == 0) {
                        map[R][C] = map[head_r][head_c] + 1;
                        head_r = R;
                        head_c = C;
                        for (int j = 0; j < 4; j++) {
                            int R1 = tail_r + dr[j];
                            int C1 = tail_c + dc[j];
                            if (0 <= R1 && R1 < n && 0 <= C1 && C1 < n && map[R1][C1] == map[tail_r][tail_c] + 1) {
                                map[tail_r][tail_c] = 0;
                                tail_r = R1;
                                tail_c = C1;
                                break;
                            }
                        }
                    }
                    // 사과 == 2
                    if (map[R][C] == -1) {
                        map[R][C] = map[head_r][head_c] + 1;
                        head_r = R;
                        head_c = C;
                    }
                }
            }
        }
        System.out.println(turn);
    }//
}