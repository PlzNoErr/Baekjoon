import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static final int INF = Integer.MAX_VALUE;
	static int[] dist;
	static int v;
	static int e;
	static ArrayList<ArrayList<int[]>> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 정점의 개수 v, 간선의 개수 e
		StringTokenizer st = new StringTokenizer(br.readLine());
		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		int start_v = Integer.parseInt(br.readLine());

		// 간선 정보를 담을 리스트 생성.
		list = new ArrayList<>();
		// 리스트안에 들어갈 리스트들의 인스턴스를 생성해줌.
		for (int i = 0; i <= v; i++) {
			list.add(new ArrayList<int[]>());
		}
		// 유향 그래프이다.
		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			// [0]에는 목적지 정점, [1]에는 비용을 담는다.
			list.get(s).add(new int[] { t, cost });
		}

		// 시작 지점 s에서 이동가능한 모든 간선들의 비용을(기준따라 최소 or 최대) 표시할 배열.
		dist = new int[v + 1];
		Arrays.fill(dist, INF);

		dijkstra(start_v);
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < dist.length; i++) {
			if(dist[i]==INF) {
				sb.append("INF\n");
				continue;
			}
			sb.append(dist[i]+"\n");
		}
		
		System.out.println(sb.toString());
	}

	private static void dijkstra(int start_v) {
		// 해당 점정의 최적비용 검사가 끝났음을 확인할 배열을 만든다.
		boolean[] check = new boolean[v + 1];
		// 입력할 간선들의 정보가 배열로 되어있기 때문에 어떻게 정렬할지 설정해 줘야한다.
		PriorityQueue<int[]> PQ = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] e1, int[] e2) {
				// 최저비용 우선으로 정렬.
				return e1[1] - e2[1];
			}
		});

		// 시작노드와 비용을 넣어준다. 자기자신에서 자기자신 가는건 비용이 없다.
		PQ.add(new int[] { start_v, 0 });
		// 자기 자신에게 가는 비용을 0으로 초기화.
		dist[start_v] = 0;

		while (!PQ.isEmpty()) {
			int[] picked = PQ.poll();

			// 이미 해당 경로의 비용최적화가 끝났다면 넘어간다.
			if (check[picked[0]])
				continue;
			// 아직 최적화 하지 않았다면 이제 선택되었다.
			check[picked[0]] = true;

			// 꺼낸 노드와 연결되어 있는 간선들을 차례로 불러와서 최적화 과정을 수행한다.
			for (int i = 0; i < list.get(picked[0]).size(); i++) {
				// 리스트 안에서 선택된 노드에 연결되어 있는 값들을 저장하고 있는 리스트를 불러온다.
				int[] node_info = list.get(picked[0]).get(i);
				// 기존에 입력되어 있는 도달 비용이 (최적화 하기 위해 선택된 간선의 총비용 + 거기서 이동비용) 보다 클때 초기화 해준다.
				if (dist[node_info[0]] > dist[picked[0]] + node_info[1]) {
					dist[node_info[0]] = dist[picked[0]] + node_info[1];
					PQ.add(new int[] { node_info[0], dist[node_info[0]] });
				}
			}
		}
	}// dijkstra

}