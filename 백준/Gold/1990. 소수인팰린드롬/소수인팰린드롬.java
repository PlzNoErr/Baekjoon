import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int start = sc.nextInt();
        int end = sc.nextInt();

        boolean[] isPrime = new boolean[end + 1];

        for (int i = 2; i * i <= end; i++) {
            if (!isPrime[i]) {
                for (int j = i * i; j <= end; j += i) {
                    isPrime[j] = true;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = start; i <= end; i++) {
            if (!isPrime[i] && check(i)) sb.append(i + "\n");
        }
        sb.append(-1);
        System.out.println(sb.toString());

    }//

    static boolean check(int num) {
        String s = String.valueOf(num);
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) return false;
        }

        return true;
    }
}