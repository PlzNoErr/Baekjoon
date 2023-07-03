import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.lang.*;
 
public class Main {
    static int t,n,target;
    static int[] arr;
    static int [][] dp;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<t; i++){
            n = Integer.parseInt(br.readLine());
            arr = new int[n+1];
            dp = new int[n+1][10001];
            String[] s = br.readLine().split(" ");
            for(int j=1; j<=n; j++){
                arr[j] = Integer.parseInt(s[j-1]);
                dp[j][Integer.parseInt(s[j-1])]++;
            }
            target = Integer.parseInt(br.readLine());
 
                    for(int j=1; j<=n; j++){
            for(int q=1; q<=target; q++){
                if(q-arr[j]<0){
                    dp[j][q]=dp[j-1][q];
                    continue;
                }
                dp[j][q] += dp[j-1][q]+dp[j][q-arr[j]];
            }
        }
 
            sb.append(dp[n][target]).append("\n");
        }
        System.out.println(sb.toString());
    }
}