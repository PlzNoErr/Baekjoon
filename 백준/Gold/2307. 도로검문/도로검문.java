import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int[] dist;
	static int[] path;
	static ArrayList<ArrayList<Node>> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 정점, 간선의 개수
		StringTokenizer st = new StringTokenizer(br.readLine());
		int v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		// 각 정점에서의 간선의 정보를 저장해둘 리스트 생성 및 최단비용 저장할 배열 생성.
		dist = new int[v + 1];
		list = new ArrayList<>();
		for (int i = 0; i <= v; i++) {
			list.add(new ArrayList<>());
		}
		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			list.get(x).add(new Node(y, cost));
			list.get(y).add(new Node(x, cost));
		}
		// 1 -> v 까지의 어느 정점을 지나왔는지 기록으로 남긴다.
		path = new int[v + 1];
		dijkstra(1);
		int shortest_cost = dist[v];

		// 최단 경로들을 하나씩 막아보면서 어떻게 최단거리가 바뀌는지 기록을 남긴다.
		// 그 중 가장 긴 친구를 남겨야 한다.
		int max_cost = 0;
		for (int i = v; i > 0; i = path[i]) {
			blocked_dijkstra(1, path[i], i);
			max_cost = Math.max(max_cost, dist[v]);
		}
		if (max_cost - shortest_cost > 200000000) {
			System.out.println(-1);
			return;
		}
		System.out.println(max_cost - shortest_cost);

	}// main

	static void dijkstra(int start) {
		// dist 배열을 초기화 하고 시작지점의 비용을 0으로 바꾼다.
		int INF = Integer.MAX_VALUE / 10;
		for (int i = 0; i < dist.length; i++) {
			dist[i] = INF;
		}
		dist[start] = 0;
		// 중복을 체크할 배열을 생성한다.
		boolean[] check = new boolean[dist.length];
		// 각 정점에서의 최소값을 구해줄 PQ를 생성한다.
		PriorityQueue<Node> PQ = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node e1, Node e2) {
				return e1.cost - e2.cost;
			}
		});
		// 시작 노드를 PQ에 넣어준다.
		PQ.add(new Node(start, 0));
		while (!PQ.isEmpty()) {
			Node node = PQ.poll();
			// PQ안의 해당 정점의 거리 최적화를 이미 했는가?
			if (check[node.to])
				continue;
			check[node.to] = true;

			// PQ에서 골라진 정점에서 이동 가능한 다른 정점들의 비용을 업데이트 한다.
			for (int i = 0; i < list.get(node.to).size(); i++) {
				Node possible_node = list.get(node.to).get(i);
				if (dist[possible_node.to] > dist[node.to] + possible_node.cost) {
					dist[possible_node.to] = dist[node.to] + possible_node.cost;
					// 업데이트가 일어났으니 PQ에 넣은뒤 다시 최적화할 가능성을 고려한다.
					PQ.add(new Node(possible_node.to, dist[possible_node.to]));
					// 업데이트가 일어날 시 노드의 기록을 남긴다.
					path[possible_node.to] = node.to;
				}
			}
		}
	}//

	static void blocked_dijkstra(int start, int from, int to) {
		// dist 배열을 초기화 하고 시작지점의 비용을 0으로 바꾼다.
		int INF = Integer.MAX_VALUE / 10;
		for (int i = 0; i < dist.length; i++) {
			dist[i] = INF;
		}
		dist[start] = 0;
		// 중복을 체크할 배열을 생성한다.
		boolean[] check = new boolean[dist.length];
		// 각 정점에서의 최소값을 구해줄 PQ를 생성한다.
		PriorityQueue<Node> PQ = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node e1, Node e2) {
				return e1.cost - e2.cost;
			}
		});
		// 시작 노드를 PQ에 넣어준다.
		PQ.add(new Node(start, 0));
		while (!PQ.isEmpty()) {
			Node node = PQ.poll();
			// PQ안의 해당 정점의 거리 최적화를 이미 했는가?
			if (check[node.to])
				continue;
			check[node.to] = true;

			// PQ에서 골라진 정점에서 이동 가능한 다른 정점들의 비용을 업데이트 한다.
			for (int i = 0; i < list.get(node.to).size(); i++) {
				Node possible_node = list.get(node.to).get(i);
				// 해당 경로는 봉쇄되었다.
				if (node.to == from && possible_node.to == to)
					continue;
				if (dist[possible_node.to] > dist[node.to] + possible_node.cost) {
					dist[possible_node.to] = dist[node.to] + possible_node.cost;
					// 업데이트가 일어났으니 PQ에 넣은뒤 다시 최적화할 가능성을 고려한다.
					PQ.add(new Node(possible_node.to, dist[possible_node.to]));
				}
			}
		}
	}//

	static class Node {
		int to, cost;

		public Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}//
}