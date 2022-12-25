import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] dr = {0, -1, 1, 0};
    static int[] dc = {1, 0, 0, -1};
    static int[][] map;
    static int h, w, n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        map = new int[h][w];
        Location[] players = new Location[n];
        for (int i = 0; i < h; i++) {
            String s = br.readLine();
            for (int j = 0; j < w; j++) {
                if (s.charAt(j) == '.') map[i][j] = 0;
                else if (s.charAt(j) == 'X') map[i][j] = 1;
                else if (s.charAt(j) == 'B') map[i][j] = 2;
                else players[s.charAt(j) - 'a'] = new Location(i, j, 0, 0);
            }
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            players[st.nextToken().charAt(0) - 'a'].dmg = Integer.parseInt(st.nextToken());
        }

        int boss_hp = Integer.parseInt(br.readLine());
        int min_time = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            players[i].time = bfs(players[i]);
            min_time = Math.min(min_time, players[i].time);
        }

        int ans = 0;
        while (boss_hp > 0) {
            ans = 0;
            for (Location player : players) {
                if (player.time <= min_time) {
                    boss_hp -= player.dmg;
                    ans++;
                }
            }
            min_time++;
        }
        System.out.println(ans);

    }//

    static int bfs(Location player) {
        Queue<Location> Q = new LinkedList<>();
        boolean[][] visit = new boolean[h][w];
        visit[player.r][player.c] = true;
        Q.add(new Location(player.r, player.c, 0, 0));
        while (!Q.isEmpty()) {
            Location loc = Q.poll();
            if (map[loc.r][loc.c] == 2) return loc.time;

            for (int i = 0; i < 4; i++) {
                int r = loc.r + dr[i];
                int c = loc.c + dc[i];
                if (0 <= r && r < h && 0 <= c && c < w && map[r][c] != 1 && !visit[r][c]) {
                    visit[r][c] = true;
                    Q.add(new Location(r, c, 0, loc.time + 1));
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