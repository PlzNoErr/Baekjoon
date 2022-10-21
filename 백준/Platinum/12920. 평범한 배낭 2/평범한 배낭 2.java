import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 물건의 개수
        int n = Integer.parseInt(st.nextToken());
        //최대 무게
        int m = Integer.parseInt(st.nextToken());

        ArrayList<Good> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());

            int tempNum = 1;
            while (tempNum <= num) {
                list.add(new Good(w * tempNum, v * tempNum));
                num -= tempNum;
                tempNum *= 2;
            }
            if (num != 0)
                list.add(new Good(w * num, v * num));
        }

        int[] dp = new int[m + 1];
        for (int i = 0; i < list.size(); i++) {
            for (int j = m; j >= 0; j--) {
                if (j - list.get(i).weight >= 0) {
                    dp[j] = Math.max(dp[j], dp[j - list.get(i).weight] + list.get(i).content);
                }
            }
        }
        System.out.println(dp[m]);
    }//

    static class Good {
        int weight, content;

        public Good(int weight, int content) {
            this.weight = weight;
            this.content = content;
        }
    }

}//