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
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[][] jem = new int[n][2];
		int[] beg = new int[k];
		for (int i = 0; i < jem.length; i++) {
			st = new StringTokenizer(br.readLine());
			jem[i][0] = Integer.parseInt(st.nextToken());
			jem[i][1] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < k; i++) {
			beg[i] = Integer.parseInt(br.readLine());
		}

		// 보석을 오름차순 무게순으로 정렬. 같은 무게일 시 가치가 높은 순으로 정렬
		Arrays.sort(jem, new Comparator<int[]>() {
			@Override
			public int compare(int[] e1, int[] e2) {
				if (e1[0] == e2[0]) {
					return e2[1] - e1[1];
				} else {
					return e1[0] - e2[0];
				}
			}
		});
		// 가방도 무게순으로 정렬
		Arrays.sort(beg);

		// 우선순위 Q를 활용해서 시간복잡도를 줄여보자.
		long total_value = 0;
		// 아직 PQ에 안들어간 보석의 idx
		int idx = 0;

		PriorityQueue<Integer> PQ = new PriorityQueue<>(Comparator.reverseOrder());
		for (int i = 0; i < beg.length; i++) {
			while (idx < n && jem[idx][0] <= beg[i]) {
				PQ.add(jem[idx++][1]);
			}
			if (!PQ.isEmpty())
				total_value += PQ.poll();
		}

		System.out.println(total_value);

	}// main
}