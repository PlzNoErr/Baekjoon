import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };
	static char[][] map;
	static boolean[][] visit;
	static boolean[][] visit_meet;
	static int H;
	static int W;
	static int start_r = -1;
	static int start_c;
	static int des_r;
	static int des_c;
	static Queue<int[]> meltQ;
	static Queue<int[]> meetQ;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		map = new char[H][W];
		for (int i = 0; i < H; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < W; j++) {
				if (map[i][j] == 'L' && start_r == -1) {
					start_r = i;
					start_c = j;
					map[i][j] = '.';
				}
				if (map[i][j] == 'L' && start_r != -1) {
					des_r = i;
					des_c = j;
					map[i][j] = '.';
				}
			}
		}
		int need_time = 0;
		visit = new boolean[H][W];
		visit_meet = new boolean[H][W];
		// 녹일 얼음을 Q에 집어 넣자.
		meltQ = new LinkedList<>();
		meetQ = new LinkedList<>();
		meetQ.add(new int[] { start_r, start_c });
		visit_meet[start_r][start_c] = true;

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (map[i][j] == '.' && !visit[i][j]) {
					visit[i][j] = true;
					bfs_melt(i, j);
				}
			}
		}

		// 백조 시뮬레이션 시작
		while (true) {
			// 얼음을 녹인다.
			// 시작점에서 도착점으로 도달 가능한지 bfs를 실행한다.
			if (bfs_check_can_meet())
				break;

			int size = meltQ.size();
			for (int sz = 0; sz < size; sz++) {
				int[] vt = meltQ.poll();
				map[vt[0]][vt[1]] = '.';
				for (int i = 0; i < 4; i++) {
					int R = vt[0] + dr[i];
					int C = vt[1] + dc[i];
					if (0 <= R && R < H && 0 <= C && C < W && !visit[R][C] && map[R][C] == 'X') {
						visit[R][C] = true;
						meltQ.add(new int[] { R, C });
					}
				}
			}

			need_time++;
		} //
		System.out.println(need_time);

	}// main

	static void bfs_melt(int r, int c) {
		Queue<int[]> Q = new LinkedList<>();
		Q.add(new int[] { r, c });
		while (!Q.isEmpty()) {
			int[] vt = Q.poll();
			for (int i = 0; i < 4; i++) {
				int R = vt[0] + dr[i];
				int C = vt[1] + dc[i];
				if (0 <= R && R < H && 0 <= C && C < W && !visit[R][C]) {
					if (map[R][C] == '.') {
						visit[R][C] = true;
						Q.add(new int[] { R, C });
					}
					if (map[R][C] == 'X') {
						visit[R][C] = true;
						meltQ.add(new int[] { R, C });
					}
				}
			}
		}
	}// bfs

	static boolean bfs_check_can_meet() {
		Queue<int[]> Q = new LinkedList<>();
		while (!meetQ.isEmpty()) {
			Q.add(meetQ.poll());
		}
		while (!Q.isEmpty()) {
			int[] vt = Q.poll();

			if (vt[0] == des_r && vt[1] == des_c)
				return true;

			for (int i = 0; i < 4; i++) {
				int R = vt[0] + dr[i];
				int C = vt[1] + dc[i];
				if (0 <= R && R < H && 0 <= C && C < W && !visit_meet[R][C]) {
					if (map[R][C] == '.') {
						visit_meet[R][C] = true;
						Q.add(new int[] { R, C });
					}
					if (map[R][C] == 'X') {
						visit_meet[R][C] = true;
						meetQ.add(new int[] { R, C });
					}
				}
			}
		}
		return false;
	}//
}