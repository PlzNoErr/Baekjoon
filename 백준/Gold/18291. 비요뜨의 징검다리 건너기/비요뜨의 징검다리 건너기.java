import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static final int MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());
        while (tc-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int ret = 0;
            if (N <= 2) ret = 1;
            else {
                N -= 2;
                ret = (int) pow(N);
            }
            sb.append(ret);
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static long pow(int n) {
        if (n == 0) return 1;
        if (n % 2 == 0) {
            long ret = pow(n / 2);
            return ret * ret % MOD;
        } else
            return 2 * pow(n - 1) % MOD;
    }
}
