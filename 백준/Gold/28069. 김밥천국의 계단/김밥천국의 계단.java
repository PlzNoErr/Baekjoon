import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int time = sc.nextInt();
        int[] dp = new int[n + 1];
        boolean possible = false;
        LinkedList<int[]> Q = new LinkedList<>();
        Q.add(new int[]{1, 1});
        while (!Q.isEmpty()) {
            int[] vt = Q.poll();
            if (vt[0] == n) {
                possible = true;
                break;
            }
            if (vt[0] + 1 <= n && vt[1] + 1 <= time && dp[vt[0] + 1] == 0) {
                dp[vt[0] + 1] = vt[1] + 1;
                Q.addLast(new int[]{vt[0] + 1, vt[1] + 1});
            }

            if (vt[0] + vt[0] / 2 <= n && vt[1] + 1 <= time && dp[vt[0] + vt[0] / 2] == 0) {
                dp[vt[0] + vt[0] / 2] = vt[1] + 1;
                Q.addFirst(new int[]{vt[0] + vt[0] / 2, vt[1] + 1});
            }
        }//
        System.out.println(possible ? "minigimbob" : "water");
    }
}
