import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		// 위상정렬에서 "들어오는 간선"의 개수를 기록해준다.
		int[] in_degree = new int[v + 1];
		// 간선의 정보를 입력할 list.
		ArrayList<ArrayList<Integer>> list = new ArrayList<>();
		for (int i = 0; i <= v; i++) {
			list.add(new ArrayList<>());
		}
		// 위상정렬은 DAG에서만 구현이 가능하다. y가 간선을 받는 입장이므로 y에게 얼마나 간선이 들어가는지 기록을 해준다.
		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			list.get(x).add(y);
			in_degree[y]++;
		}

		// BFS를 활용해서 위상정렬을 출력해주자.
		StringBuilder sb = new StringBuilder();
		Queue<Integer> Q = new LinkedList<>();
		// in_degree의 개수가 0인 정점들을 먼저 Q에 집어 넣는다.
		for (int i = 1; i <= v; i++) {
			if (in_degree[i] == 0)
				Q.add(i);
		}
		while (!Q.isEmpty()) {
			int V = Q.poll();
			if (in_degree[V] == 0)
				sb.append(V + " ");
			for (int i = 0; i < list.get(V).size(); i++) {
				in_degree[list.get(V).get(i)]--;
				if (in_degree[list.get(V).get(i)] == 0)
					Q.add(list.get(V).get(i));
			}
		}
		System.out.println(sb.toString());
	}// main

}