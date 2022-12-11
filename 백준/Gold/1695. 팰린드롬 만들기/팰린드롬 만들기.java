import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    
    public static int n;
    public static int[][] dp;
    public static int[] arr;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        dp = new int[n+1][n+1];
        arr = new int[n+1];
        for(int i=0;i<n;i++){
            Arrays.fill(dp[i],-1);
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        int res = dp(0,n-1);
        System.out.println(res);
    }

    public static int dp(int left, int right){
        if(left>=right) 
        	return 0;
        if(dp[left][right]!=-1)
        	return dp[left][right];
        int min =0;
        if(arr[left]==arr[right])
            min =dp(left+1,right-1);
        else 
            min = Math.min(dp(left+1,right)+1 ,dp(left,right-1)+1);     
        return dp[left][right]=min;
    }
}