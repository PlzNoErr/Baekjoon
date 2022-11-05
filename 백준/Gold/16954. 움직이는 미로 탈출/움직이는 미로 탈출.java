import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class Main {

	static int[] dr = { 0, 0, -1, 1, 1, 1, -1, -1, 0 };
	static int[] dc = { -1, 1, 0, 0, 1, -1, 1, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[][] map = new char[8][8];
		boolean[][][] check = new boolean[64][8][8];
		ArrayList<int[]> walls = new ArrayList<>();
		for (int i = 0; i < 8; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < 8; j++) {
				if (map[i][j] == '#')
					walls.add(new int[] { i, j });
			}
		}

		LinkedList<R> Q = new LinkedList<>();
		check[0][7][0] = true;
		Q.add(new R(7, 0, 0));
		int wall = 1;
		while (!Q.isEmpty()) {
			R record = Q.poll();
			if (record.r == 0 && record.c == 7) {
				System.out.println(1);
				return;
			}
			if (record.move == wall) {
				for (int i = walls.size() - 1; i >= 0; i--) {
					if (walls.get(i)[0] < 7) {
						map[walls.get(i)[0] + 1][walls.get(i)[1]] = '#';
						map[walls.get(i)[0]][walls.get(i)[1]] = '.';
						walls.get(i)[0] += 1;
					} else {
						map[walls.get(i)[0]][walls.get(i)[1]] = '.';
					}
				}
				wall++;
			}

			if (map[record.r][record.c] == '#') {
				continue;
			}

			for (int i = 0; i < 9; i++) {
				int R = record.r + dr[i];
				int C = record.c + dc[i];
				if (0 <= R && R < 8 && 0 <= C && C < 8 && !check[record.move + 1][R][C] && map[R][C] != '#') {
					check[record.move + 1][R][C] = true;
					Q.add(new R(R, C, record.move + 1));
				}
			}

		} // while
		System.out.println(0);
	}// main

	static class R {
		int r, c, move;

		R(int r, int c, int move) {
			this.r = r;
			this.c = c;
			this.move = move;
		}

	}

}