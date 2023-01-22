import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long s = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		long limit = 1000000000L;
		if (s == t) {
			System.out.println(0);
			return;
		}

		HashSet<Long> set = new HashSet<>();
		Queue<Path> Q = new LinkedList<>();
		Q.add(new Path(s, ""));
		while (!Q.isEmpty()) {
			Path path = Q.poll();
			if (path.num == t) {
				System.out.println(path.s);
				return;
			}

			if (!set.contains(path.num * path.num) && limit >= path.num * path.num && path.num * path.num >= 0) {
				Q.add(new Path(path.num * path.num, path.s + "*"));
				set.add(path.num * path.num);
			}

			if (!set.contains(path.num * 2) && limit >= path.num * 2 && path.num * 2 >= 0) {
				Q.add(new Path(path.num * 2, path.s + "+"));
				set.add(path.num * 2);
			}

			if (!set.contains(path.num - path.num)) {
				Q.add(new Path(path.num - path.num, path.s + "-"));
				set.add(path.num - path.num);
			}

			if (path.num != 0 && !set.contains(path.num / path.num) && path.num != 1) {
				Q.add(new Path(path.num / path.num, path.s + "/"));
				set.add(path.num / path.num);
			}
		}
		System.out.println(-1);
	}// main

	static class Path {
		long num;
		String s = "";

		Path(long num, String s) {
			this.num = num;
			this.s = s;
		}
	}
}