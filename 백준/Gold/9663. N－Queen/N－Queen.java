import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

	public static int[] Arr;
	public static int n;
	public static int count = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		Arr = new int[n];

		nQueen(0);
		System.out.println(count);

	}

	public static void nQueen(int depth) {
		if (depth == n) {
			count++;
			return;
		}

		for (int i = 0; i < n; i++) {
			Arr[depth] = i;
			if (Possibility(depth)) {
				nQueen(depth + 1);
			}
		}
	}//N-queen 숫자새기

	public static boolean Possibility(int c) {
		for (int i = 0; i < c; i++) {
			if (Arr[c] == Arr[i]) {
				return false;
			} else if (Math.abs(c - i) == Math.abs(Arr[c] - Arr[i])) {
				return false;
			}
		}
		return true;
	}//놓을 수 있는지 검사
}