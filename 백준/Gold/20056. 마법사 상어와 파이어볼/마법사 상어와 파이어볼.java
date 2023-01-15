import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
    static ArrayList<Fireball>[][] map;
    static Queue<Fireball> Q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int ballNum = Integer.parseInt(st.nextToken());
        int time = Integer.parseInt(st.nextToken());
        map = new ArrayList[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = new ArrayList<>();
            }
        }
        Q = new LinkedList<>();
        for (int i = 0; i < ballNum; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            Q.add(new Fireball(r, c, weight, speed, dir));
        }

        while (time-- > 0) {
            // 파이어 볼을 이동
            int size = Q.size();
            for (int i = 0; i < size; i++) {
                Fireball ball = Q.poll();
                int r = (n + ball.r + dr[ball.dir] * (ball.speed % n)) % n;
                int c = (n + ball.c + dc[ball.dir] * (ball.speed % n)) % n;
                ball.r = r;
                ball.c = c;
                map[r][c].add(ball);
            }

            // 파이어 볼을 분열
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j].size() == 0) continue;
                    if (map[i][j].size() == 1) {
                        Q.add(map[i][j].get(0));
                        map[i][j].clear();
                    } else {
                        int sumWeight = map[i][j].get(0).weight;
                        int sumSpeed = map[i][j].get(0).speed;
                        int beforeDir = map[i][j].get(0).dir % 2;
                        boolean check = true;
                        for (int k = 1; k < map[i][j].size(); k++) {
                            sumWeight += map[i][j].get(k).weight;
                            sumSpeed += map[i][j].get(k).speed;
                            if (check && beforeDir != map[i][j].get(k).dir % 2) check = false;
                        }
                        if (sumWeight / 5 == 0) {
                            map[i][j].clear();
                            continue;
                        }
                        if (check) {
                            Q.add(new Fireball(i, j, sumWeight / 5, sumSpeed / map[i][j].size(), 0));
                            Q.add(new Fireball(i, j, sumWeight / 5, sumSpeed / map[i][j].size(), 2));
                            Q.add(new Fireball(i, j, sumWeight / 5, sumSpeed / map[i][j].size(), 4));
                            Q.add(new Fireball(i, j, sumWeight / 5, sumSpeed / map[i][j].size(), 6));
                            map[i][j].clear();
                        } else {
                            Q.add(new Fireball(i, j, sumWeight / 5, sumSpeed / map[i][j].size(), 1));
                            Q.add(new Fireball(i, j, sumWeight / 5, sumSpeed / map[i][j].size(), 3));
                            Q.add(new Fireball(i, j, sumWeight / 5, sumSpeed / map[i][j].size(), 5));
                            Q.add(new Fireball(i, j, sumWeight / 5, sumSpeed / map[i][j].size(), 7));
                            map[i][j].clear();
                        }
                    }
                }
            }
        }//
        int total = 0;
        while (!Q.isEmpty()) {
            Fireball ball = Q.poll();
            total += ball.weight;
        }
        System.out.println(total);
    }

    static class Fireball {
        int r, c, weight, speed, dir;

        public Fireball(int r, int c, int weight, int speed, int dir) {
            this.r = r;
            this.c = c;
            this.weight = weight;
            this.speed = speed;
            this.dir = dir;
        }
    }//
}