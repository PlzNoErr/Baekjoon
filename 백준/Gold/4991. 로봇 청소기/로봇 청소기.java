import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = { 0, 0, -1, 1 };
	static int[] dc = { -1, 1, 0, 0 };

	static int h, w;
	static char[][] map;
	static boolean[][] visit;
	static int[][] dist;
	static ArrayList<Node> list;

	static boolean[] check;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		here: while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());

			if (w == 0 && h == 0)
				break;

			map = new char[h][w];
			list = new ArrayList<>();

			for (int i = 0; i < h; i++) {
				map[i] = br.readLine().toCharArray();
				for (int j = 0; j < w; j++) {
					if (map[i][j] == 'o') {
						list.add(0, new Node(i, j, 0));
					}
					if (map[i][j] == '*') {
						list.add(new Node(i, j, 0));
					}
				}
			}

			// 먼저 외판원 순회로 각 정점(청소기, 쓰래기)에서 다른 정점으로의 거리를 모두 구한다.
			// 플로이드 워셜느낌이라고 생각하면 됨.
			dist = new int[list.size()][list.size()];

			// 맨 처음 청소기에서 거리를 구한 다음 못가는 정점이 있다면 그 테케는 pass
			bfs(list.get(0), 0);
			for (int i = 1; i < list.size(); i++) {
				if (dist[0][i] == 0) {
					sb.append(-1 + "\n");
					continue here;
				}
			}
			// 나머지 정점에서의 거리도 구해서 채워준다.
			for (int i = 1; i < list.size(); i++) {
				bfs(list.get(i), i);
			}

			// 이제 구해진 각 정점간의 조합을 순열을 통해서 뽑아서 그 값의 최소값을 구해준다.
			result = Integer.MAX_VALUE;
			check = new boolean[list.size()];
			check[0] = true;
			Permutation(0, 1, 0);
			sb.append(result + "\n");
		}
		System.out.println(sb);
	}// main

	static void bfs(Node node, int start) {
		visit = new boolean[h][w];
		Queue<Node> q = new LinkedList<>();
		q.offer(node);
		visit[node.r][node.c] = true;
		while (!q.isEmpty()) {
			Node next = q.poll();

			if (map[next.r][next.c] == '*') {
				for (int i = 1; i < list.size(); i++) {
					if (next.r == list.get(i).r && next.c == list.get(i).c) {
						dist[start][i] = next.move;
					}
				}
			}

			for (int i = 0; i < 4; i++) {
				int R = next.r + dr[i];
				int C = next.c + dc[i];

				if (R >= 0 && C >= 0 && R < h && C < w && map[R][C] != 'x' && !visit[R][C]) {
					visit[R][C] = true;
					q.offer(new Node(R, C, next.move + 1));
				}
			}
		}
	}//

	static void Permutation(int start, int cnt, int distance) {
		if (distance > result)
		return;
        
        if (cnt == list.size()) {
			result = Math.min(result, distance);
		}

		for (int i = 1; i < list.size(); i++) {
			if (!check[i]) {
				check[i] = true;
				Permutation(i, cnt + 1, distance + dist[start][i]);
				check[i] = false;
			}
		}
	}//

	static class Node {
		int r, c, move;

		Node(int r, int c, int move) {
			this.r = r;
			this.c = c;
			this.move = move;
		}
	}//
}