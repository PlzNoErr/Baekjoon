import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		PriorityQueue<Test> PQ = new PriorityQueue<>(new Comparator<Test>() {
			@Override
			public int compare(Test e1, Test e2) {
				return e2.ramen - e1.ramen;
			}
		});
		ArrayList<Test> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int deadline = Integer.parseInt(st.nextToken());
			int ramen = Integer.parseInt(st.nextToken());
			list.add(new Test(deadline, ramen));
		}

		Collections.sort(list, new Comparator<Test>() {
			@Override
			public int compare(Test e1, Test e2) {
				return e1.deadline - e2.deadline;
			}
		});

		int last_day = list.get(list.size() - 1).deadline;
		int idx = list.size() - 1;
		int count = 0;
		for (int i = last_day; i >= 1; i--) {
			while (idx >= 0 && list.get(idx).deadline >= i) {
				PQ.add(new Test(list.get(idx).deadline, list.get(idx).ramen));
				idx--;
			}
			if (!PQ.isEmpty()) {
				Test now = PQ.poll();
				count += now.ramen;
			}
		}
		System.out.println(count);

	}// main

	static class Test {
		int deadline, ramen;

		public Test(int deadline, int ramen) {
			this.deadline = deadline;
			this.ramen = ramen;
		}

	}
}
