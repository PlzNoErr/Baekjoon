import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] Arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		Arr = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				Arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		String check = br.readLine().replace(" ", "");
		int quadrant = 1, rot = 0, shuf = 0;
		int[][] qq = { { 0, 0, 0, 0, 0 }, { 0, 3, 4, 1, 2 }, { 0, 2, 1, 4, 3 } };


		int count = 0;
		for (int i = 0; i < check.length(); i++) {
			if (check.charAt(i) == '1' || check.charAt(i) == '2') {
				count++;
			}

			if (count % 2 == 0) {
				if (check.charAt(i) == '1' || check.charAt(i) == '2') {
					quadrant = qq[check.charAt(i) - '0'][quadrant];
				}
				if (check.charAt(i) == '3') {
					rot = (rot + 1) % 4;
				}
				if (check.charAt(i) == '4') {
					rot = (rot + 3) % 4;
				}
				if (check.charAt(i) == '5') {
					shuf = (shuf + 1) % 4;
				}
				if (check.charAt(i) == '6') {
					shuf = (shuf + 3) % 4;
				}
			}
			if (count % 2 == 1) {
				if (check.charAt(i) == '1' || check.charAt(i) == '2') {
					quadrant = qq[check.charAt(i) - '0'][quadrant];
				}
				if (check.charAt(i) == '3') {
					rot = (rot + 3) % 4;
				}
				if (check.charAt(i) == '4') {
					rot = (rot + 1) % 4;
				}
				if (check.charAt(i) == '5') {
					shuf = (shuf + 3) % 4;
				}
				if (check.charAt(i) == '6') {
					shuf = (shuf + 1) % 4;
				}
			}
		}

		// rotate
		if (rot == 1) {
			int N = Arr.length;
			int M = Arr[0].length;
			go(3, N, M);
		}
		if (rot == 2) {
			int N = Arr.length;
			int M = Arr[0].length;
			go(3, N, M);
			int N1 = Arr.length;
			int M1 = Arr[0].length;
			go(3, N1, M1);
		}
		if (rot == 3) {
			int N = Arr.length;
			int M = Arr[0].length;
			go(4, N, M);
		}

		// shuffle
		if (shuf == 1) {
			int N = Arr.length;
			int M = Arr[0].length;
			go(5, N, M);
		}
		if (shuf == 2) {
			int N = Arr.length;
			int M = Arr[0].length;
			go(5, N, M);
			int N1 = Arr.length;
			int M1 = Arr[0].length;
			go(5, N1, M1);
		}
		if (shuf == 3) {
			int N = Arr.length;
			int M = Arr[0].length;
			go(6, N, M);
		}
		
		// reverse
		if (quadrant == 2) {
			int N = Arr.length;
			int M = Arr[0].length;
			go(2, N, M);
		}
		if (quadrant == 3) {
			int N = Arr.length;
			int M = Arr[0].length;
			go(1, N, M);
		}
		if (quadrant == 4) {
			int N = Arr.length;
			int M = Arr[0].length;
			go(1, N, M);
			go(2, N, M);
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Arr.length; i++) {
			for (int j = 0; j < Arr[0].length; j++) {
				sb.append(Arr[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}// main

	static void go(int order, int n, int m) {
		if (order == 1) {
			int[] temp;
			for (int i = 0; i < n / 2; i++) {
				temp = Arr[i];
				Arr[i] = Arr[n - 1 - i];
				Arr[n - 1 - i] = temp;
			}
		} // 1

		if (order == 2) {
			int temp;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m / 2; j++) {
					temp = Arr[i][j];
					Arr[i][j] = Arr[i][m - 1 - j];
					Arr[i][m - 1 - j] = temp;
				}
			}
		} // 2

		if (order == 3) {
			int[][] temp = new int[m][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					temp[j][n - 1 - i] = Arr[i][j];
				}
			}
			Arr = temp;
		} // 3

		if (order == 4) {
			int[][] temp = new int[m][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					temp[m - 1 - j][i] = Arr[i][j];
				}
			}
			Arr = temp;
		} // 4

		if (order == 5) {
			int[][] temp1 = new int[n / 2][m / 2];
			int[][] temp2 = new int[n / 2][m / 2];
			int[][] temp3 = new int[n / 2][m / 2];
			int[][] temp4 = new int[n / 2][m / 2];
			for (int i = 0; i < n / 2; i++) {
				for (int j = 0; j < m / 2; j++) {
					temp1[i][j] = Arr[i][j];
					temp4[i][j] = Arr[i + n / 2][j];
				}
			}
			for (int i = 0; i < n / 2; i++) {
				for (int j = m / 2; j < m; j++) {
					temp2[i][j - m / 2] = Arr[i][j];
					temp3[i][j - m / 2] = Arr[i + n / 2][j];
				}
			}

			for (int i = 0; i < n / 2; i++) {
				for (int j = 0; j < m / 2; j++) {
					Arr[i][j] = temp4[i][j];
					Arr[i + n / 2][j] = temp3[i][j];
				}
			}
			for (int i = 0; i < n / 2; i++) {
				for (int j = m / 2; j < m; j++) {
					Arr[i][j] = temp1[i][j - m / 2];
					Arr[i + n / 2][j] = temp2[i][j - m / 2];
				}
			}
		} // 5

		if (order == 6) {
			int[][] temp1 = new int[n / 2][m / 2];
			int[][] temp2 = new int[n / 2][m / 2];
			int[][] temp3 = new int[n / 2][m / 2];
			int[][] temp4 = new int[n / 2][m / 2];
			for (int i = 0; i < n / 2; i++) {
				for (int j = 0; j < m / 2; j++) {
					temp1[i][j] = Arr[i][j];
					temp4[i][j] = Arr[i + n / 2][j];
				}
			}
			for (int i = 0; i < n / 2; i++) {
				for (int j = m / 2; j < m; j++) {
					temp2[i][j - m / 2] = Arr[i][j];
					temp3[i][j - m / 2] = Arr[i + n / 2][j];
				}
			}

			for (int i = 0; i < n / 2; i++) {
				for (int j = 0; j < m / 2; j++) {
					Arr[i][j] = temp2[i][j];
					Arr[i + n / 2][j] = temp1[i][j];
				}
			}
			for (int i = 0; i < n / 2; i++) {
				for (int j = m / 2; j < m; j++) {
					Arr[i][j] = temp3[i][j - m / 2];
					Arr[i + n / 2][j] = temp4[i][j - m / 2];
				}
			}
		} // 6
		return;
	}// go
}
