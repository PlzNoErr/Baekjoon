import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M, H, map[][], answer;
    static int[] home;
    static List<int[]> mints;
    static boolean used[];

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        mints = new ArrayList<>();
        answer = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) home = new int[]{i, j};
                else if (map[i][j] == 2) mints.add(new int[]{i, j});
            }
        }

        used = new boolean[mints.size()];
        dfs(0, new int[mints.size()][2]);
        System.out.println(answer);
    }

    static void dfs(int depth, int[][] list) {
        if (depth == list.length) {
            simul(list);
            return;
        }

        for (int i = 0; i < mints.size(); i++) {
            if (!used[i]) {
                used[i] = true;
                list[depth] = mints.get(i);
                dfs(depth + 1, list);
                used[i] = false;
            }
        }
    }//

    static void simul(int[][] list) {
        int cur[] = home;
        int curH = M;
        int cnt = 0;
        for (int[] mint : list) {
            int dist = Math.abs(cur[0] - mint[0]) + Math.abs(cur[1] - mint[1]);
            if (dist > curH) break;
            curH -= dist;
            curH += H;
            cnt++;
            cur = new int[]{mint[0], mint[1]};
            int tohome = Math.abs(home[0] - mint[0]) + Math.abs(home[1] - mint[1]);
            if (tohome <= curH) answer = Math.max(answer, cnt);
        }
    }//
}
