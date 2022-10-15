import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int[] dr = { 1, -1, 0, 0, 0 };
	static int[] dc = { 0, 0, 1, -1, 0 };
	static char[][] map;
	static boolean[] first_line;
	static int ans = Integer.MAX_VALUE;

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[][] origin_map = new char[10][10];
		for (int i = 0; i < 10; i++) {
			origin_map[i] = br.readLine().toCharArray();
		}
		first_line = new boolean[10];
		map = new char[10][10];
		dfs_firstLine(origin_map, 0);
		if (ans == Integer.MAX_VALUE) {
			System.out.println(-1);
			return;
		}
		System.out.println(ans);

	}// main

	// 첫째 행의 버튼을 누를 지 말지 시뮬 돌림
	static void dfs_firstLine(char[][] origin_map, int depth) {
		if (depth == 10) {
			// 깊은 복사
			for (int i = 0; i < origin_map.length; i++) {
				for (int j = 0; j < origin_map.length; j++) {
					map[i][j] = origin_map[i][j];
				}
			}
			simulation();
			return;
		} //

		first_line[depth] = true;
		dfs_firstLine(origin_map, depth + 1);
		first_line[depth] = false;
		dfs_firstLine(origin_map, depth + 1);

	}// dfs

	// 첫째행을 따라서 아래까지 쭈욱 실행해 봄
	static void simulation() {
		int count = 0;
		
		// 첫번째 행 입력받은 대로 조작
		for (int i = 0; i < 10; i++) {
			if (first_line[i]) {
				push(0, i);
				count++;
			}
		}
		
		// 나머지 열들은 첫째열에 종속되서 내려감
		for (int i = 0; i <= 8; i++) {
			for (int j = 0; j < map.length; j++) {
				if (map[i][j] == 'O') {
					push(i + 1, j);
					count++;
				}
			}
		}

		// 마지막 행을 검사하면서 만약 켜진 전구가 있다면 시뮬레이션 종료
		for (int j = 0; j < 10; j++) {
			if (map[9][j] == 'O')
				return;
		}

		// 마지막까지 다 꺼졌다? 이제 최소값을 업데이트
		ans = Math.min(ans, count);
	}// dfs

	static void push(int r, int c) {
		for (int i = 0; i < 5; i++) {
			int R = r + dr[i];
			int C = c + dc[i];
			if (R >= 0 && R < 10 && C >= 0 && C < 10) {
				if (map[R][C] == '#')
					map[R][C] = 'O';
				else if (map[R][C] == 'O')
					map[R][C] = '#';
			}
		}
	}

}