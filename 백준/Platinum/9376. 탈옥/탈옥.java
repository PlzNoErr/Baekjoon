import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };
	static char[][] map;
	static int[][] dist;
	static int n;
	static int m;
	static final int INF = Integer.MAX_VALUE / 10;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		while (tc-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			// 핵심 아이디어는 밖에서 감옥으로 들어오는 제3자를 개입시키는 것이다.
			// 제 3자는 감옥 밖에 있으므로 감옥 주위를 빈칸으로 둘러주자.
			map = new char[n + 2][m + 2];
			
			// 감옥 내부의 맵을 입력받으면서 동시에 죄수의 위치도 기록한다.
			ArrayList<int[]> prisoner = new ArrayList<>();
			for (int i = 1; i < n + 1; i++) {
				String s = br.readLine();
				for (int j = 1; j < m + 1; j++) {
					map[i][j] = s.charAt(j - 1);
					if (map[i][j] == '$')
						prisoner.add(new int[] { i, j });
				}
			}

			// 외곽을 빈장소로 둘러주자.
			int t = 0;
			while (t <= n + 1) {
				for (int j = 0; j < m + 2; j++) {
					map[t][j] = '.';
				}
				t += n + 1;
			}
			t = 0;
			while (t <= m + 1) {
				for (int j = 0; j < n + 2; j++) {
					map[j][t] = '.';
				}
				t += m + 1;
			}
			
			// 3번의 다익스트라를 실행한다.
			// 제3자, 죄수1, 죄수2의 관점에서 시행하고 각각의 dist값을
			// 모두  record에 저장시킨다.
			// 저장된 record값에서 #(문)에 해당되는 좌표면
			// -2를 해준다. 왜냐하면 3명이 동시에 이용했기 떄문에 처음 한명만 열면 그만이기 때문이다.
			// 3명의 dist값이 합산된 수 중 최소값이 최소 돌파의 문의 개수가 된다.
			int[][] record = new int[n + 2][m + 2];
			dijkstra(new int[] { 0, 0 });
			for (int i = 0; i < n + 2; i++) {
				for (int j = 0; j < m + 2; j++) {
					record[i][j] += dist[i][j];
				}
			}

			dijkstra(prisoner.get(0));
			for (int i = 0; i < n + 2; i++) {
				for (int j = 0; j < m + 2; j++) {
					record[i][j] += dist[i][j];
				}
			}

			dijkstra(prisoner.get(1));
			for (int i = 0; i < n + 2; i++) {
				for (int j = 0; j < m + 2; j++) {
					record[i][j] += dist[i][j];
				}
			}

			for (int i = 0; i < n + 2; i++) {
				for (int j = 0; j < m + 2; j++) {
					if (map[i][j] == '#')
						record[i][j] -= 2;
				}
			}
			
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < n + 2; i++) {
				for (int j = 0; j < m + 2; j++) {
					min = Math.min(min, record[i][j]);
				}
			}
			System.out.println(min);

		}
	}// main

	static void dijkstra(int[] start) {
		dist = new int[n + 2][m + 2];
		// dist 초기화, 시작점 초기화
		for (int i = 0; i < n + 2; i++) {
			for (int j = 0; j < m + 2; j++) {
				dist[i][j] = INF;
			}
		}
		dist[start[0]][start[1]] = 0;
		PriorityQueue<Vt> PQ = new PriorityQueue<>(new Comparator<Vt>() {
			@Override
			public int compare(Vt e1, Vt e2) {
				return e1.cost - e2.cost;
			}
		});
		PQ.add(new Vt(start[0], start[1], 0));
		while (!PQ.isEmpty()) {
			Vt vt = PQ.poll();
			if (dist[vt.r][vt.c] < vt.cost)
				continue;
			// 해당 점점에서 시도해 볼 수 있는 경로는 4개이다.
			for (int i = 0; i < 4; i++) {
				int R = vt.r + dr[i];
				int C = vt.c + dc[i];
				// 해당 경로가 이동 가능할때
				if (R >= 0 && R < n + 2 && C >= 0 && C < m + 2 && map[R][C] != '*') {
					// 이동 가능한 경로가 문이면 비용이 추가된다.
					int RCcost = 0;
					if (map[R][C] == '#')
						RCcost = 1;
					if (dist[R][C] > dist[vt.r][vt.c] + RCcost) {
						// 비용 업데이트
						dist[R][C] = dist[vt.r][vt.c] + RCcost;
						PQ.add(new Vt(R, C, dist[R][C]));
					}
				}
			}
		}
	}// dijk

	static class Vt {
		int r, c, cost;

		public Vt(int r, int c, int cost) {
			this.r = r;
			this.c = c;
			this.cost = cost;
		}
	}//

}
