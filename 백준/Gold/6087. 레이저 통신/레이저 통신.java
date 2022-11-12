import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[] dr = { 0, 0, -1, 1 };
	static int[] dc = { -1, 1, 0, 0 };
	static char[][] map;
	static int[][] record;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int w = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		// C의 위치를 배열에 기억해 주자
		int[] Arr = new int[4];
		int tmp = 0;
		map = new char[h][w];
		record = new int[h][w];
		for (int i = 0; i < h; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < w; j++) {
				if (map[i][j] == 'C') {
					Arr[tmp++] = i;
					Arr[tmp++] = j;
				}
			}
		}

		Queue<int[]> Q = new LinkedList<>();
		Q.add(new int[] { Arr[0], Arr[1] });

		while (!Q.isEmpty()) {
			int[] L = Q.poll();
			for (int i = 0; i < 4; i++) {
				int R = L[0] + dr[i];
				int C = L[1] + dc[i];
				while (0 <= R && R < h && 0 <= C && C < w) {
					if (map[R][C] == '*') {
						break;
					}
					if (record[R][C] == 0) {
						Q.add(new int[] { R, C });
						record[R][C] = record[L[0]][L[1]] + 1;
					}
					R += dr[i];
					C += dc[i];
				}
			}
		}
		System.out.println(record[Arr[2]][Arr[3]] - 1);

	}// main
}