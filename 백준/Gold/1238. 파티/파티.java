import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int v;
	static int party;
	static int[] dist;
	static final int INF = 99999999;
	static ArrayList<ArrayList<Node>> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		v = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		party = Integer.parseInt(st.nextToken());

		// 간선 정보를 저장할 리스트
		list = new ArrayList<>();
		for (int i = 0; i <= v; i++) {
			list.add(new ArrayList<>());
		}

		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			list.get(a).add(new Node(b, cost));
		}

		int[] cost_record = new int[v + 1];
		dijkstra(party);
		cost_record = dist.clone();
		for (int i = 1; i <= v; i++) {
			if (i == party)
				continue;
			dijkstra(i);
			cost_record[i] += dist[party];
		}
		Arrays.sort(cost_record);
		System.out.println(cost_record[cost_record.length - 2]);

	}

	static void dijkstra(int start) {
		// 최단거리를 저장할 배열 & 초기화
		dist = new int[v + 1];
		Arrays.fill(dist, INF);
		dist[start] = 0;
		// 중복검사 방지용 체크
		boolean[] check = new boolean[v + 1];
		// 최소비용 순으로 노드들을 정렬한다.
		PriorityQueue<Node> PQ = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node e1, Node e2) {
				return e1.cost - e2.cost;
			}
		});
		PQ.add(new Node(start, 0));

		while (!PQ.isEmpty()) {
			Node node = PQ.poll();
			if (check[node.untilnow])
				continue;
			check[node.untilnow] = true;
			for (int i = 0; i < list.get(node.untilnow).size(); i++) {
				Node want_togo = list.get(node.untilnow).get(i);
				if (dist[want_togo.untilnow] > dist[node.untilnow] + want_togo.cost) {
					dist[want_togo.untilnow] = dist[node.untilnow] + want_togo.cost;
					PQ.add(new Node(want_togo.untilnow, dist[want_togo.untilnow]));
				}
			}
		}
	}//

	static class Node {
		int untilnow, cost;

		public Node(int untilnow, int cost) {
			this.untilnow = untilnow;
			this.cost = cost;
		}

	}

}