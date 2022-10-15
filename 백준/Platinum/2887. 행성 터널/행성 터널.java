import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Planet[] planets = new Planet[n];
		for (int i = 0; i < planets.length; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			long x = Long.parseLong(st.nextToken());
			long y = Long.parseLong(st.nextToken());
			long z = Long.parseLong(st.nextToken());
			planets[i] = new Planet(x, y, z, i);
		}

		PriorityQueue<Vt> PQ = new PriorityQueue<>(new Comparator<Vt>() {
			@Override
			public int compare(Vt e1, Vt e2) {
				return (int) (e1.cost - e2.cost);
			}
		});
		
		// 간선의 수를 줄이기 위한 과정
		ArrayList<Planet> listX = new ArrayList<>(Arrays.asList(planets));
		listX.sort((e1, e2) -> (int) (e1.x - e2.x));
		ArrayList<Planet> listY = new ArrayList<>(Arrays.asList(planets));
		listY.sort((e1, e2) -> (int) (e1.y - e2.y));
		ArrayList<Planet> listZ = new ArrayList<>(Arrays.asList(planets));
		listZ.sort((e1, e2) -> (int) (e1.z - e2.z));

		// 간선 생성 및 입력
		for (int i = 0; i < n - 1; i++) {
			Planet planet1 = listX.get(i);
			Planet planet2 = listX.get(i + 1);
			PQ.add(new Vt(planet1.idx, planet2.idx, Math.abs(planet1.x - planet2.x)));
			planet1 = listY.get(i);
			planet2 = listY.get(i + 1);
			PQ.add(new Vt(planet1.idx, planet2.idx, Math.abs(planet1.y - planet2.y)));
			planet1 = listZ.get(i);
			planet2 = listZ.get(i + 1);
			PQ.add(new Vt(planet1.idx, planet2.idx, Math.abs(planet1.z - planet2.z)));
		}

		// 부모 배열 초기화
		parent = new int[n];
		for (int i = 0; i < parent.length; i++) {
			parent[i] = i;
		}

		long total_cost = 0;
		int count = 0;
		while (!PQ.isEmpty()) {
			Vt vt = PQ.poll();
			if (find(vt.vt1) != find(vt.vt2)) {
				union(vt.vt1, vt.vt2);
				total_cost += vt.cost;
				count++;
			}
			if (count == n - 1)
				break;
		}

		System.out.println(total_cost);

	}// main

	static int find(int x) {
		if (x == parent[x])
			return x;
		else
			return parent[x] = find(parent[x]);
	}//

	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a > b)
			parent[b] = a;
		if (a < b)
			parent[a] = b;
	}//

	static class Planet {
		long x, y, z;
		int idx;

		public Planet(long x, long y, long z, int idx) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.idx = idx;
		}
	}//

	static class Vt {
		int vt1, vt2;
		long cost;

		public Vt(int vt1, int vt2, long cost) {
			this.vt1 = vt1;
			this.vt2 = vt2;
			this.cost = cost;
		}
	}//
}