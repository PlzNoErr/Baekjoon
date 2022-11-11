import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static boolean ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		while (tc-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			ArrayList<Integer>[] graph = new ArrayList[n + 1];
			for (int i = 0; i <= n; i++) {
				graph[i] = new ArrayList<>();
			}
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				graph[x].add(y);
				graph[y].add(x);
			}

			boolean[] check = new boolean[n + 1];
			int[] group = new int[n + 1];
			ans = true;
			for (int i = 1; i <= n; i++) {
				if (!check[i]) {
					if (group[i] == 0) {
						group[i] = 1;
					}
					dfs(i, graph, check, group, 0);
				}
			}
			if (ans) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		} // tc
	}// main

	static void dfs(int start, ArrayList<Integer>[] graph, boolean[] check, int[] group, int before) {
		if (group[start] == 0) {
			if (group[before] == 1) {
				group[start] = 2;
			}
			if (group[before] == 2) {
				group[start] = 1;
			}
		}

		if (group[before] == group[start]) {
			ans = false;
			return;
		}

		if (check[start]) {
			return;
		}

		check[start] = true;
		for (int i : graph[start]) {
			dfs(i, graph, check, group, start);
		}

	}// dfs
}
