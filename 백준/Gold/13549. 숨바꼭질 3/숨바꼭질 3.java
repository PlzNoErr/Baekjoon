import java.util.LinkedList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		sc.close();
		int max = 100000;
		int[] Arr = new int[max + 1];
		boolean[] check = new boolean[max + 1];

		if (m <= n) {
			System.out.println(n - m);
			return;
		}

		LinkedList<Integer> Q = new LinkedList<>();
		Q.add(n);
		check[n] = true;
		while (!Q.isEmpty()) {
			int v = Q.pollFirst();
			int a = v + 1;
			int b = v - 1;
			int c = 2 * v;

			if (c <= max && !check[c] && c < 2 * m) {
				Arr[c] = Arr[v];
				check[c] = true;
				Q.addFirst(c);
			}
			if (a <= m && !check[a]) {
				Arr[a] = Arr[v] + 1;
				check[a] = true;
				Q.addLast(a);
			}
			if (0 <= b && !check[b]) {
				Arr[b] = Arr[v] + 1;
				check[b] = true;
				Q.addLast(b);
			}
			if (a == m || b == m || c == m) {
				check[m] = false;
				break;
			}
		}
		
		
		System.out.println(Arr[m]);

	}// main
}
