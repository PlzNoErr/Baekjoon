import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static boolean[][] check;
    static int min, n, total;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        check = new boolean[n][n];
        total = 0;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                total += map[i][j];
            }
        }

        min = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    for (int l = 0; l < n; l++) {
                        Area top = new Area(i, j);
                        Area left = new Area(i + 1 + l, j - 1 - l);
                        Area right = new Area(i + 1 + k, j + 1 + k);
                        Area bottom = new Area(i + 2 + l + k, j - l + k);
                        if (left.c < 0 || n <= right.c || n <= bottom.r) continue;
                        Simulation(top, left, right, bottom);
                    }
                }
            }
        }//

        System.out.println(min);
    }//

    static void Simulation(Area top, Area left, Area right, Area bottom) {
        int big = Integer.MIN_VALUE;
        int small = Integer.MAX_VALUE;
        int[] area = new int[6];

        for (int i = 0; top.c - i >= left.c; i++) {
            check[top.r + i][top.c - i] = true;
        }
        for (int i = 0; top.c + i <= right.c; i++) {
            check[top.r + i][top.c + i] = true;
        }
        for (int i = 0; left.c + i <= bottom.c; i++) {
            check[left.r + i][left.c + i] = true;
        }
        for (int i = 0; right.c - i >= bottom.c; i++) {
            check[right.r + i][right.c - i] = true;
        }


        for (int i = 0; i < left.r; i++) {
            for (int j = 0; j <= top.c; j++) {
                if (check[i][j]) break;
                area[1] += map[i][j];
            }
        }

        for (int i = 0; i <= right.r; i++) {
            for (int j = n - 1; j > top.c; j--) {
                if (check[i][j]) break;
                area[2] += map[i][j];
            }
        }

        for (int i = n - 1; i >= left.r; i--) {
            for (int j = 0; j < bottom.c; j++) {
                if (check[i][j]) break;
                area[3] += map[i][j];
            }
        }

        for (int i = right.r + 1; i < n; i++) {
            for (int j = n - 1; j >= bottom.c; j--) {
                if (check[i][j]) break;
                area[4] += map[i][j];
            }
        }
        area[5] = total - area[1] - area[2] - area[3] - area[4];

        for (int i = 1; i <= 5; i++) {
            big = Math.max(big, area[i]);
            small = Math.min(small, area[i]);
        }

        min = Math.min(min, big - small);

        for (int i = 0; top.c - i >= left.c; i++) {
            check[top.r + i][top.c - i] = false;
        }
        for (int i = 0; top.c + i <= right.c; i++) {
            check[top.r + i][top.c + i] = false;
        }
        for (int i = 0; left.c + i <= bottom.c; i++) {
            check[left.r + i][left.c + i] = false;
        }
        for (int i = 0; right.c - i >= bottom.c; i++) {
            check[right.r + i][right.c - i] = false;
        }

        return;
    }//

    static class Area {
        int r, c;

        public Area(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }//
}