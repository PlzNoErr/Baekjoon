import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[] dist;
	static int[] path;
	static ArrayList<ArrayList<Node>> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int v = Integer.parseInt(br.readLine());
		int e = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		for (int i = 0; i <= v; i++) {
			list.add(new ArrayList<>());
		}
		ArrayList<ArrayList<Node>> shooted_list = new ArrayList<>();
		for (int i = 0; i <= v; i++) {
			shooted_list.add(new ArrayList<>());
		}

		for (int i = 0; i < e; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			// 최대 시간을 찾는거기 때문에 -로 곱해주고 다익스트라 활용.
			list.get(x).add(new Node(y, -1 * cost));
			// 받는 경로도 저장.
			shooted_list.get(y).add(new Node(x, -1 * cost));
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int arrive = Integer.parseInt(st.nextToken());

		// 경로를 역산하기 위해 경로 기록용 배열을 만들어 주자. 동시에 최장거리를 기록하기 위한 배열도 만들자.
		dist = new int[v + 1];
		path = new int[v + 1];
		// 다다다다다잌스트라~
		dijkstra(start);

		boolean[] check = new boolean[v + 1];
		int pass_road = 0;
		Queue<Integer> Q = new LinkedList<>();
		Q.add(arrive);
		while (!Q.isEmpty()) {
			int city = Q.poll();
			if (check[city])
				continue;
			check[city] = true;
			for (int i = 0; i < shooted_list.get(city).size(); i++) {
				if (dist[city] == dist[shooted_list.get(city).get(i).to] + shooted_list.get(city).get(i).cost) {
					pass_road++;
					Q.add(shooted_list.get(city).get(i).to);
				}
			}
		}

		System.out.println(Math.abs(dist[arrive]));
		System.out.println(pass_road);
	}// main

	static void dijkstra(int start) {
		// 원래 INF로 dist를 초기화 해줘야 되는데 간선 비용을 음수처리 해버려서 INF = 0 으로 자동 초기화가 되었다.
		// 물론 시작점은 문제 조건에 맞춰서 제일 작은 값으로 바꿔줘야 한다.
		dist[start] = 0;
		PriorityQueue<Node> PQ = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node e1, Node e2) {
				return e1.cost - e2.cost;
			}
		});
		PQ.add(new Node(start, dist[start]));
		while (!PQ.isEmpty()) {
			Node node = PQ.poll();
			// check박스는 집어 치워라 이제부터 이 방식으로 간다.
			if (dist[node.to] > node.cost)
				continue;
			for (int i = 0; i < list.get(node.to).size(); i++) {
				Node willgo = list.get(node.to).get(i);
				if (dist[willgo.to] > dist[node.to] + willgo.cost) {
					dist[willgo.to] = dist[node.to] + willgo.cost;
					PQ.add(new Node(willgo.to, dist[willgo.to]));
					path[willgo.to] = node.to;
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