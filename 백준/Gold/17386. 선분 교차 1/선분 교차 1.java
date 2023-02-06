import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long x1 = sc.nextLong();
		long y1 = sc.nextLong();
		long x2 = sc.nextLong();
		long y2 = sc.nextLong();
		long x3 = sc.nextLong();
		long y3 = sc.nextLong();
		long x4 = sc.nextLong();
		long y4 = sc.nextLong();
		sc.close();

		if (CCW(x1, x2, x3, y1, y2, y3) * CCW(x1, x2, x4, y1, y2, y4) < 0
				&& CCW(x3, x4, x1, y3, y4, y1) * CCW(x3, x4, x2, y3, y4, y2) < 0) {
			System.out.println(1);
			return;
		}
		System.out.println(0);
	}

	static long CCW(long x1, long x2, long x3, long y1, long y2, long y3) {
		long ans = x1 * y2 + x2 * y3 + x3 * y1 - y1 * x2 - y2 * x3 - y3 * x1;
		if (ans == 0) {
			return 0;
		} else if (ans > 0) {
			return 1;
		} else {
			return -1;
		}
	}

}