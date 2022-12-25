import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] dr = {0, -1, 1, 0};
    static int[] dc = {1, 0, 0, -1};
    static int[][] map, info;
    static int h, w, n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        map = new int[h][w];
        info = new int[h][w];
        int r = 0, c = 0;
        Location[] players = new Location[n];
        for (int i = 0; i < h; i++) {
            String s = br.readLine();
            for (int j = 0; j < w; j++) {
                if (s.charAt(j) == '.') map[i][j] = 0;
                else if (s.charAt(j) == 'X') map[i][j] = 1;
                else if (s.charAt(j) == 'B') {
                    map[i][j] = 2;
                    r = i;
                    c = j;
                } else players[s.charAt(j) - 'a'] = new Location(i, j, 0, 0);
            }
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            players[st.nextToken().charAt(0) - 'a'].dmg = Integer.parseInt(st.nextToken());
        }

        int boss_hp = Integer.parseInt(br.readLine());
        int min_time = Integer.MAX_VALUE;
        bfs(new Location(r, c, 0, 0));
        for (int i = 0; i < n; i++) {
            players[i].time = info[players[i].r][players[i].c];
            if (players[i].time != 0)
                min_time = Math.min(min_time, players[i].time);
        }

        int ans = 0;
        while (boss_hp > 0) {
            ans = 0;
            for (Location player : players) {
                if (0 < player.time && player.time <= min_time) {
                    boss_hp -= player.dmg;
                    ans++;
                }
            }
            min_time++;
        }
        System.out.println(ans);

    }//

    static int bfs(Location boss) {
        Queue<Location> Q = new LinkedList<>();
        boolean[][] visit = new boolean[h][w];
        visit[boss.r][boss.c] = true;
        Q.add(new Location(boss.r, boss.c, 0, 0));
        while (!Q.isEmpty()) {
            Location loc = Q.poll();
            for (int i = 0; i < 4; i++) {
                int r = loc.r + dr[i];
                int c = loc.c + dc[i];
                if (0 <= r && r < h && 0 <= c && c < w && map[r][c] != 1 && !visit[r][c]) {
                    visit[r][c] = true;
                    Q.add(new Location(r, c, 0, loc.time + 1));
                    info[r][c] = loc.time + 1;
                }
            }
        }
        return -1;
    }//

    static class Location {
        int r, c, dmg, time;

        public Location(int r, int c, int dmg, int time) {
            this.r = r;
            this.c = c;
            this.dmg = dmg;
            this.time = time;
        }
    }//
}