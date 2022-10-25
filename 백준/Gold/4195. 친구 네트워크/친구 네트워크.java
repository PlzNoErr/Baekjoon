import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

	static int[] parent;
	static int[] level;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		while (tc-- > 0) {
			int n = Integer.parseInt(br.readLine());
			HashMap<String, Integer> map = new HashMap<>();
			// 부모배열 초기화
			parent = new int[n * 2];
			level = new int[n * 2];
			for (int i = 0; i < n * 2; i++) {
				parent[i] = i;
				level[i] = 1;
			}

			int idx = 0;
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				String a = st.nextToken();
				String b = st.nextToken();
				if (!map.containsKey(a)) {
					map.put(a, idx++);
				}
				if (!map.containsKey(b)) {
					map.put(b, idx++);
				}
				sb.append(union(map.get(a), map.get(b))+"\n");
			}
		} // while
		System.out.println(sb.toString());
	}// main

	static int find(int x) {
		if (parent[x] == x) {
			return x;
		} else {
			return parent[x] = find(parent[x]);
		}
	}// find

	static int union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a != b) {
			parent[b] = a;
			level[a] += level[b];
		}
		return level[a];

	}// union

}
