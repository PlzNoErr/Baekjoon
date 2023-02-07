import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());
            ArrayList<Food> list = new ArrayList<>();
            int ans = 0;

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                list.add(new Food(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            }

            Collections.sort(list, new Comparator<Food>() {
                @Override
                public int compare(Food o1, Food o2) {
                    return (o1.p + o1.s) - (o2.p + o2.s);
                }
            });

            boolean[] dp = new boolean[200004];
            dp[0] = true;

            for (int i = 0; i < N; i++) {
                for (int j = list.get(i).p; j >= 0; j--) {
                    if (dp[j]) {
                        dp[j + list.get(i).s] = true;
                        ans = Math.max(ans, j + list.get(i).s);
                    }
                }
            }

            System.out.println("Case #" + t + ": " + ans);
        }
    }

    static class Food {
        int p, s;

        public Food(int p, int s) {
            this.p = p;
            this.s = s;
        }
    }
}