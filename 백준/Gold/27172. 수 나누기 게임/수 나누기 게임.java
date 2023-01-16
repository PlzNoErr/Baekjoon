import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] card = new int[1000001];
        int[] scroe = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            card[Integer.parseInt(st.nextToken())] = i;
        }
        for (int i = 1; i <= 500000; i++) {
            if (card[i] != 0) {
                int num = 2 * i;
                while (num <= 1000000) {
                    if (card[num] != 0) {
                        scroe[card[i]]++;
                        scroe[card[num]]--;
                    }
                    num += i;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++)
            sb.append(scroe[i] + " ");
        System.out.println(sb.toString());
    }
}