import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		PriorityQueue<Road> PQ = new PriorityQueue<>(new Comparator<Road>() {
			@Override
			public int compare(Road e1, Road e2) {
				return e1.cost - e2.cost;
			}
		});
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			PQ.add(new Road(from, to, cost));
		}

		// 크루스칼
		parent = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}
		int max = Integer.MIN_VALUE;
		int total_cost = 0;
		while (!PQ.isEmpty()) {
			Road road = PQ.poll();
			if (find(road.from) != find(road.to)) {
				union(road.from, road.to);
				max = Math.max(max, road.cost);
				total_cost += road.cost;
			}
		}

		System.out.println(total_cost - max);

	}// main

	static int find(int x) {
		if (parent[x] == x)
			return x;
		else
			return find(parent[x]);
	}

	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a > b)
			parent[b] = a;
		if (a < b)
			parent[a] = b;
	}

	static class Road {
		int from, to, cost;

		public Road(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

	}
}
