import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] map;
    static int[] order;
    static List<Integer>[] list;
    static PriorityQueue<Point> PQ;
    static int[] dr = {1, -1, 0, 0}, dc = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        map = new int[n + 1][n + 1];
        order = new int[n * n + 1];

        list = new ArrayList[n * n + 1];

        for (int i = 1; i <= n * n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 1; i <= n * n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            order[i] = Integer.parseInt(st.nextToken());
            for (int j = 0; j < 4; j++) {
                list[order[i]].add(Integer.parseInt(st.nextToken()));
            }
        }

        PQ = new PriorityQueue<>((p1, p2) -> {
            if (p1.likeCount != p2.likeCount) return p2.likeCount - p1.likeCount;
            if (p1.nearCount != p2.nearCount) return p2.nearCount - p1.nearCount;
            if (p1.r != p2.r) return p1.r - p2.r;
            else return p1.c - p2.c;
        });

        for (int i = 1; i <= n * n; i++) {
            solve(order[i]);
            PQ.clear();
        }

        int result = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                result += getPrice(i, j);
            }
        }
        System.out.println(result);

    }

    private static int getPrice(int r, int c) {
        int num = map[r][c];

        int R, C, likeCount = 0;
        for (Integer now : list[num])
            for (int i = 0; i < 4; i++) {
                R = r + dr[i];
                C = c + dc[i];
                if (R < 1 || R > n || C < 1 || C > n) continue;
                if (map[R][C] == now) likeCount++;
            }

        switch (likeCount) {
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 10;
            case 3:
                return 100;
            default:
                return 1000;
        }
    }

    private static void solve(int num) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                int nearCount = 0;
                int likeCount = 0;
                if (map[i][j] != 0) continue;

                for (int k = 0; k < 4; k++) {
                    int R = i + dr[k];
                    int C = j + dc[k];
                    if (R < 1 || R > n || C < 1 || C > n) continue;
                    for (Integer now : list[num]) if (now == map[R][C]) likeCount++;
                    if (map[R][C] == 0) nearCount++;
                }
                PQ.add(new Point(i, j, nearCount, likeCount));
            }
        }
        Point poll = PQ.poll();
        map[poll.r][poll.c] = num;
    }

    static class Point {
        int r;
        int c;
        int nearCount;
        int likeCount;

        public Point(int r, int c, int nearCount, int likeCount) {
            this.r = r;
            this.c = c;
            this.nearCount = nearCount;
            this.likeCount = likeCount;
        }
    }
}
