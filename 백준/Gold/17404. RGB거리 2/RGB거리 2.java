import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] Arr = new int[n][3];
		int[][] dpR = new int[n][3];
		int[][] dpG = new int[n][3];
		int[][] dpB = new int[n][3];
		for(int i = 0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			Arr[i][0] = Integer.parseInt(st.nextToken());
			Arr[i][1] = Integer.parseInt(st.nextToken());
			Arr[i][2] = Integer.parseInt(st.nextToken());
		}
		
		dpR[0][0] = Arr[0][0];
		dpR[0][1] = 9999999;
		dpR[0][2] = 9999999;
		
		dpG[0][0] = 9999999;
		dpG[0][1] = Arr[0][1];
		dpG[0][2] = 9999999;
	
		dpB[0][0] = 9999999;
		dpB[0][1] = 9999999;
		dpB[0][2] = Arr[0][2];		
		
		for(int i = 1; i<n; i++) {
			dpR[i][0] = Math.min(dpR[i-1][1]+Arr[i][0],dpR[i-1][2]+Arr[i][0]);
			dpR[i][1] = Math.min(dpR[i-1][0]+Arr[i][1],dpR[i-1][2]+Arr[i][1]);
			dpR[i][2] = Math.min(dpR[i-1][0]+Arr[i][2],dpR[i-1][1]+Arr[i][2]);
			dpG[i][0] = Math.min(dpG[i-1][1]+Arr[i][0],dpG[i-1][2]+Arr[i][0]);
			dpG[i][1] = Math.min(dpG[i-1][0]+Arr[i][1],dpG[i-1][2]+Arr[i][1]);
			dpG[i][2] = Math.min(dpG[i-1][0]+Arr[i][2],dpG[i-1][1]+Arr[i][2]);
			dpB[i][0] = Math.min(dpB[i-1][1]+Arr[i][0],dpB[i-1][2]+Arr[i][0]);
			dpB[i][1] = Math.min(dpB[i-1][0]+Arr[i][1],dpB[i-1][2]+Arr[i][1]);
			dpB[i][2] = Math.min(dpB[i-1][0]+Arr[i][2],dpB[i-1][1]+Arr[i][2]);
		}
		
		int Min = Math.min(dpR[n-1][1],dpR[n-1][2]);
		Min = Math.min(Min, Math.min(dpG[n-1][0],dpG[n-1][2]));
		Min = Math.min(Min, Math.min(dpB[n-1][0],dpB[n-1][1]));
		
		System.out.println(Min);
		
	}//Main
}