import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		if ((A + B + C) % 3 != 0) {
			System.out.println(0);
			return;
		}
		
		Queue<Stone> Q = new LinkedList<>();
		boolean[][] check = new boolean[1501][1501];
		
		Q.add(new Stone(A, B, C));
		check[A][B] = true;
		check[B][A] = true;
		check[A][C] = true;
		check[C][A] = true;
		check[B][C] = true;
		check[C][B] = true;
		
		while (!Q.isEmpty()) {
			Stone stone = Q.poll();
			int a = stone.a;
			int b = stone.b;
			int c = stone.c;

			if (a == b && b == c) {
				System.out.println(1);
				return;
			}

			if (a != b) {
				int na = a > b ? a - b : a * 2;
				int nb = a > b ? b * 2 : b - a;

				if (!check[na][nb]) {
					Q.add(new Stone(na, nb, c));
					check[na][nb] = true;
					check[nb][na] = true;
					check[na][c] = true;
					check[c][na] = true;
					check[nb][c] = true;
					check[c][nb] = true;
				}
			}

			if (a != c) {
				int na = a > c ? a - c : a * 2;
				int nc = a > c ? c * 2 : c - a;

				if (!check[na][nc]) {
					Q.add(new Stone(na, b, nc));
					check[na][nc] = true;
					check[nc][na] = true;
					check[na][b] = true;
					check[b][na] = true;
					check[b][nc] = true;
					check[nc][b] = true;
				}
			}

			if (b != c) {
				int nb = b > c ? b - c : b * 2;
				int nc = b > c ? c * 2 : c - b;

				if (!check[nb][nc]) {
					Q.add(new Stone(a, nb, nc));
					check[nb][nc] = true;
					check[nc][nb] = true;
					check[nb][a] = true;
					check[a][nb] = true;
					check[a][nc] = true;
					check[nc][a] = true;
				}
			}

		}//bfs
		
		System.out.println(0);
	}


	static class Stone {
		int a, b, c;

		public Stone(int a, int b, int c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}
	}
}