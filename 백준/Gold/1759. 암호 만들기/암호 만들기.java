import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int m;
	static int n;
	static char[] Arr;
	static boolean[] check;
	static char[] print;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		Arr = new char[n];
		check = new boolean[n];
		print = new char[m];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++) {
			Arr[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(Arr);
		sb = new StringBuilder();
		password(0);
		System.out.println(sb.toString());

	}// main

	static void password(int depth) {
		if (depth == m) {
			int mo = 0;
			int za = 0;
			for (int i = 0; i < m; i++) {
				if (print[i] == 'a' || print[i] == 'e' || print[i] == 'i' || print[i] == 'o' || print[i] == 'u') {
					mo++;
				} else {
					za++;
				}
			}

			if (mo > 0 && za > 1) {
				String s = new String(print);
				sb.append(s + "\n");
				return;
			} else {
				return;
			}

		}

		for (int i = 0; i < n; i++) {
			if (!check[i] && depth == 0) {
				check[i] = true;
				print[depth] = Arr[i];
				password(depth + 1);
				check[i] = false;
			}
			if (!check[i] && depth != 0) {
				if (print[depth - 1] < Arr[i]) {
					check[i] = true;
					print[depth] = Arr[i];
					password(depth + 1);
					check[i] = false;
				}
			}

		}
	}// password

}
