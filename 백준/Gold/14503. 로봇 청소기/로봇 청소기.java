import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { -1, 0, 1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] map = new int[n][m];

		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int direction = Integer.parseInt(st.nextToken());

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 입력

		int count = 0;
		
		if (map[r][c] == 0) {
			map[r][c] = 2;
			count++;
		}
		
		here: while (true) {
			
			int check = 4;
			for (int i = (4 - direction) % 4; check-- > 0;) {
				if (map[r + dr[i]][c + dc[i]] == 0) {
					r = r + dr[i];
					c = c + dc[i];
					direction = (direction + 3) % 4;
					map[r][c] = 2;
					count++;
					continue here;
				} 
				else {
					direction = (direction + 3) % 4;
					i = (4 - direction) % 4;
				}
			} // for
			
			if (map[r + dr[(5 - direction) % 4]][c + dc[(5 - direction) % 4]] != 1) {
				r = r + dr[(5 - direction) % 4];
				c = c + dc[(5 - direction) % 4];
				continue;
			}

			if (map[r + dr[(5 - direction) % 4]][c + dc[(5 - direction) % 4]] == 1)
				break;
		} // while
		
		System.out.println(count);
	}// main
}
