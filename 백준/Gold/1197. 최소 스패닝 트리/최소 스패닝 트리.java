import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int[] Parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 정점의 개수 v, 간선의 개수 e
		StringTokenizer st = new StringTokenizer(br.readLine());
		int v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());

		// 노드의 비용이 제일 저렴한 순으로 힙 정렬
		PriorityQueue<Node> PQ = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node e1, Node e2) {
				return e1.cost - e2.cost;
			}
		});

		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			PQ.add(new Node(from, to, cost));
		}

		// 유니온 파인드
		Parent = new int[v + 1];
		for (int i = 1; i < Parent.length; i++) {
			Parent[i] = i;
		}

		// 크루스칼 알고리즘을 활용해서 최소 비용을 도출하자.
		int min_cost = 0;
		while (!PQ.isEmpty()) {
			Node node = PQ.poll();
			if (find(node.from) != find(node.to)) {
				union(node.to, node.from);
				min_cost += node.cost;
			}
		}
		
		System.out.println(min_cost);

	}// main

	static int find(int x) {
		if (Parent[x] == x) {
			return x;
		} else {
			return find(Parent[x]);
		}
	}// find

	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a > b) {
			Parent[a] = b;
		} else {
			Parent[b] = a;
		}
	}// union

	static class Node {
		int from, to, cost;

		public Node(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

	}// Node
}