import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	final static int INF = Integer.MAX_VALUE / 10;
	static ArrayList<ArrayList<Node>> list;
	static int[][] dist;
	static int total_cost;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while (tc-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 정점의 수 v, 간선의 수 e
			int v = Integer.parseInt(st.nextToken());
			total_cost = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			list = new ArrayList<>();
			for (int i = 0; i <= v; i++) {
				list.add(new ArrayList<>());
			}

			for (int i = 0; i < e; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				int time = Integer.parseInt(st.nextToken());
				list.get(x).add(new Node(y, cost, time));
			}
			// 최단경로를 알아내기 위해서 다익스트라 시행
			dist = new int[total_cost + 1][v + 1];
			dijkstra(1);

			int min = Integer.MAX_VALUE;
			for (int i = 0; i <= total_cost; i++) {
				min = Math.min(min, dist[i][v]);
			}

			if (min == INF)
				System.out.println("Poor KCM");
			else
				System.out.println(min);

		} // tc
	}// main

	static void dijkstra(int start) {
		// dist배열을 초기화 & 시작지점 0으로 초기화
		for (int i = 0; i <= total_cost; i++) {
			Arrays.fill(dist[i], INF);
		}
		dist[0][start] = 0;

		PriorityQueue<Node> PQ = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node e1, Node e2) {
				return e1.time - e2.time;
			}
		});
		PQ.add(new Node(start, 0, 0));
		while (!PQ.isEmpty()) {
			Node node = PQ.poll();

			// check배열은 사용하지 마라. 이렇게 value비교로 거르는게 더 맞는 방식이다
			if (dist[node.cost][node.vt] < node.time)
				continue;

			for (int i = 0; i < list.get(node.vt).size(); i++) {
				Node target = list.get(node.vt).get(i);
				// 더 빠른 경로를 추종하는 건 좋지만 그 경로의 비용이 총 비용을 넘어서서는 안된다.
				if (node.cost + target.cost <= total_cost
						&& dist[node.cost + target.cost][target.vt] > dist[node.cost][node.vt] + target.time) {
					dist[node.cost + target.cost][target.vt] = dist[node.cost][node.vt] + target.time;
					PQ.add(new Node(target.vt, node.cost + target.cost, dist[node.cost + target.cost][target.vt]));
				}
			}
		}
	}//

	static class Node {
		int vt, cost, time;

		public Node(int vt, int cost, int time) {
			this.vt = vt;
			this.cost = cost;
			this.time = time;
		}
	}//

}