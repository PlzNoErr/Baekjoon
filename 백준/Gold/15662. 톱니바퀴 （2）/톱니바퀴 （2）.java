import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num_gear = Integer.parseInt(br.readLine());
		ArrayList<LinkedList<Integer>> list = new ArrayList<>();
		for (int i = 0; i <= num_gear; i++) {
			list.add(new LinkedList<>());
		}
		for (int i = 1; i <= num_gear; i++) {
			String s = br.readLine();
			for (int j = 0; j < 8; j++) {
				list.get(i).add(s.charAt(j) - '0');
			}
		} // 톱니바퀴 정보 삽입
			// 0 = 12시, 2 = 3시, 6 = 9시
		int try_num = Integer.parseInt(br.readLine());
		for (int i = 0; i < try_num; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int gear_num = Integer.parseInt(st.nextToken());
			int direction = Integer.parseInt(st.nextToken());

			int now_dir = direction;
			Queue<int[]> Q = new LinkedList<>();
			Q.add(new int[] { gear_num, direction });
			for (int left = gear_num; 1 < left; left--) {
				if (list.get(left - 1).get(2) != list.get(left).get(6)) {
					Q.add(new int[] { left - 1, now_dir * (-1) });
					now_dir *= -1;
				} else
					break;
			}

			now_dir = direction;
			for (int right = gear_num; right < num_gear; right++) {
				if (list.get(right).get(2) != list.get(right + 1).get(6)) {
					Q.add(new int[] { right + 1, now_dir * (-1) });
					now_dir *= -1;
				} else
					break;
			}

			while (!Q.isEmpty()) {
				int[] info = Q.poll();
				if (info[1] == 1)
					list.get(info[0]).addFirst(list.get(info[0]).pollLast());
				if (info[1] == -1)
					list.get(info[0]).addLast(list.get(info[0]).pollFirst());
			}
		}

		// S극 count
		int result = 0;
		for (int i = 1; i <= num_gear; i++) {
			if (list.get(i).get(0) == 1)
				result++;
		}
		System.out.println(result);

	}// main
}
