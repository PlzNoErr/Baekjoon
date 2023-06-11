import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static String answer;
	static int N;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());	
		answer = "";
		go("");
		System.out.println(answer);	
	}
	
	static void go(String temp) {
		
		if(answer != "") return;
		
		if(temp.length() == N) {
			answer = temp;
			return;
		}
		
		for(int i=1;i<=3;i++) {
			if(check(temp+i)) {
				go(temp+i);
			}
		}
	}
	
	public static boolean check(String value) {
		for(int i=1;i<=value.length()/2;i++) {
			for(int j=0;j<=value.length()-i*2;j++) {
				if(value.substring(j,j+i).equals(value.substring(j+i,j+i*2))) return false;
			}
		}
		return true;
	}

}