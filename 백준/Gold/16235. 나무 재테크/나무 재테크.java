import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int[] dr = { 1, 1, 1, -1, -1, -1, 0, 0 };
	static int[] dc = { 1, -1, 0, -1, 1, 0, 1, -1 };
	static int[][] map;
	static int[][] add_map;
	static ArrayList[][] tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		// 나무 구매
		int m = Integer.parseInt(st.nextToken());
		// year년에 나무 몇게?
		int target_year = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		add_map = new int[n][n];
		tree = new ArrayList[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				tree[i][j] = new ArrayList<>();
			}
		}

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				add_map[i][j] = Integer.parseInt(st.nextToken());
				map[i][j] = 5;
			}
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int age = Integer.parseInt(st.nextToken());
			tree[r][c].add(age);
		}
		
		// 봄,여름,가을,겨울 시뮬레이션 돌아간다.
		int year = 0;
		while (year < target_year) {
			year++;

			// 봄,여름 과정
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					tree_aging(i, j, tree[i][j]);
				}
			}

			// 가을, 겨울 과정
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					tree_make_child(i, j, tree[i][j]);
					map[i][j] += add_map[i][j];
				}
			}
			
		}
		int answer = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				answer += tree[i][j].size();
			}
		}
		System.out.println(answer);
	}// main

	// 봄 여름 과정을 한번에 수행한다.
	static void tree_aging(int r, int c, ArrayList<Integer> list) {
		int idx = 0;
		for (int i = idx; i < list.size(); i++) {
			if (map[r][c] >= list.get(i)) {
				int temp = list.get(i);
				map[r][c] -= temp;
				list.set(i, temp + 1);
				idx++;
			} else {
				break;
			}
		}
		for (int i = list.size()-1; i >= idx; i--) {
			map[r][c] += list.get(i) / 2;
			list.remove(i);
			
		}
	}//
	
	static void tree_make_child(int r, int c, ArrayList<Integer> list) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) % 5 == 0) {
				for (int j = 0; j < 8; j++) {
					int R = r + dr[j];
					int C = c + dc[j];
					if (0 <= R && R < map.length && 0 <= C && C < map.length) {
						tree[R][C].add(0, 1);
					}
				}
			}
		}
	}//

}