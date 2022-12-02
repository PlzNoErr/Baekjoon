import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());
        while (tc-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Math.abs(Integer.parseInt(st.nextToken()));
            int m = Math.abs(Integer.parseInt(st.nextToken()));
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int[] Arr = new int[num];
            for (int i = 0; i < num; i++) {
                Arr[i] = Integer.parseInt(st.nextToken());
            }
            int GCD = 0;
            if (num == 1) GCD = Arr[0];
            else {
                GCD = uclid_GCD(Arr[0], Arr[1]);
                for (int i = 2; i < num; i++) {
                    GCD = uclid_GCD(Arr[i], GCD);
                }
            }
            if (n % GCD == 0 && m % GCD == 0) sb.append("Ta-da" + "\n");
            else sb.append("Gave up" + "\n");
        }
        System.out.println(sb.toString());

    }//

    static int uclid_GCD(int a, int b) {
        if (a % b == 0) return b;
        else return uclid_GCD(b, a % b);
    }
}