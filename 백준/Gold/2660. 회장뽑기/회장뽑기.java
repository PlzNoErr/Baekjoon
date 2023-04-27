import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[][] map = new int[n + 1][n + 1];
        while (true) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (a == -1 && b == -1) break;
            map[a][b] += 1;
            map[b][a] += 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (map[i][j] == 0 && i != j) map[i][j] = 500000;
            }
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        ArrayList<int[]> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 1; j <= n; j++) max = Math.max(max, map[i][j]);
            list.add(new int[]{i, max});
        }
        Collections.sort(list, (int[] arr1, int[] arr2) -> arr1[1] == arr2[1] ? arr1[0] - arr2[0] : arr1[1] - arr2[1]);
        sb.append(list.get(0)[1] + " ");
        int count = 0;
        for (int[] arr : list) if (arr[1] == list.get(0)[1]) count++;
        sb.append(count + "\n");
        for (int[] arr : list) if (arr[1] == list.get(0)[1]) sb.append(arr[0] + " ");
        System.out.println(sb.toString());
    }
}
