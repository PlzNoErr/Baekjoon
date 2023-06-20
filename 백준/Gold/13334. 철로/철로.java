import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Info> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.add(new Info(Math.min(a, b), Math.max(a, b)));
        }
        int d = Integer.parseInt(br.readLine());
        Collections.sort(list, (i1, i2) -> i1.right == i2.right ? i1.left - i2.left : i1.right - i2.right);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int answer = 0;
        for (int i = 0; i < N; i++) {
            Info info = list.get(i);
            if (info.right - info.left > d) continue;
            pq.add(info.left);
            while (!pq.isEmpty()) {
                if (info.right - pq.peek() <= d) break;
                pq.poll();
            }
            answer = Math.max(answer, pq.size());
        }
        System.out.println(answer);
    }

    static class Info {
        int left, right;

        public Info(int l, int r) {
            left = l;
            right = r;
        }
    }
}