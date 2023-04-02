import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n, totalPrice;
    static Nutrient[] food;
    static Nutrient rule;
    static int[] ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        food = new Nutrient[n + 1];
        totalPrice = Integer.MAX_VALUE;
        for (int i = 0; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int nutrient = Integer.parseInt(st.nextToken());
            int fat = Integer.parseInt(st.nextToken());
            int carbohydrate = Integer.parseInt(st.nextToken());
            int vitamin = Integer.parseInt(st.nextToken());
            if (i == 0) rule = new Nutrient(nutrient, fat, carbohydrate, vitamin, 0);
            else {
                int price = Integer.parseInt(st.nextToken());
                food[i] = new Nutrient(nutrient, fat, carbohydrate, vitamin, price);
            }
        }
        for (int i = 1; i <= n; i++) {
            int[] print = new int[i];
            dfs(1, 0, 0, print, i);
        }
        if (totalPrice == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        System.out.println(totalPrice);
        for (int i = 0; i < ans.length; i++) {
            System.out.printf(ans[i] + " ");
        }
    }

    static void dfs(int idx, int depth, int total, int[] print, int limit) {
        if (total > totalPrice) return;
        if (depth == limit) {
            if (check(print)) {
                if (totalPrice == Integer.MAX_VALUE || total < totalPrice) {
                    ans = Arrays.copyOf(print, print.length);
                    totalPrice = total;
                    return;
                }
                if (Arrays.compare(ans, print) > 0) {
                    ans = Arrays.copyOf(print, print.length);
                }
            }
            return;
        }

        for (int i = idx; i <= n; i++) {
            print[depth] = i;
            dfs(i + 1, depth + 1, total + food[i].price, print, limit);
        }
    }

    static boolean check(int[] choice) {
        int protein = 0;
        int fat = 0;
        int carbohydrate = 0;
        int vitamin = 0;
        for (int i = 0; i < choice.length; i++) {
            protein += food[choice[i]].protein;
            fat += food[choice[i]].fat;
            carbohydrate += food[choice[i]].carbohydrate;
            vitamin += food[choice[i]].vitamin;
        }
        if (protein >= rule.protein && fat >= rule.fat && carbohydrate >= rule.carbohydrate && vitamin >= rule.vitamin)
            return true;

        return false;
    }

    static class Nutrient {
        int protein, fat, carbohydrate, vitamin, price;

        public Nutrient(int protein, int fat, int carbohydrate, int vitamin, int price) {
            this.protein = protein;
            this.fat = fat;
            this.carbohydrate = carbohydrate;
            this.vitamin = vitamin;
            this.price = price;
        }
    }
}
