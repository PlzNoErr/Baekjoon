import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int sum = 0, sum_2 = 0;

        for (int i = 0; i < n; i++) {
            int num = sc.nextInt();
            sum += num;
            sum_2 += (num / 2);
        }

        if (sum % 3 != 0) {
            System.out.println("NO");
            return;
        }

        if (sum_2 >= (sum / 3)) System.out.println("YES");
        else System.out.println("NO");
    }
}
