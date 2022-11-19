import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int v = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		parent = new int[v];
		for (int i = 0; i < parent.length; i++) {
			parent[i] = i;
		}
		int already_cicle = 1;
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (find(a) != find(b)) {
				union(a, b);
			}
			else {
				System.out.println(already_cicle);
				return;
			}
			already_cicle++;
		}
		System.out.println(0);

	}// main

	static int find(int x) {
		if (parent[x] == x) {
			return x;
		} else {
			return parent[x] = find(parent[x]);
		}
	}

	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a > b)
			parent[b] = a;
		if (a < b)
			parent[a] = b;
	}

}
