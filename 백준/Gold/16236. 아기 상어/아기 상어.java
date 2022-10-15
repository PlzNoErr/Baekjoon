import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[] dr = { 0, 0, -1, 1 };
	static int[] dc = { -1, 1, 0, 0 };
	static int[][] map;
	static boolean[][][] visit;
	static int n;
	static int size;
	static int time = 0;
	static int EAT = 2;
	static int shark_r;
	static int shark_c;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		// 아기상어의 최초위치
		Shark shark = null;
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					shark = new Shark(i, j, 2, 0);
					shark_r = i;
					shark_c = j;
					map[i][j] = 0;
				}
			}
		}
		visit = new boolean[n * n * 2][n][n];

		// 루프 순회
		while (true) {
			// 현재 상어의 size를 업데이트
			size = 2;
			for (int i = 0; i < 500; i++) {
				if (EAT < i * (i + 1) / 2 + 1) {
					size = i;
					break;
				}
			}

			// 상어가 지금부터 먹어나갈 경우의 수를 모두 담는 리스트를 만든다.
			ArrayList<int[]> list = new ArrayList<>();

			// bfs문을 돌면서 처음으로 뭔가를 먹을때는 리스트에 넣어준다. 아닐경우 그냥 Q에 넣고 계속 돌린다.
			Queue<Shark> Q = new LinkedList<>();
			Q.add(new Shark(shark_r, shark_c, EAT, time));
			visit[EAT][shark_r][shark_c] = true;

			while (!Q.isEmpty()) {
				Shark s = Q.poll();
				for (int i = 0; i < 4; i++) {
					int R = s.r + dr[i];
					int C = s.c + dc[i];
					if (R >= 0 && R < n && C >= 0 && C < n && (map[R][C] == 0 || map[R][C] == size)
							&& !visit[s.eat][R][C]) {
						visit[s.eat][R][C] = true;
						Q.add(new Shark(R, C, s.eat, s.time + 1));
					}

					if (R >= 0 && R < n && C >= 0 && C < n && map[R][C] > 0 && map[R][C] < size
							&& !visit[s.eat + 1][R][C]) {
						visit[s.eat + 1][R][C] = true;
						list.add(new int[] { s.time + 1, R, C, s.eat+1});
					}
				}
			} // bfs
			
			//먹은 생선이 없을경우 연산 종료
			if (list.size() == 0) {
				break;
			}

			// 먹은 생선들 중에서 뭐가 가장 가까운 생선인지 확인 후 현재 상어의 위치를 업데이트.
			ArrayList<int[]> result = check(list);
			shark_r = result.get(0)[1];
			shark_c = result.get(0)[2];
			EAT = result.get(0)[3];
			time = result.get(0)[0];
			map[shark_r][shark_c] = 0;

		} // 탐색 while
		System.out.println(time);
	}// main

	// 먹은 물고기들을 순서대로 정렬해서 뭘 우선적으로 먹은처리 할지 골라준다.
	static ArrayList<int[]> check(ArrayList<int[]> fish) {
		ArrayList<int[]> temp = fish;
		ArrayList<int[]> list = new ArrayList<>();

		if (temp.size() == 1) {
			return temp;
		}

		if (temp.size() != 0) {
			// 거리값을 우선으로 정렬
			Collections.sort(temp, new Comparator<int[]>() {
				@Override
				public int compare(int[] e1, int[] e2) {
					return e1[0] - e2[0];
				}
			});

			// 거리값이 가장 짧은 애들만 list에 옮겨담음.
			for (int i = 0; i < temp.size() - 1; i++) {
				list.add(temp.get(i));
				if (temp.get(i)[0] != temp.get(i + 1)[0]) {
					break;
				}
				if (i == temp.size() - 2) {
					list.add(temp.get(temp.size() - 1));
				}
			}

			// r 우선 c 이후 순으로 list 값들을 재정렬 함.
			Collections.sort(list, new Comparator<int[]>() {
				@Override
				public int compare(int[] e1, int[] e2) {
					if (e1[1] == e2[1]) {
						return e1[2] - e2[2];
					} else {
						return e1[1] - e2[1];
					}
				}
			});
		} // if

		return list;
	}

	static class Shark {
		int r, c, eat, time;

		Shark(int r, int c, int eat, int time) {
			super();
			this.r = r;
			this.c = c;
			this.eat = eat;
			this.time = time;
		}

	}
}