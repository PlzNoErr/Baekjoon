import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dc = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Sea[][] map = new Sea[4][4];
		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				int size = Integer.parseInt(st.nextToken());
				int direction = Integer.parseInt(st.nextToken()) - 1;
				map[i][j] = new Sea("fish", i, j, size, direction);
			}
		}
		max = map[0][0].size;
		map[0][0] = new Sea("shark", 0, 0, map[0][0].size, map[0][0].direction);
		dfs(map, 0, 0);
		System.out.println(max);

	}// main

	static void dfs(Sea[][] map, int r, int c) {
		// 물고기가 이동한다.
		map = fish_move(map);

		// 상어가 순차적으로 이동하면서 해당하게 되면 깊은 복사를 수행한뒤 다음 dfs를 소환한다.
		int k = 1;
		int direction = map[r][c].direction;
		int R = r + dr[direction] * k;
		int C = c + dc[direction] * k;

		while (0 <= R && R < 4 && 0 <= C && C < 4) {
			if (map[R][C] != null) {
				Sea[][] copy = new Sea[4][4];
				copy = deep_copy(map, copy);
				Sea temp = copy[R][C];
				copy[R][C] = copy[r][c];
				copy[r][c] = null;
				copy[R][C].direction = temp.direction;
				copy[R][C].size += temp.size;
				dfs(copy, R, C);
			}
			k++;
			R = r + dr[direction] * k;
			C = c + dc[direction] * k;
		} //
		max = Math.max(max, map[r][c].size);
	}//

	static Sea[][] deep_copy(Sea[][] map, Sea[][] copy) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (map[i][j] != null)
					copy[i][j] = new Sea(map[i][j].type, map[i][j].r, map[i][j].c, map[i][j].size, map[i][j].direction);
			}
		}
		return copy;
	}

	static Sea[][] fish_move(Sea[][] map) {
		// 크기가 작은 생선을 정렬하기 위한 list
		ArrayList<Sea> list = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (map[i][j] != null && map[i][j].type.equals("fish"))
					list.add(map[i][j]);
			}
		}

		Collections.sort(list, new Comparator<Sea>() {
			@Override
			public int compare(Sea e1, Sea e2) {
				return e1.size - e2.size;
			}
		});

		for (int k = 0; k < list.size(); k++) {
			Sea fish = list.get(k);
			for (int i = 0; i < 8; i++) {
				int R = fish.r + dr[(fish.direction + i) % 8];
				int C = fish.c + dc[(fish.direction + i) % 8];
				if (0 <= R && R < 4 && 0 <= C && C < 4) {
					if (map[R][C] == null) {
						map[R][C] = fish;
						map[R][C].direction = (fish.direction + i) % 8;
						map[fish.r][fish.c] = null;
						map[R][C].r = R;
						map[R][C].c = C;
						break;
					}
					if (map[R][C].type.equals("fish")) {
						map[fish.r][fish.c] = map[R][C];
						map[R][C] = fish;
						map[R][C].direction = (fish.direction + i) % 8;
						map[fish.r][fish.c].r = fish.r;
						map[fish.r][fish.c].c = fish.c;
						map[R][C].r = R;
						map[R][C].c = C;
						break;
					}
				}
			}
		} //
		return map;
	}//

	static class Sea {
		String type;
		int r, c, size, direction;

		public Sea(String type, int r, int c, int size, int direction) {
			super();
			this.type = type;
			this.r = r;
			this.c = c;
			this.size = size;
			this.direction = direction;
		}
	}//
}