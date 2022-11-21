
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] Arr = new int[n];
		for (int i = 0; i < n; i++) {
			Arr[i] = sc.nextInt();
		}
		Arrays.sort(Arr);

		if (n != 2) {
			int k = Uclid(Arr[1] - Arr[0], Arr[2] - Arr[1]);

			for (int i = 2; i < n - 1; i++) {
				k = Uclid(k, Arr[i + 1] - Arr[i]);
			}
			for (int i = 2; i <= k; i++) {
				if (k % i == 0) {
					System.out.print(i + " ");
				}
			}
		}
		else {
			int k = Arr[1]-Arr[0];
			for (int i = 2; i <= k; i++) {
				if (k % i == 0) {
					System.out.print(i + " ");
				}
			}
		}
	}// main

	static int Uclid(int a, int b) {
		int r = 1;
		while (r != 0) {
			r = a % b;
			a = b;
			b = r;
		}
		return a;
	}

}