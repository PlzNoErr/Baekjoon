import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] A = new int[n];
		int[] B = new int[n];
		String s = br.readLine();
		for (int i = 0; i < n; i++) {
			A[i] = s.charAt(i) - '0';
		}
		s = br.readLine();
		for (int i = 0; i < n; i++) {
			B[i] = s.charAt(i) - '0';
		}
		int[] A2 = A.clone();

		// 첫번째 스위치를 누르지 않음.
		int count = 0;
		for (int i = 1; i < n; i++) {
			if (A[i - 1] != B[i - 1]) {
				A[i - 1] = (A[i - 1] + 1) % 2;
				A[i] = (A[i] + 1) % 2;
				if (i < n - 1) {
					A[i + 1] = (A[i + 1] + 1) % 2;
				}
				count++;
			}
		}

		// 똑같은지 검사
		boolean check = true;
		if (A[n - 1] != B[n - 1]) {
			check = false;
		}
		// 똑같으면 거기서 종료
		if (check) {
			System.out.println(count);
			return;
		}

		// 첫번재 스위치를 누름
		A2[0] = (A2[0] + 1) % 2;
		A2[1] = (A2[1] + 1) % 2;
		count = 1;
		for (int i = 1; i < n; i++) {
			if (A2[i - 1] != B[i - 1]) {
				A2[i - 1] = (A2[i - 1] + 1) % 2;
				A2[i] = (A2[i] + 1) % 2;
				if (i < n - 1) {
					A2[i + 1] = (A2[i + 1] + 1) % 2;
				}
				count++;
			}
		}

		// 똑같은지 검사
		check = true;
		if (A2[n - 1] != B[n - 1]) {
			System.out.println(-1);
			return;
		}
		System.out.println(count);
	}// main
}