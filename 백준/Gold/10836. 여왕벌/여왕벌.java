import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] bees = new int[n][n];
        int[] change = new int[n * 2 - 1];
        for (int[] bee : bees) Arrays.fill(bee, 1);

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            for (int i = 0; i < change.length; i++) {
                if (a != 0) {
                    a--;
                    continue;
                } else if (b != 0) {
                    change[i] += 1;
                    b--;
                } else {
                    change[i] += 2;
                }
            }
        }
        bees[0][0] += change[n - 1];
        for (int i = 1; i < n; i++) {
            bees[i][0] += change[n - 1 - i];
            bees[0][i] += change[n - 1 + i];
        }

        for (int i = 1; i < n; i++) {
            if (change[n - 1 - i] < change[n - 1 - i + 1]) change[n - 1 - i] = change[n - 1 - i + 1];
            if (change[n - 1 + i - 1] > change[n - 1 + i]) change[n - 1 + i] = change[n - 1 + i - 1];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                bees[i][j] += Math.max(change[n - 1 - i], change[n - 1 + j]);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(bees[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}
