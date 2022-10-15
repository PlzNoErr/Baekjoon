import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	static int[] dr = { 0, 0, -1, 1 };
	static int[] dc = { -1, 1, 0, 0 };
	static int[][] map;
	static boolean[][][][] check;
	static int n;
	static int m;
	static int k;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		check = new boolean[2][k + 1][n][m];
		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		LinkedList<Info> Q = new LinkedList<>();
		Q.add(new Info(0, 0, 0, 0));
		check[0][0][0][0] = true;

		while (!Q.isEmpty()) {
			Info info = Q.poll();
			int r = info.r;
			int c = info.c;
			int bt = info.bt;
			int record = info.record;
			if (r == n - 1 && c == m - 1) {
				System.out.println(record+1);
				return;
			}

			for (int i = 0; i < 4; i++) {
				if (r + dr[i] >= 0 && r + dr[i] < n && c + dc[i] >= 0 && c + dc[i] < m) {
					if (map[r + dr[i]][c + dc[i]] == 0 && !check[(record + 1) % 2][bt][r + dr[i]][c + dc[i]]
							&& record % 2 == 0) {
						check[(record + 1) % 2][bt][r + dr[i]][c + dc[i]] = true;
						Q.add(new Info(r + dr[i], c + dc[i], record + 1, bt));
					}
					if (map[r + dr[i]][c + dc[i]] == 0 && !check[(record + 1) % 2][bt][r + dr[i]][c + dc[i]]
							&& record % 2 == 1) {
						check[(record + 1) % 2][bt][r + dr[i]][c + dc[i]] = true;
						Q.add(new Info(r + dr[i], c + dc[i], record + 1, bt));
					}
					if (map[r + dr[i]][c + dc[i]] == 1 && bt < k
							&& !check[(record + 1) % 2][bt + 1][r + dr[i]][c + dc[i]] && record % 2 == 0) {
						check[(record + 1) % 2][bt + 1][r + dr[i]][c + dc[i]] = true;
						Q.add(new Info(r + dr[i], c + dc[i], record + 1, bt + 1));
					}
					if (map[r + dr[i]][c + dc[i]] == 1 && bt < k
							&& !check[(record + 1) % 2][bt + 1][r + dr[i]][c + dc[i]] && record % 2 == 1) {
						check[(record + 1) % 2][bt][r][c] = true;
						Q.add(new Info(r, c, record + 1, bt));
					}
				}
			}
		} // while

		System.out.println(-1);

	}// main

	static class Info {
		int record, bt, r, c;

		Info(int r, int c, int record, int bt) {
			this.r = r;
			this.c = c;
			this.record = record;
			this.bt = bt;
		}
	}
}