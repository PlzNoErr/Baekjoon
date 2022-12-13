import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int gcd = Integer.parseInt(st.nextToken());
        int lcd = Integer.parseInt(st.nextToken());

        long mul = (long) gcd * lcd;

        int a = 0, b = 0;

        // 두 수 a,b는 최대공약수의 배수이다.
        // 어차피 합이 최소이려면 sqrt에 가장 가까워야 한다.
        for (int i = gcd; i <= Math.sqrt(mul); i += gcd) {
            if (mul % i == 0 && euclidean(i, mul / i) == gcd) {
                a = i;
                b = (int) (mul / i);
            }
        }

        System.out.println(a + " " + b);
    }

    public static long euclidean(long a, long b) {
        long temp = a % b;
        if (temp == 0) {
            return b;
        }
        return euclidean(b, temp);
    }

}