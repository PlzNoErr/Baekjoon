import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	final static long INF = Long.MAX_VALUE;
	static ArrayList<ArrayList<Node>> list;
	static long[][] dist;
	static int v;
	static int K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 정점의 수 v, 간선의 수 e
		v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		list = new ArrayList<>();
		for (int i = 0; i <= v; i++) {
			list.add(new ArrayList<>());
		}
		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			list.get(x).add(new Node(y, cost, 0));
			list.get(y).add(new Node(x, cost, 0));
		}
		// 최단경로를 알아내기 위해서 다익스트라 시행
		dist = new long[K + 1][v + 1];
		dijkstra(1);
		long min = INF;
		for (int i = 0; i <= K; i++) {
			min = Math.min(min, dist[i][v]);
		}
		System.out.println(min);

	}// main

	static void dijkstra(int start) {
		// dist배열을 초기화 & 시작지점 0으로 초기화
		for (int i = 0; i <= K; i++) {
			for (int j = 0; j <= v; j++) {
				dist[i][j] = INF;
			}
		}
		dist[0][start] = 0;

		PriorityQueue<Node> PQ = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node e1, Node e2) {
				return (int) (e1.cost - e2.cost);
			}
		});
		PQ.add(new Node(start, 0, 0));
		while (!PQ.isEmpty()) {
			Node node = PQ.poll();

			if (dist[node.coverd][node.vt] < node.cost)
				continue;

			for (int i = 0; i < list.get(node.vt).size(); i++) {
				Node target = list.get(node.vt).get(i);
				if (node.coverd < K && dist[node.coverd + 1][target.vt] > dist[node.coverd][node.vt]) {
					dist[node.coverd + 1][target.vt] = dist[node.coverd][node.vt];
					PQ.add(new Node(target.vt, dist[node.coverd + 1][target.vt], node.coverd + 1));
				}
				if (dist[node.coverd][target.vt] > dist[node.coverd][node.vt] + target.cost) {
					dist[node.coverd][target.vt] = dist[node.coverd][node.vt] + target.cost;
					PQ.add(new Node(target.vt, dist[node.coverd][target.vt], node.coverd));
				}
			}
		}
	}//

	static class Node {
		int vt;
		long cost;
		int coverd;

		public Node(int vt, long cost, int covered) {
			this.vt = vt;
			this.cost = cost;
			this.coverd = covered;
		}
	}//

}