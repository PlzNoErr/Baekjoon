import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main {

	static int max = 0;
	static char[] alph;
	static boolean[] check;
	static int cc;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		char[][] word = new char[n][];
		HashSet<Character> set = new HashSet<>();
		HashSet<Character> set2;
		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			s = s.substring(4, s.length() - 4);
			int ccc = 0;
			set2 = new HashSet<>();
			for (int j = 0; j < s.length(); j++) {
				if (s.charAt(j) != 'a' && s.charAt(j) != 'n' && s.charAt(j) != 't' && s.charAt(j) != 'i'
						&& s.charAt(j) != 'c') {
					set2.add(s.charAt(j));
					set.add(s.charAt(j));
					ccc++;
				}
			}
			word[i] = new char[set2.size()];
			int zz = 0;
			Iterator<Character> iter = set2.iterator();
			while (iter.hasNext()) {
				word[i][zz++] = iter.next();
			}
		}

		int z = 0;
		alph = new char[set.size()];
		check = new boolean[set.size()];
		Iterator<Character> iter = set.iterator();
		while (iter.hasNext()) {
			alph[z++] = iter.next();
		}
		if (k < 5) {
			System.out.println(0);
			return;
		}

		int K = 0;
		if (check.length >= k - 5) {
			K = k - 5;
		} else {
			K = check.length;
		}

		char[] print = new char[K];


		dfs(n, K, print, word, 0, 0);
		System.out.println(max);

	}// main

	static void dfs(int n, int K, char[] print, char[][] word, int depth, int num) {
		if (depth == K) {
			int count = 0;
			for (int i = 0; i < n; i++) {
				cc = 0;
				for (int k = 0; k < K; k++) {
					for (int j = 0; j < word[i].length; j++) {
						if (print[k] == word[i][j]) {
							cc++;
							break;
						}
					}
				}
				if (cc >= word[i].length) {
					count++;
				}
			}
			max = Math.max(max, count);
			return;
		}

		for (int i = num; i < check.length; i++) {
			if (!check[i]) {
				check[i] = true;
				print[depth] = alph[i];
				dfs(n, K, print, word, depth + 1, i);
				check[i] = false;
			}
		}

	}// dfs
}