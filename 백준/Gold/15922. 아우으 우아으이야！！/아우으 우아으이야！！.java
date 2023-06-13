import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine()," ");
		int N = Integer.valueOf(st.nextToken());
		int a1,b1,a2,b2;
		
		st = new StringTokenizer(in.readLine()," ");
		a1=Integer.valueOf(st.nextToken());
		b1=Integer.valueOf(st.nextToken());
		int sum=0;
		for(int i=0;i<N-1; i++) {
			st = new StringTokenizer(in.readLine()," ");
			a2= Integer.valueOf(st.nextToken());
			b2 = Integer.valueOf(st.nextToken());
			if(b1>=b2) {
				continue;
			}else if(b1>=a2) {
				b1=b2;
			}else {
				sum+=Math.abs(b1-a1);
				a1=a2;
				b1=b2;
			}
		}
		sum+=Math.abs(b1-a1);
		System.out.println(sum);
	}
}