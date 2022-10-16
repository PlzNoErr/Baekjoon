import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int v;
	static int[] population;
	static boolean[] section;
	static ArrayList<ArrayList<Integer>> graph;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		v = Integer.parseInt(br.readLine());
		population = new int[v + 1];
		section = new boolean[v + 1];
		graph = new ArrayList<>();
		for (int i = 0; i <= v; i++) {
			graph.add(new ArrayList<>());
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i < population.length; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 1; i <= v; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			while (num-- > 0) {
				int to = Integer.parseInt(st.nextToken());
				graph.get(i).add(to);
			}
		}
		Combination(1, 0);
		
		if (min == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(min);

	}//

	static void Combination(int idx, int depth) {
		if (depth == v - 1) {
			simulation();
			return;
		}
		for (int i = idx; i <= v; i++) {
			if (!section[i]) {
				section[i] = true;
				Combination(i + 1, depth + 1);
				simulation();
				section[i] = false;
			}
		}
	}

	static void simulation() {
		boolean[] visit = new boolean[v + 1];
		int idx = 0;
		for (int i = 1; i <= v; i++) {
			if (section[i]) {
				idx = i;
				break;
			}
		}

		Queue<Integer> Q = new LinkedList<>();
		Q.add(idx);
		visit[idx] = true;
		while (!Q.isEmpty()) {
			int vt = Q.poll();
			for (int i = 0; i < graph.get(vt).size(); i++) {
				int to = graph.get(vt).get(i);
				if (section[to] && !visit[to]) {
					visit[to] = true;
					Q.add(to);
				}
			}
		} //

		for (int i = 1; i <= v; i++) {
			if (visit[i] != section[i])
				return;
		}
		
		visit = new boolean[v + 1];
		idx = 0;
		for (int i = 1; i <= v; i++) {
			if (!section[i]) {
				idx = i;
				break;
			}
		}

		Q = new LinkedList<>();
		Q.add(idx);
		visit[idx] = true;
		while (!Q.isEmpty()) {
			int vt = Q.poll();
			for (int i = 0; i < graph.get(vt).size(); i++) {
				int to = graph.get(vt).get(i);
				if (!section[to] && !visit[to]) {
					visit[to] = true;
					Q.add(to);
				}
			}
		} //

		for (int i = 1; i <= v; i++) {
			if (visit[i] == section[i])
				return;
		}
		int p1 = 0;
		int p2 = 0;
		for (int i = 1; i <= v; i++) {
			if (section[i])
				p1 += population[i];
			else
				p2 += population[i];
		}
		min = Math.min(min, Math.abs(p1 - p2));
	}//

}