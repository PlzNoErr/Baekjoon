import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int[] dr1 = {-1, 0, 0, 1, 1, 0, 0, -1};
    static int[] dc1 = {0, -1, -1, 0, 0, 1, 1, 0};
    static int[] dr2 = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] dc2 = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int[] dr3 = {-3, -2, 2, 3, 3, 2, -2, -3};
    static int[] dc3 = {-2, -3, -3, -2, 2, 3, 3, 2};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean[][] map = new boolean[10][9];
        int[] sang = {sc.nextInt(), sc.nextInt()};
        int[] king = {sc.nextInt(), sc.nextInt()};
        Queue<int[]> Q = new LinkedList<>();
        Q.add(new int[]{sang[0], sang[1], 0});
        map[sang[0]][sang[1]] = true;
        int ans = -1;

        while (!Q.isEmpty()) {
            int[] vt = Q.poll();
            if (vt[0] == king[0] && vt[1] == king[1]) {
                ans = vt[2];
                break;
            }
            for (int i = 0; i < 8; i++) {
                int R3 = vt[0] + dr3[i];
                int C3 = vt[1] + dc3[i];
                if (R3 < 0 || 10 <= R3 || C3 < 0 || 9 <= C3 || map[R3][C3]) continue;
                int R2 = vt[0] + dr2[i];
                int C2 = vt[1] + dc2[i];
                int R1 = vt[0] + dr1[i];
                int C1 = vt[1] + dc1[i];
                if ((R2 == king[0] && C2 == king[1]) || (R1 == king[0] && C1 == king[1])) continue;
                map[R3][C3] = true;
                Q.add(new int[]{R3, C3, vt[2] + 1});
            }
        }//
        System.out.println(ans);
    }
}
