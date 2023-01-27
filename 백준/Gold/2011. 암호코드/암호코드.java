import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        if (s.charAt(0) == '0') {
            System.out.println("0");
            return;
        }

        long[] dp = new long[s.length() + 1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= s.length(); i++) {
            char ch = s.charAt(i - 1);
            char prev = s.charAt(i - 2); 
            if (ch == '0') {
                if (prev == '1' || prev == '2') dp[i] = dp[i - 2] % 1000000;
                else break;
            } else {
                if (prev == '0') dp[i] = dp[i - 1] % 1000000;
                else {
                    int temp = (prev - '0') * 10 + (ch - '0');
                    if (temp <= 26) dp[i] = (dp[i - 2] + dp[i - 1]) % 1000000;
                    else dp[i] = dp[i - 1] % 1000000;
                }
            }
        }
        System.out.println(dp[s.length()] % 1000000);
    }
}