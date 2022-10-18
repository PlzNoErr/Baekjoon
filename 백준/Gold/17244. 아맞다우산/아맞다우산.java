import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    static char[][] map;
    static int W;
    static int H;
    static ArrayList<int[]> list;
    static int[][] record;
    static int min = Integer.MAX_VALUE;
    static int[] arr;
    static boolean[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new char[H][W];
        int sr = 0;
        int sc = 0;
        int er = 0;
        int ec = 0;
        list = new ArrayList<>();
        for (int i = 0; i < H; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < W; j++) {
                if (map[i][j] == 'S') {
                    sr = i;
                    sc = j;
                    map[i][j] = 'X';
                    continue;
                }
                if (map[i][j] == 'E') {
                    er = i;
                    ec = j;
                    map[i][j] = 'X';
                    continue;
                }
                if (map[i][j] == 'X') {
                    list.add(new int[]{i, j});
                }
            }
        }
        list.add(0, new int[]{sr, sc});
        list.add(new int[]{er, ec});
        record = new int[list.size()-1][list.size()];
        for (int i = 0; i < list.size()-1; i++) {
            bfs(list.get(i), i);
        }
        arr = new int[list.size()];
        check = new boolean[list.size()];
        arr[list.size()-1] = list.size()-1;
        dfs(1);
        System.out.println(min);

    }//

    static void bfs(int[] idx, int lIdx) {
        boolean[][] visit = new boolean[H][W];
        Queue<int[]> Q = new LinkedList<>();
        Q.add(new int[]{idx[0], idx[1], 0});
        visit[idx[0]][idx[1]] = true;
        while (!Q.isEmpty()) {
            int[] vt = Q.poll();
            if(map[vt[0]][vt[1]]=='X'){
                for (int j = 0; j < list.size(); j++) {
                    if (vt[0] == list.get(j)[0] && vt[1] == list.get(j)[1]) {
                        record[lIdx][j] = vt[2];
                        break;
                    }
                }
            }
            for (int i = 0; i < 4; i++) {
                int R = vt[0] + dr[i];
                int C = vt[1] + dc[i];
                if (0 <= R && R < H && 0 <= C && C < W && !visit[R][C] && map[R][C] != '#') {
                    visit[R][C] = true;
                    Q.add(new int[]{R, C, vt[2] + 1});
                }
            }
        }//
    }//

    static void dfs(int depth){
        if(depth==list.size()-1){
            int sum = 0;
            for (int i = 0; i < list.size()-1; i++) {
                sum += record[arr[i]][arr[i+1]];
            }
            min = Math.min(min, sum);
            return;
        }
        for (int i = 1; i < list.size()-1; i++) {
            if(!check[i]){
                arr[depth] = i;
                check[i] = true;
                dfs(depth+1);
                check[i] = false;
            }
        }
    }//
}