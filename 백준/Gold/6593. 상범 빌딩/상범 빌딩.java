import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] dr = {1, -1, 0, 0, 1, -1};
    static int[] dc = {0, 0, 1, -1};
    static char[][][] building;
    static int[][][] visit;
    static Queue<int[]> Q;
    static int a, b, c, L, R, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        here:
        while (true) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            if (a == 0 && b == 0 && c == 0) break;
            building = new char[a][b][c];
            visit = new int[a][b][c];
            Q = new LinkedList<>();
            for (int i = 0; i < a; i++) {
                for (int j = 0; j < b; j++) {
                    building[i][j] = br.readLine().toCharArray();
                    for (int k = 0; k < c; k++) {
                        if (building[i][j][k] == 'S') Q.add(new int[]{i, j, k});
                    }
                }
                br.readLine();
            }
            while (!Q.isEmpty()) {
                int[] now = Q.poll();
                if (building[now[0]][now[1]][now[2]] == 'E') {
                    sb.append("Escaped in " + visit[now[0]][now[1]][now[2]] + " minute(s)." + "\n");
                    continue here;
                }
                for (int i = 0; i < 6; i++) {
                    if (i < 4) {
                        L = now[0];
                        R = now[1] + dr[i];
                        C = now[2] + dc[i];
                    } else {
                        L = now[0] + dr[i];
                        R = now[1];
                        C = now[2];
                    }
                    if (check(L, R, C) && building[L][R][C] != '#' && visit[L][R][C] == 0) {
                        visit[L][R][C] = visit[now[0]][now[1]][now[2]] + 1;
                        Q.add(new int[]{L, R, C});
                    }
                }
            } //Q
            sb.append("Trapped!" + "\n");
        }
        System.out.println(sb.toString());
    }

    static boolean check(int A, int B, int C) {
        if (0 <= A && A < a && 0 <= B && B < b && 0 <= C && C < c) return true;
        return false;
    }
}