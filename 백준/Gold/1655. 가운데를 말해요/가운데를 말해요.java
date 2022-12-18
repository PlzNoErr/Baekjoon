import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Integer> PQ_max = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> PQ_min = new PriorityQueue<>();
		int n = Integer.parseInt(br.readLine());
		while (n-- > 0) {
			int x = Integer.parseInt(br.readLine());
			if (PQ_max.size() == PQ_min.size()) {
				PQ_max.add(x);
				if (!PQ_min.isEmpty() && PQ_max.peek() > PQ_min.peek()) {
					PQ_min.add(PQ_max.poll());
					PQ_max.add(PQ_min.poll());
				}
			} else {
				PQ_min.add(x);
				if (PQ_max.peek() > PQ_min.peek()) {
					PQ_min.add(PQ_max.poll());
					PQ_max.add(PQ_min.poll());
				}
			}
			sb.append(PQ_max.peek()+"\n");

		} // while
		System.out.println(sb.toString());
	}// main

}