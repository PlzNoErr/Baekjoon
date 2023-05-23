import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] info;
    static boolean[] check;
    static int problem, student, ans;
    static boolean isFind = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        problem = Integer.parseInt(st.nextToken());
        student = Integer.parseInt(st.nextToken());
        info = new int[student][];
        for (int i = 0; i < student; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            info[i] = new int[num];
            for (int j = 0; j < num; j++) info[i][j] = Integer.parseInt(st.nextToken());
        }
        ans = -1;
        for (int i = 1; i <= problem; i++) {
            int[] arr = new int[i];
            combination(arr, 0, 0, i);
        }

        System.out.println(ans);
    }

    static void combination(int[] arr, int idx, int depth, int target_depth) {
        if (isFind) return;
        if (depth == target_depth) {
            //시뮬레이션
            check = new boolean[problem + 1];
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < info[arr[i]].length; j++) check[info[arr[i]][j]] = true;
            }
            boolean isCheck = true;
            for (int i = 1; i <= problem; i++) {
                if (!check[i]) {
                    isCheck = false;
                    break;
                }
            }
            if (isCheck) {
                isFind = true;
                ans = target_depth;
            }
            return;
        }

        for (int i = idx; i < student; i++) {
            arr[depth] = i;
            combination(arr, idx + 1, depth + 1, target_depth);
        }

    }//
}
