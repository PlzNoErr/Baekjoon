import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int[] Parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 정점의 개수
		int v = Integer.parseInt(st.nextToken());


		// 신들의 xy좌표를 받아주자. 정점 숫자가 1부터 시작이니까 비어있는 인스턴스 하나 넣어주자.
		ArrayList<double[]> list = new ArrayList<>();
		list.add(new double[] {});

		for (int i = 1; i <= v; i++) {
			st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			list.add(new double[] { x, y });
		}

		Parent = new int[v + 1];
		for (int i = 1; i <= v; i++) {
			Parent[i] = i;
		}

		// 각 점간의 거리를 계산한 다음에 Node의 형태로 치환해서 간선정보를 힙에 집어넣는다.
		PriorityQueue<Node> PQ = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node e1, Node e2) {
				// compare 함수 태생이 return 값이 int형으로 강제되다보니 실수값 연산에서
                // 차이가 작아지면 재대로 정렬이 안되는 문제가 생기는데 이때는 그냥 깡으로
                // 1000 곱하게 만들어서 부동소수점 문제를 해결하자.
                return (int) ((e1.cost - e2.cost)*1000);
			}
		});

		for (int i = 1; i < v; i++) {
			for (int j = i + 1; j <= v; j++) {
				double[] e1 = list.get(i);
				double[] e2 = list.get(j);
				PQ.add(new Node(i, j, distance(e1, e2)));
			}
		}

		double min_cost = 0;
		while (!PQ.isEmpty()) {
			Node node = PQ.poll();
			if (find(node.from) != find(node.to)) {
				union(node.to, node.from);
				min_cost += node.cost;
			}
		}

		System.out.println(String.format("%.2f", min_cost));

	}// main

	static double distance(double[] e1, double[] e2) {
		return Math.sqrt(Math.pow(e1[0] - e2[0], 2) + Math.pow(e1[1] - e2[1], 2));
	}

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
		int from, to;
		double cost;

		public Node(int from, int to, double cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

	}// Node


}
