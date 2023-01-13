import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static ArrayList<int[]> teachers;
    static ArrayList<int[]> spaces;
    static boolean possible;
    static int n;
    static int[][] wall;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        map = new char[n][n];
        teachers = new ArrayList<>();
        spaces = new ArrayList<>();
        wall = new int[3][2];
        possible = false;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = st.nextToken().charAt(0);
                if (map[i][j] == 'T') teachers.add(new int[]{i, j});
                if (map[i][j] == 'X') spaces.add(new int[]{i, j});
            }
        }
        Combination(0, 0);
        System.out.println(possible ? "YES" : "NO");

    }//

    static void Combination(int depth, int idx) {
        if (possible) return;
        if (depth == 3) {
            Simulation();
            return;
        }
        for (int i = idx; i < spaces.size(); i++) {
            wall[depth][0] = spaces.get(i)[0];
            wall[depth][1] = spaces.get(i)[1];
            Combination(depth + 1, idx + 1);
        }
    }

    static void Simulation() {
        map[wall[0][0]][wall[0][1]] = 'W';
        map[wall[1][0]][wall[1][1]] = 'W';
        map[wall[2][0]][wall[2][1]] = 'W';
        boolean isPossible = true;
        here:
        for (int i = 0; i < teachers.size(); i++) {
            for (int j = 0; j < 4; j++) {
                int k = 1;
                while (true) {
                    int R = teachers.get(i)[0] + dr[j] * k;
                    int C = teachers.get(i)[1] + dc[j] * k;
                    if (R < 0 || n <= R || C < 0 || n <= C) break;
                    if (map[R][C] == 'W') break;
                    if (map[R][C] == 'S') {
                        isPossible = false;
                        break here;
                    }
                    k++;
                }
            }
        }
        if (isPossible) possible = true;
        map[wall[0][0]][wall[0][1]] = 'X';
        map[wall[1][0]][wall[1][1]] = 'X';
        map[wall[2][0]][wall[2][1]] = 'X';
    }//
}