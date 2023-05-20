import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        long c = Long.parseLong(st.nextToken());
        long d = Long.parseLong(st.nextToken());
        long u = Long.parseLong(st.nextToken());

        long count = 0;
        if (a <= u) count = (u - a) / b + 1;

        if (d == 1 && c <= u) {
            if (a != c && (a >= c || (c - a) % b != 0)) count++;
        } else {
            while (c <= u) {
                if (c < a || (c - a) % b != 0) count++;
                c *= d;
            }
        }
        System.out.println(count);
    }
}