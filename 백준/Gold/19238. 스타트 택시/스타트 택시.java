import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n;
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    static int[][] map;
    static Client[][] info_map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int left_fuel = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        info_map = new Client[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        int taxi_r = Integer.parseInt(st.nextToken()) - 1;
        int taxi_c = Integer.parseInt(st.nextToken()) - 1;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int cr = Integer.parseInt(st.nextToken()) - 1;
            int cc = Integer.parseInt(st.nextToken()) - 1;
            int desr = Integer.parseInt(st.nextToken()) - 1;
            int desc = Integer.parseInt(st.nextToken()) - 1;
            info_map[cr][cc] = new Client(cr, cc, desr, desc);
        }

        int now_client = 0;
        while (true) {
            // 먼저 현재의 위치에서 최단거리에 있는 손님을 찾는다.
            Client client = find_client(taxi_r, taxi_c);
            // 만일 벽으로 가로막혀서 손님까지 도달이 불가능한 경우 -1 출력.
            if (client == null) {
                System.out.println(-1);
                return;
            }
            // 해당 칸을 비우고 이제 client로 연산한다.
            info_map[client.r][client.c] = null;
            // 최단손님에게 도착하기 위한 연료량을 추산.
            left_fuel -= client.used_fuel;
            if (left_fuel < 0) {
                System.out.println(-1);
                return;
            }
            taxi_r = client.r;
            taxi_c = client.c;
            // bfs로 최단거리 목적지를 계산한다.
            int[] des = find_des(taxi_r, taxi_c, client);
            if (des[0] == -1) {
                System.out.println(-1);
                return;
            }

            if (left_fuel - des[2] < 0) {
                System.out.println(-1);
                return;
            }
            left_fuel += des[2];
            taxi_r = des[0];
            taxi_c = des[1];
            now_client++;
            if (now_client == m)
                break;
        }//
        System.out.println(left_fuel);
    }//

    static Client find_client(int r, int c) {
        // 최단거리의 손님을 조건에 맞게 탐색한다.
        PriorityQueue<Client> PQ = new PriorityQueue<>(new Comparator<Client>() {
            @Override
            public int compare(Client o1, Client o2) {
                if (o1.used_fuel == o2.used_fuel) {
                    if (o1.r == o2.r)
                        return o1.c - o2.c;
                    else
                        return o1.r - o2.r;
                } else
                    return o1.used_fuel - o2.used_fuel;
            }
        });
        boolean[][] visit = new boolean[n][n];
        // bfs로 택시기사의 현재 위치에서 손님의 위치와 소모될 연료량도 같이 넣어준다.
        Queue<int[]> Q = new LinkedList<>();
        Q.add(new int[]{r, c, 0});
        visit[r][c] = true;
        while (!Q.isEmpty()) {
            int[] vt = Q.poll();
            if (info_map[vt[0]][vt[1]] != null) {
                info_map[vt[0]][vt[1]].used_fuel = vt[2];
                PQ.add(info_map[vt[0]][vt[1]]);
            }
            for (int i = 0; i < 4; i++) {
                int R = vt[0] + dr[i];
                int C = vt[1] + dc[i];
                if (0 <= R && R < n && 0 <= C && C < n && map[R][C] != 1 && !visit[R][C]) {
                    visit[R][C] = true;
                    Q.add(new int[]{R, C, vt[2] + 1});
                }
            }
        }
        return PQ.poll();
    }//

    static int[] find_des(int taxi_r, int taxi_c, Client client) {
        int[] des = new int[3];
        des[0] = -1;
        // bfs로 목적지까지 최단거리를 계산한다.
        boolean[][] visit = new boolean[n][n];
        Queue<int[]> Q = new LinkedList<>();
        Q.add(new int[]{taxi_r, taxi_c, 0});
        visit[taxi_r][taxi_c] = true;
        while (!Q.isEmpty()) {
            int[] vt = Q.poll();
            if (vt[0] == client.des_r && vt[1] == client.des_c) {
                des = vt;
                break;
            }
            for (int i = 0; i < 4; i++) {
                int R = vt[0] + dr[i];
                int C = vt[1] + dc[i];
                if (0 <= R && R < n && 0 <= C && C < n && map[R][C] != 1 && !visit[R][C]) {
                    visit[R][C] = true;
                    Q.add(new int[]{R, C, vt[2] + 1});
                }
            }
        }

        return des;
    }

    static class Client {
        int r, c, des_r, des_c;
        int used_fuel = 0;

        public Client(int r, int c, int des_r, int des_c) {
            this.r = r;
            this.c = c;
            this.des_r = des_r;
            this.des_c = des_c;
        }
    }//
}