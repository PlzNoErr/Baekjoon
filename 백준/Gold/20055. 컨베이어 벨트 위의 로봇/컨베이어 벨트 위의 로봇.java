import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] Arr = new int[2 * n + 1];
		int[] robot = new int[2 * n + 1];
		st = new StringTokenizer(br.readLine());
		int limit = 0;
		for (int i = 1; i <= 2 * n; i++) {
			Arr[i] = Integer.parseInt(st.nextToken());
			if (Arr[i] == 0)
				limit++;
		}
		// 입력

		int print = 0;
		int up = 1;
		int down = n;
		while (limit < k) {
			print++;
			if (up == 1) {
				up = 2 * n;
			} else {
				up -= 1;
			}

			if (down == 1) {
				down = 2 * n;
			} else {
				down -= 1;
			}
			// 컨베이어 벨트가 돌았음.

			if (robot[down] == 1)
				robot[down] = 0;
			// 로봇이 다운에 도착하면 자동으로 내린다.

			if (up < down) {
				for (int i = down - 1; i > up; i--) {
					if (Arr[i + 1] >= 1 && robot[i + 1] == 0 && robot[i] == 1) {
						Arr[i + 1] -= 1;
						robot[i + 1] = 1;
						robot[i] = 0;
						if (Arr[i + 1] == 0)
							limit++;
					}
				}
			}
			if (up > down) {
				for (int i = down - 1; i >= 1; i--) {
					if (Arr[i + 1] >= 1 && robot[i + 1] == 0 && robot[i] == 1) {
						Arr[i + 1] -= 1;
						robot[i + 1] = 1;
						robot[i] = 0;
						if (Arr[i + 1] == 0)
							limit++;
					}
				}
				if (Arr[1] >= 1 && robot[1] == 0 && robot[2 * n] == 1) {
					Arr[1] -= 1;
					robot[1] = 1;
					robot[2 * n] = 0;
					if (Arr[1] == 0)
						limit++;
				}
				for (int i = 2 * n - 1; i > up; i--) {
					if (Arr[i + 1] >= 1 && robot[i + 1] == 0 && robot[i] == 1) {
						Arr[i + 1] -= 1;
						robot[i + 1] = 1;
						robot[i] = 0;
						if (Arr[i + 1] == 0)
							limit++;
					}
				}
			}
			if (robot[down] == 1)
				robot[down] = 0;
			// 로봇이 다운에 도착하면 자동으로 내린다.

			if (Arr[up] > 0) {
				Arr[up] -= 1;
				robot[up] = 1;
				if (Arr[up] == 0)
					limit++;
			}
			// 로봇을 컨베이너 벨트에 올린다.

		} // while

		System.out.println(print);
	}// main
}
