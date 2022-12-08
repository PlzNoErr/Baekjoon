import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Flower[] Arr = new Flower[n];
		for (int i = 0; i < Arr.length; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int startM = Integer.parseInt(st.nextToken());
			int startD = Integer.parseInt(st.nextToken());
			int endM = Integer.parseInt(st.nextToken());
			int endD = Integer.parseInt(st.nextToken());
			Arr[i] = new Flower(startM, startD, endM, endD);
		}

		Arrays.sort(Arr, new Comparator<Flower>() {
			@Override
			public int compare(Flower e1, Flower e2) {
				if (e1.startM == e2.startM) {
					return e1.startD - e2.startD;
				} else
					return e1.startM - e2.startM;
			}
		});

		PriorityQueue<Flower> PQ = new PriorityQueue<>(new Comparator<Flower>() {
			@Override
			public int compare(Flower e1, Flower e2) {
				if (e1.endM == e2.endM) {
					return e2.endD - e1.endD;
				} else
					return e2.endM - e1.endM;
			}
		});
		int count = 0;
		int idx = 0;
		int sM = 3;
		int sD = 1;
		while (true) {
			for (int i = idx; i < Arr.length; i++) {
				if (Arr[i].startM > sM || (Arr[i].startM == sM && Arr[i].startD > sD))
					break;
				PQ.add(Arr[i]);
				idx++;
			}

			if (PQ.isEmpty()) {
				count = -1;
				break;
			}
			count++;
			if (PQ.peek().endM > 11) {
				break;
			} else if (PQ.peek().endM < sM || (PQ.peek().endM == sM && PQ.peek().endD <= sD)) {
				count = -1;
				break;

			} else {
				sM = PQ.peek().endM;
				sD = PQ.peek().endD;
				PQ.poll();
			}
		}
		if (count != -1)
			System.out.println(count);
		else
			System.out.println(0);

	}//

	static class Flower {
		int startM, startD, endM, endD;

		public Flower(int startM, int startD, int endM, int endD) {
			this.startM = startM;
			this.startD = startD;
			this.endM = endM;
			this.endD = endD;
		}
	}
}
