import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int[][] map = new int[n][m];
		boolean[][] check = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		int[] dr = { 0, 0, 1, -1 };
		int[] dc = { 1, -1, 0, 0 };

		LinkedList<Integer> Qr = new LinkedList<>();
		LinkedList<Integer> Qc = new LinkedList<>();

		Qr.add(0);
		Qc.add(0);

		while (!Qr.isEmpty()) {
			int r = Qr.pollFirst();
			int c = Qc.pollFirst();
			if (r == n - 1 && c == m - 1) {
				System.out.println(map[r][c]);
				return;
			}

			for (int k = 0; k < 4; k++) {
				if (r + dr[k] >= 0 && r + dr[k] < n && c + dc[k] >= 0 && c + dc[k] < m) {
					if (map[r + dr[k]][c + dc[k]] == 0 && !check[r + dr[k]][c + dc[k]]) {
						Qr.addFirst(r + dr[k]);
						Qc.addFirst(c + dc[k]);
						check[r + dr[k]][c + dc[k]] = true;
						map[r + dr[k]][c + dc[k]] = map[r][c];
					}
					if (map[r + dr[k]][c + dc[k]] == 1 && !check[r + dr[k]][c + dc[k]]) {
						Qr.addLast(r + dr[k]);
						Qc.addLast(c + dc[k]);
						check[r + dr[k]][c + dc[k]] = true;
						map[r + dr[k]][c + dc[k]] = map[r][c]+1;
					}
				}
			}

		} // while

	}// main
}
