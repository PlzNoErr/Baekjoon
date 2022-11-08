import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    // 위 아래 왼쪽 오른쪽
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int N, M, k;
    static Shark[][] shark_map;
    static Smell[][] smell_map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        shark_map = new Shark[N][N];
        smell_map = new Smell[N][N];
        int[][] temp = new int[M + 1][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num != 0) {
                    temp[num][0] = i;
                    temp[num][1] = j;
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        int level = 1;
        while (st.hasMoreTokens()) {
            int num = Integer.parseInt(st.nextToken());
            shark_map[temp[level][0]][temp[level][1]] = new Shark(temp[level][0], temp[level][1], level, num - 1);
            level++;
        }
        for (int i = 1; i <= M; i++) {
            int dir = 0;
            for (int p = 0; p < 4; p++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 4; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    shark_map[temp[i][0]][temp[i][1]].direction[dir][j] = num - 1;
                }
                dir++;
            }
        }

        //시뮬레이션을 시작하자.
        int time = 1;
        while (time <= 1000) {
            // 냄새를 뿌린다
            make_smell();

            // 상어들이 이동한다.
            shark_move();

            // 상어 1만 남았는지 검사한다. 1만 남았으면 시뮬종료
            if (check_shark()) {
                System.out.println(time);
                return;
            }

            // 냄새가 1만큼 줄어든다
            disapper_smell();

            time++;
        }
        System.out.println(-1);

    }//

    static void make_smell() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (shark_map[i][j] != null) {
                    smell_map[i][j] = new Smell(shark_map[i][j].level, k);
                }
            }
        }
    }//

    static void shark_move() {
        Queue<Shark> Q = new LinkedList<>();
        // 상어 맵에 있는 모든 상어들을 Q로 옮겨 담는다.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (shark_map[i][j] != null) {
                    Q.add(shark_map[i][j]);
                    shark_map[i][j] = null;
                }
            }
        }
        here:
        while (!Q.isEmpty()) {
            Shark shark = Q.poll();

            // 먼저 빈칸이 있는지 우선순위로 탐사한다.
            for (int i = 0; i < 4; i++) {
                int R = shark.r + dr[shark.direction[shark.now_head][i]];
                int C = shark.c + dc[shark.direction[shark.now_head][i]];
                if (0 <= R && R < N && 0 <= C && C < N && smell_map[R][C] == null) {
                    if (shark_map[R][C] == null || shark_map[R][C].level > shark.level) {
                        shark.r = R;
                        shark.c = C;
                        shark.now_head = shark.direction[shark.now_head][i];
                        shark_map[R][C] = shark;
                        continue here;
                    } else {
                        continue here;
                    }
                }
            }

            // 빈칸이 없을경우 자기냄새가 있는지 우선순위로 탐사한다.
            // 우선순위 대로 걸렸는데 선객이 없거나 있더라도 내가 짬이 더 높으면 밀어낸다.
            // 선객이 있는데 나보다 짬이 더 높다? 그럼 그냥 연산을 끝내고 해당 상어정보는 사라진다.
            for (int i = 0; i < 4; i++) {
                int R = shark.r + dr[shark.direction[shark.now_head][i]];
                int C = shark.c + dc[shark.direction[shark.now_head][i]];
                if (0 <= R && R < N && 0 <= C && C < N && smell_map[R][C].level == shark.level) {
                    if (shark_map[R][C] == null || shark_map[R][C].level > shark.level) {
                        shark.r = R;
                        shark.c = C;
                        shark.now_head = shark.direction[shark.now_head][i];
                        shark_map[R][C] = shark;
                        continue here;
                    } else {
                        continue here;
                    }
                }
            }
        }//
    }//

    static boolean check_shark() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (shark_map[i][j] != null && shark_map[i][j].level != 1) return false;
            }
        }
        return true;
    }//

    static void disapper_smell() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (smell_map[i][j] != null) {
                    smell_map[i][j].left_time--;
                    if (smell_map[i][j].left_time == 0) smell_map[i][j] = null;
                }
            }
        }
    }//

    static class Shark {
        int r, c, level, now_head;
        int[][] direction = new int[4][4];

        public Shark(int r, int c, int level, int now_head) {
            this.r = r;
            this.c = c;
            this.level = level;
            this.now_head = now_head;
        }
    }

    static class Smell {
        int level, left_time;

        public Smell(int level, int left_time) {
            this.level = level;
            this.left_time = left_time;
        }
    }

}