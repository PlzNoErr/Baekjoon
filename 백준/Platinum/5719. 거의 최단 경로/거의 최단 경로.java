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

	final static int INF = Integer.MAX_VALUE / 10;
	static ArrayList<ArrayList<Node>> list;
	static ArrayList<ArrayList<Node>> shoted_list;
	static int[] dist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 정점의 수 n, 간선의 수 v
			int v = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			// 테스트 케이스 종료 조건
			if (v == 0 && e == 0)
				break;

			st = new StringTokenizer(br.readLine());
			// 시작점 & 도착점
			int start = Integer.parseInt(st.nextToken());
			int arrive = Integer.parseInt(st.nextToken());

			// 간선 정보를 저장할 리스트를 생성
			// 간선 번호가 0번부터 시작하기 때문에 신경쓰자
			// 경로 역추적으로 최단경로에 들어오는 간선들을 지워줘야 되기 때문에 2개의 리스트를 만들어야 한다
			// 하나는 "내가 쏘는 리스트", 하나는 "내가 받는 리스트"
			list = new ArrayList<>();
			shoted_list = new ArrayList<>();
			for (int i = 0; i < v; i++) {
				list.add(new ArrayList<>());
				shoted_list.add(new ArrayList<>());
			}
			for (int i = 0; i < e; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				// x정점이 y정점으로 노드를 "쏘는" 것을 기록
				list.get(x).add(new Node(y, cost));
				// y정점이 x정점에게 노드를 "받는" 것을 기록
				shoted_list.get(y).add(new Node(x, cost));
			}
			// 최단경로를 알아내기 위해서 다익스트라 시행
			dist = new int[v];
			dijkstra(start);
			// 최단거리가 나왔으니 이제 역추적을 하면서 해당 경로를 list에서 지워주자
			// 최단거리를 달성하는 경로는 여럿이 있을 수 있기 때문에 Q로 하나하나 따져봐야 한다
			// check 배열도 만들어야 한다. 이미 지워버린 경로를 중복으로 따라가지 않도록
			Queue<Integer> Q = new LinkedList<>();
			boolean[] check = new boolean[v];
			Q.add(arrive);
			while (!Q.isEmpty()) {
				int vt = Q.poll();
				// 도착지부터 역으로 역추적에 들어간다. 미리 만들어 두었던 "받는 리스트"에서
				// 도착지로 어떤 경로들이 들어왔는지 역으로 검사한다
				// 들어오는 경로의 최단거리 dist값 + 현재 vt로 오는 cost가 도착지의 최소거리 dist값과
				// 일치한다면 이는 해당 경로가 "최단경로" 중 하나임을 의미한다
				// 고로 받는 리스트를 역으로 검사하면서 최단거리로 연결되는 정점은 Q에 넣어준 뒤 그 노드는 지워준다
				// 리스트를 앞에서부터 검사하면서 remove 메소드 쓰면 음.. 알죠? 무조건 뒤에서 부터 검사하면서 remove 해야됩니다
				for (int i = shoted_list.get(vt).size() - 1; i >= 0; i--) {
					// 최단 경로를 발견했다. Q에 그 노드를 따라서 역추적 하도록 담아 준다 이후 그 노드를 지운다
					if (!check[vt] && dist[vt] == dist[shoted_list.get(vt).get(i).vt] + shoted_list.get(vt).get(i).cost) {
						Q.add(shoted_list.get(vt).get(i).vt);
						shoted_list.get(vt).remove(i);
					}
				}
				// 해당 검사연산이 모두 끝나고 나서야 비로소 중복을 기록해 주자
				check[vt] = true;
			} // Q
				// 지금까지는 "받는 노드"를 초기화한 것이기 때문에 이제 "쏘는 노드"에 기록을 덮어씌워준다.
			list = new ArrayList<>();
			for (int i = 0; i < v; i++) {
				list.add(new ArrayList<>());
			}
			for (int i = 0; i < shoted_list.size(); i++) {
				for (int j = 0; j < shoted_list.get(i).size(); j++) {
					Node node = shoted_list.get(i).get(j);
					list.get(node.vt).add(new Node(i, node.cost));
				}
			}
			// 이제 최단경로들을 모두 지워 주었으니 "거의 최단경로"를 구하기 위해서 다시 다익스트라를 돌린다
			dijkstra(start);
			
			// INF값을 띄운다면 해당 경로로 도달할 수 없다는 뜻이다
			if (dist[arrive] > 200000000)
				sb.append(-1 + "\n");
			else
				sb.append(dist[arrive] + "\n");

		} // tc
		System.out.println(sb.toString());
	}// main

	static void dijkstra(int start) {
		// dist배열을 초기화 & 시작지점 0으로 초기화
		for (int i = 0; i < dist.length; i++) {
			dist[i] = INF;
		}
		dist[start] = 0;

		PriorityQueue<Node> PQ = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node e1, Node e2) {
				return e1.cost - e2.cost;
			}
		});
		PQ.add(new Node(start, 0));
		while (!PQ.isEmpty()) {
			Node node = PQ.poll();

			// check배열은 사용하지 마라. 이렇게 cost비교로 거르는게 더 맞는 방식이다
			if (dist[node.vt] < node.cost)
				continue;

			for (int i = 0; i < list.get(node.vt).size(); i++) {
				Node target = list.get(node.vt).get(i);
				if (dist[target.vt] > dist[node.vt] + target.cost) {
					dist[target.vt] = dist[node.vt] + target.cost;
					PQ.add(new Node(target.vt, dist[target.vt]));
				}
			}
		}
	}//

	static class Node {
		int vt, cost;

		public Node(int vt, int cost) {
			this.vt = vt;
			this.cost = cost;
		}
	}//

}