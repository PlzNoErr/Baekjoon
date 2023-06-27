import java.io.*;
import java.util.*;
 
public class Main {
    public static int n;
    public static int[][] card, dp;
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        card = new int[n][2];
        dp = new int[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(dp[i], -1);
        for (int i = 0; i < 2; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) card[j][i] = Integer.parseInt(st.nextToken());    
        }
        System.out.println(TopDown(0, 0));
    }
 
    public static int TopDown(int leftDepth, int rightDepth) {
        if (leftDepth == n || rightDepth == n) return 0; 
        if (dp[leftDepth][rightDepth] != -1) return dp[leftDepth][rightDepth];
        else {
            dp[leftDepth][rightDepth] = 0;
            if (card[leftDepth][0] > card[rightDepth][1])
                dp[leftDepth][rightDepth] = TopDown(leftDepth, rightDepth + 1) + card[rightDepth][1];
            dp[leftDepth][rightDepth] = Math.max(TopDown(leftDepth + 1, rightDepth + 1),
                    Math.max(TopDown(leftDepth + 1, rightDepth), dp[leftDepth][rightDepth]));
        }
        return dp[leftDepth][rightDepth];
    }
}