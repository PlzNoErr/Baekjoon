import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int max = 0;
	static int node;
	static boolean[] check;
	static ArrayList[] tree;

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		if(n==1) {
			System.out.println(0);
			return;
		}
		tree = new ArrayList[n + 1];
		check = new boolean[n + 1];
		for (int i = 1; i <= n; i++) {
			tree[i] = new ArrayList<int[]>();
		}

		StringTokenizer st;
		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			tree[x].add(new int[] { y, value });
			tree[y].add(new int[] { x, value });
		}

		dfs(1, 0);
		check = new boolean[n + 1];
		dfs(node, 0);

		System.out.println(max);

	}// main

	static void dfs(int x, int len) {
		if (len > max) {
			max = len;
			node = x;
		}
		check[x] = true;

		for (int i = 0; i < tree[x].size(); i++) {
			int[] arr = (int[]) tree[x].get(i);
			if (!check[arr[0]]) {
				dfs(arr[0], arr[1] + len);
				check[arr[0]] = true;
			}
		}
	}

}
