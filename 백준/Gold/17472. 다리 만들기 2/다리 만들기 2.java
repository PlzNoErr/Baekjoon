import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

	static int[] dr = { 0, 1, -1, 0, 0 };
	static int[] dc = { 1, 0, 0, -1, 0 };
	static int n;
	static int m;
	static int[][] map;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 맵의 상태를 입력받는다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 단계1 dfs를 사용해 이제 섬을 구분해서 칠해준다. 2부터 시작하도록 섬을 채워준다.
		// 이 친구는 맵을 칠할때도 사용되므로 섬의 개수는 변수값-2를 해야 한다는 점을 명심하자.
		int num_island = 2;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 1) {
					dfs_paint_island(i, j, num_island);
					num_island++;
				}
			}
		}

		// 단계2에서는 섬들을 구분해서 칠해주는 것이 끝났으니 다른 섬으로 이동할때 확인되는 모든 간선을 리스트에 집어 넣어준다.
		// dfs를 사용해서 간선을 구하자. 그리디적으로 생각해 보건데 왼쪽 위쪽으로는 탐색할 필요가 없다. 어차피 누군가의 아래쪽 오른쪽
		// 다리의 반대편에 연결될 것이기 때문이다.

		// 간선들의 정보를 저장해줄 리스트
		ArrayList<int[]> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] != 0) {
					// 오른쪽으로 탐색
					int[] temp1 = dfs_find_bridge(i, j, 0);
					// 꽝이 아니라면
					if (temp1[0] != -1) {
						list.add(temp1);
					}
					// 아래쪽으로 탐색
					int[] temp2 = dfs_find_bridge(i, j, 1);
					// 꽝이 아니라면
					if (temp2[0] != -1) {
						list.add(temp2);
					}
				}
			}
		}
		// 마지막 단계로 크루스칼을 구현해서 (모든섬이 연결 && 최소 다리길이)가 달성되도록 구하면 된다!
		// 굳이 크루스칼 안써도 다익스트라나 이분탐색으로도 해답을 구할 수 있지만 여기서는 크루스칼을 구현하는 연습을 해보자.
		// 다리 길이가 가장 짧은 순으로 정렬해주자
		Collections.sort(list, new Comparator<int[]>() {
			@Override
			public int compare(int[] e1, int[] e2) {
				return e1[2] - e2[2];
			}
		});

		int min_cost = 0;
		// 대표(root)를 기록할 유니온 파인드 배열이다.
		// 섬은 2~ 시작하므로 num_island를 넣으면 딱 맞는다.
		parent = new int[num_island];

		// 스스로 root로 하는 상호배타 집합으로 기록
		for (int i = 2; i < num_island; i++) {
			parent[i] = i;
		}

		// 낮은 비용부터 크루스칼 알고리즘 진행
		int connected = 0;
		for (int i = 0; i < list.size(); i++) {
			// 사이클이 존재하지 않는 경우에만 간선을 선택한다.
			if (find(list.get(i)[0]) != find(list.get(i)[1])) {
				union(list.get(i)[0], list.get(i)[1]);
				min_cost += list.get(i)[2];
				connected++;
			}
		}
		
		// 연결된 간선들의 수가 섬의 갯수 -1 이 못된다면 모든 섬이 연결되지 않았다는 뜻이다. 고로 -1 출력
		// 아니라면 모든섬이 연결된 것이니 cost값을 출력하면 된다.
		if (connected == (num_island - 2) - 1) {
			System.out.println(min_cost);
		} else {
			System.out.println(-1);
		}

	}// main

	static void dfs_paint_island(int r, int c, int num_island) {
		//4방 탐색이 아니라 4방 + 1인 이유는 자기자신 혼자서 섬인 1블록 섬을 체크해야 해서 그렇다.
		for (int i = 0; i < 5; i++) {
			int R = r + dr[i];
			int C = c + dc[i];
			if (R >= 0 && R < n && C >= 0 && C < m && map[R][C] == 1) {
				map[R][C] = num_island;
				dfs_paint_island(R, C, num_island);
			}
		}
	}// dfs

	static int[] dfs_find_bridge(int r, int c, int dir) {
		int length = 0;
		int start_isLand = map[r][c];
		int R = r + dr[dir];
		int C = c + dc[dir];

		while (true) {
			// 탐색칸이 바다라면
			if (R >= 0 && R < n && C >= 0 && C < m && map[R][C] == 0) {
				length++;
				R += dr[dir];
				C += dc[dir];
				continue;
			}
			// 탐색결과 꽝, 섬을 만나지 못한고 범위를 벗어났거나 바다 경계선에 있는 블록이 아니던가
			if (R < 0 || R >= n || C < 0 || C >= m || map[R][C] == start_isLand) {
				return new int[] { -1 };
			}
			// 다리에 연결된 섬을 찾았다!
			if (map[R][C] != 0 && map[R][C] != start_isLand) {
				// 연결된 다리 찾은 건 좋은데 연결된 길이가 2를 넘지 않으면 무효처리 된다.
				// 처음 시작섬[0], 도착 섬[1], 다리의 길이[2]
				if (length > 1) {
					return new int[] { start_isLand, map[R][C], length };
				} else {
					return new int[] { -1 };
				}
			}
		}
	}// dfs2

	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a > b) {
			parent[a] = b;
		} else {
			parent[b] = a;
		}
	}

	static int find(int x) {
		if (parent[x] == x)
			return x;
		else
			return find(parent[x]);
	}

}