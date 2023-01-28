import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

	static ArrayList<Integer>[] a;
	static ArrayList<Integer> dfs_order = new ArrayList<>();
	static boolean[] check;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		a = new ArrayList[n];
		check = new boolean[n];
		int[] b = new int[n];
		int[] order = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = new ArrayList<>();
		}
		
		StringTokenizer st;
		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;
			a[u].add(v);
			a[v].add(u);
		}
		
        st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			b[i] = Integer.parseInt(st.nextToken()) - 1;
			order[b[i]] = i;
		}
		
		for (int i = 0; i < n; i++) {
			Collections.sort(a[i], new Comparator<Integer>() {
				public int compare(Integer u, Integer v) {
					if (order[u] < order[v]) {
						return -1;
					} else {
						return 1;
					}
				}
			});
		}
		dfs(0);
		boolean ok = true;
		for (int i = 0; i < n; i++) {
			if (dfs_order.get(i) != b[i]) {
				ok = false;
			}
		}
		if (ok) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}
	}// main

	static void dfs(int x) {
		if (check[x])
			return;
		dfs_order.add(x);
		check[x] = true;
		for (int y : a[x]) {
			dfs(y);
		}
	}// dfs
}