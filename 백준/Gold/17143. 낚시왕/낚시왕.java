import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	// 1번은 북쪽, 2번은 남쪽, 3번은 동쪽, 4번은 서쪽
	static int[] dr = { 0, -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 0, 1, -1 };
	static Shark[][] map;
	static Queue<Shark> Q;
	static int H;
	static int W;
	static int total_shark;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		// 상어의 개수
		int M = Integer.parseInt(st.nextToken());
		map = new Shark[H + 1][W + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int speed = Integer.parseInt(st.nextToken());
			int direction = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());
			map[r][c] = new Shark(r, c, speed, direction, size);
		}

		total_shark = 0;
		int fishman = 0;
		// 낚시 시뮬레이터 시작
		while (fishman < W) {
			// fishman이 홍어를.. 아니 상어를 잡으러 이동한다.
			fishman++;

			// 상어사냥!
			getShark(fishman);

			// 마지막 회차는 상어이동 연산할 필요 X
			if (fishman == W) {
				break;
			}

			// 상어이동 시뮬레이터 시작!
			// 일단 상어들이 현재 정보를 모두 Q로 옮겨 담음 다음에 map은 null로 초기화 시킨다.
			Q = new LinkedList<>();
			for (int i = 1; i <= H; i++) {
				for (int j = 1; j <= W; j++) {
					if (map[i][j] != null) {
						Q.add(map[i][j]);
						map[i][j] = null;
					}
				}
			}

			while (!Q.isEmpty()) {
				Shark shark = Q.poll();
				int move_cnt = 0;
				// 상어의 이동
				while (move_cnt < shark.speed) {
					int R = shark.r + dr[shark.direction];
					int C = shark.c + dc[shark.direction];
					if (1 <= R && R <= H && 1 <= C && C <= W) {
						shark.r = R;
						shark.c = C;
						move_cnt++;
						continue;
					}
					else {
						if (shark.direction == 1 || shark.direction == 3) {
							shark.direction++;
							shark.r += dr[shark.direction];
							shark.c += dc[shark.direction];
							move_cnt++;
							continue;
						}
						if (shark.direction == 2 || shark.direction == 4) {
							shark.direction--;
							shark.r += dr[shark.direction];
							shark.c += dc[shark.direction];
							move_cnt++;
							continue;
						}
					}
				}//

				// 어떤 상어가 잡아 먹히고 아닐지 정해준다.
				if (map[shark.r][shark.c] == null)
					map[shark.r][shark.c] = shark;
				else {
					if (shark.size > map[shark.r][shark.c].size)
						map[shark.r][shark.c] = shark;
				}
			} // 전체 상어의 이동 완료
		} // 낚시 시뮬레이션
		System.out.println(total_shark);
	}// main

	static void getShark(int fishman) {
		// fishman이 행을 위에서 부터 검사하면서 가장 가까운놈 하나 걸리면 잡아버린다.
		for (int i = 1; i <= H; i++) {
			if (map[i][fishman] != null) {
				total_shark += map[i][fishman].size;
				map[i][fishman] = null;
				return;
			}
		}
	}//

	static class Shark {
		int r, c, speed, direction, size;

		public Shark(int r, int c, int speed, int direction, int size) {
			this.r = r;
			this.c = c;
			this.speed = speed;
			this.direction = direction;
			this.size = size;
		}
	}//
}