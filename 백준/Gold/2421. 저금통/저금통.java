import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        int[] sieve = new int[1000000];
        Arrays.fill(sieve, 1);
        sieve[0] = 0;
        sieve[1] = 0;
        for (int i = 2; i < 1001; i++) {
            if (sieve[i] == 1) {
                for (int j = i * i; j < 1000000; j += i) {
                    sieve[j] = 0;
                }
            }
        }

        Set<Integer> primes = new HashSet<>();
        for (int i = 12; i < 1000000; i++) {
            if (sieve[i] == 1) {
                primes.add(i);
            }
        }

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[][] DP = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                DP[i][j] = Math.max(DP[i - 1][j], DP[i][j - 1]) + isPrime(i, j, primes);
            }
        }

        System.out.println(DP[N][N]);
    }

    private static int isPrime(int a, int b, Set<Integer> primes) {
        int num = Integer.parseInt(Integer.toString(a) + Integer.toString(b));
        if (primes.contains(num)) {
            return 1;
        }
        return 0;
    }
}
