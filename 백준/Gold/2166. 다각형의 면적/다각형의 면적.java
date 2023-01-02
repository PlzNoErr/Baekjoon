import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		long[][] Arr = new long[n+1][2];
		for(int i = 0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			Arr[i][0] = Integer.parseInt(st.nextToken());
			Arr[i][1] = Integer.parseInt(st.nextToken());
		}
		Arr[n][0] = Arr[0][0];
		Arr[n][1] = Arr[0][1];
		
		long sumA = 0;
		long sumB = 0;
		
		for(int i = 0; i<n; i++) {
				sumA += Arr[i][0]*Arr[i+1][1];
				sumB += Arr[i][1]*Arr[i+1][0];
		}
		
		System.out.println(String.format("%.1f", (Math.abs(sumA - sumB) / 2.0)));

	}//main
}