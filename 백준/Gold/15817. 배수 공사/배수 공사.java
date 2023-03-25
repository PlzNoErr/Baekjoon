import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        int[] pipe = new int[x + 1];
        pipe[0] = 1;
        int a, b;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            for (int j = x; j >= 0; j--) {
                if (pipe[j] != 0) {
                    for (int k = 1; k <= b; k++) {
                        if (j + a * k > x) break;
                        pipe[j + a * k] += pipe[j];
                    }
                }
            }
        }
        System.out.println(pipe[x]);
    }

}
