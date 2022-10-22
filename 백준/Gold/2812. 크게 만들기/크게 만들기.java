import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		st.nextToken();
        int K = Integer.parseInt(st.nextToken());
 
        String input = br.readLine();
 
        char[] arr = input.toCharArray();
        Deque<Character> dq = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            while (K > 0 && !dq.isEmpty() && dq.peekLast() < arr[i]) {
                dq.removeLast();
                K--;
            }
            dq.addLast(arr[i]);
        }
 
        StringBuilder sb = new StringBuilder();
        while (dq.size() > K) {
            sb.append(dq.removeFirst());
        }
		System.out.println(sb.toString());
	}
}