import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[] dr = { 0, 0, -1, 1, -2, -2, -1, -1, 1, 1, 2, 2 };
	static int[] dc = { -1, 1, 0, 0, 1, -1, -2, 2, -2, 2, 1, -1 };
	static int[][] map;
	static boolean[][][] check;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int horse_limit = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		check = new boolean[horse_limit + 1][H][W];
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		if (W == 1 && H == 1) {
			System.out.println(0);
			return;
		}
		int answer = 0;
		Queue<Monkey> Q = new LinkedList<>();
		Q.add(new Monkey(0, 0, 0, 0));
		check[0][0][0] = true;
		while (!Q.isEmpty()) {
			Monkey monkey = Q.poll();
			if (monkey.r == H - 1 && monkey.c == W - 1) {
				answer = monkey.count;
				break;
			}
			int run = 4;
			if (monkey.horse < horse_limit)
				run = 12;
			for (int i = 0; i < run; i++) {
				int R = monkey.r + dr[i];
				int C = monkey.c + dc[i];
				if (0 <= R && R < H && 0 <= C && C < W) {
					if (i < 4 && !check[monkey.horse][R][C] && map[R][C] == 0) {
						check[monkey.horse][R][C] = true;
						Q.add(new Monkey(R, C, monkey.horse, monkey.count + 1));
					}
					if (i >= 4 && !check[monkey.horse + 1][R][C] && map[R][C] == 0) {
						check[monkey.horse + 1][R][C] = true;
						Q.add(new Monkey(R, C, monkey.horse + 1, monkey.count + 1));
					}
				}
			}
		} // bfs

		if (answer == 0) {
			System.out.println(-1);
			return;
		}
		System.out.println(answer);

	}// main

	static class Monkey {
		int r, c, horse, count;

		public Monkey(int r, int c, int horse, int count) {
			this.r = r;
			this.c = c;
			this.horse = horse;
			this.count = count;
		}
	}//
}
