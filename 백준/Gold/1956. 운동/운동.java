import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static int MAX = Integer.MAX_VALUE; 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().trim().split(" ");
		
		int V = Integer.parseInt(str[0]);
		int E = Integer.parseInt(str[1]);
		
		int[][] dist = new int[V+1][V+1];
		
		for(int i=1;i<=V;i++) {
			Arrays.fill(dist[i], MAX);
		}
		
		for(int i=0;i<E;i++) {
			str = br.readLine().trim().split(" ");
			int s = Integer.parseInt(str[0]);
			int e = Integer.parseInt(str[1]);
			int d = Integer.parseInt(str[2]);
			
			dist[s][e] = d;
		}
		
		for(int k=1;k<=V;k++) {
			for(int a=1;a<=V;a++) {
				if(dist[a][k]== MAX) continue;
				for(int b=1;b<=V;b++) {
					if(dist[k][b]== MAX) continue;
					dist[a][b] = Math.min(dist[a][b], dist[a][k]+dist[k][b]);
				}
			}
		}
		
		int result = MAX;
		for(int a=1;a<=V;a++) {
			result = Math.min(result, dist[a][a]); 
		}
		
		if(result == MAX) System.out.println(-1);
		else System.out.println(result);
	}
}