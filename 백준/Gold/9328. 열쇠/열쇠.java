import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    static int document, n, m;
    static HashSet<Integer> set;
    static Queue<int[]> Q;
    static Queue<int[]> DQ;
    static char[][] map;
    static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        while (tc-- > 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            set = new HashSet<>();
            map = new char[n][m];
            visit = new boolean[n][m];
            Q = new LinkedList<>();
            DQ = new LinkedList<>();
            document = 0;
            for (int i = 0; i < n; i++) {
                map[i] = br.readLine().toCharArray();
            }

            ArrayList<ArrayList<int[]>> door = new ArrayList<>();
            for (int i = 0; i < 26; i++) {
                door.add(new ArrayList<>());
            }

            // 주어진 열쇠를 set에 기록
            String key = br.readLine();
            for (int i = 0; i < key.length(); i++) {
                set.add(key.charAt(i) - 'a');
            }

            // 테두리를 돌면서 입구를 입력한다.
            for (int i = 0; i < n; i++) {
                if (map[i][0] != '*') {
                    Q.add(new int[]{i, 0});
                    visit[i][0] = true;
                }
                if (map[i][m - 1] != '*') {
                    Q.add(new int[]{i, m - 1});
                    visit[i][m - 1] = true;
                }
            }
            for (int i = 1; i < m - 1; i++) {
                if (map[0][i] != '*') {
                    Q.add(new int[]{0, i});
                    visit[0][i] = true;
                }
                if (map[n - 1][i] != '*') {
                    Q.add(new int[]{n - 1, i});
                    visit[n - 1][i] = true;
                }
            }

            while (!Q.isEmpty()) {
                int[] loc = Q.poll();
                // 해당칸에 열쇠가 없다면 집어 넣으면서 못 열었던 문들을 Q에 집어 넣는다.
                if (97 <= map[loc[0]][loc[1]] && !set.contains(map[loc[0]][loc[1]] - 'a')) {
                    set.add(map[loc[0]][loc[1]] - 'a');
                    for (int[] v : door.get(map[loc[0]][loc[1]] - 97))
                        Q.add(v);
                }
                // 안열리는 문은 리스트에 기록해 놓는다.
                else if (65 <= map[loc[0]][loc[1]] && map[loc[0]][loc[1]] < 97 && !set.contains(map[loc[0]][loc[1]] - 'A')) {
                    door.get(map[loc[0]][loc[1]] - 'A').add(new int[]{loc[0], loc[1]});
                    continue;
                }
                //
                else if (map[loc[0]][loc[1]] == '$') {
                    document++;
                }

                for (int i = 0; i < 4; i++) {
                    int R = loc[0] + dr[i];
                    int C = loc[1] + dc[i];
                    if (0 <= R && R < n && 0 <= C && C < m && !visit[R][C] && map[R][C] != '*') {
                        visit[R][C] = true;
                        Q.add(new int[]{R, C});
                    }
                }
            }

            sb.append(document + "\n");
        }//tc
        System.out.println(sb.toString());
    }//main
}