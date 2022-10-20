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
		int last_day = 0;
		int[][] work = new int[n][2];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int due = Integer.parseInt(st.nextToken());
			int score = Integer.parseInt(st.nextToken());
			work[i][0] = due;
			work[i][1] = score;
			last_day = Math.max(last_day, due);
		}
		Arrays.sort(work, new Comparator<int[]>() {
			@Override
			public int compare(int[] e1, int[] e2) {
				return e2[0] - e1[0];
			}
		});

		PriorityQueue<int[]> PQ = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] e1, int[] e2) {
				return e2[1] - e1[1];
			}
		});

		int total = 0;
		int idx = 0;
		int bf_idx = 0;
		while (last_day > 0) {
			for (int i = idx; i < work.length; i++) {
				if (work[i][0] < last_day) {
					break;
				}
				bf_idx++;
			}
			for (int i = idx; i < bf_idx; i++) {
				PQ.add(work[i]);
			}
			idx = bf_idx;

			if (!PQ.isEmpty())
				total += PQ.poll()[1];

			last_day--;
		}
		System.out.println(total);

	}// main

}
