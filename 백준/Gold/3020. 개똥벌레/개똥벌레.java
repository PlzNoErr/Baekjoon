import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int[] bot = new int[H + 1];
        int[] top = new int[H + 1];
        int min = N;
        int cnt = 0;

        for (int i = 0; i < N / 2; i++) {
            bot[Integer.parseInt(br.readLine())]++;
            top[Integer.parseInt(br.readLine())]++;
        }

        int[] bot_sum = new int[H + 1];
        int[] top_sum = new int[H + 1];

        for (int i = 1; i < H + 1; i++) {
            top_sum[i] = top_sum[i - 1] + top[i];
            bot_sum[i] = bot_sum[i - 1] + bot[i];
        }

        for (int i = 1; i < H + 1; i++) {
            int crush = 0;

            crush += bot_sum[H] - bot_sum[i - 1];
            crush += top_sum[H] - top_sum[H - i];
            
            if (min > crush) {
                min = crush;
                cnt = 1;
            } else if (min == crush) {
                cnt++;
            }
        }

        System.out.println(min + " " + cnt);

    }
}