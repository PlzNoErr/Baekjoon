import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        PriorityQueue<Integer> PQ_max;
        PriorityQueue<Integer> PQ_min;
        int tc = Integer.parseInt(br.readLine());
        while (tc-- > 0) {
            int n = Integer.parseInt(br.readLine());
            sb.append(n / 2 + 1 + "\n");
            st = new StringTokenizer(br.readLine());
            PQ_max = new PriorityQueue<>(Collections.reverseOrder());
            PQ_min = new PriorityQueue<>();
            int now = 0;
            int check = 0;
            while (n-- > 0) {
                if (!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                now++;
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
                if (now % 2 == 1) {
                    check++;
                    if (check % 10 == 1 && now == 1) sb.append(PQ_max.peek() + " ");
                    else if (check % 10 == 1) sb.append("\n" + PQ_max.peek() + " ");
                    else sb.append(PQ_max.peek() + " ");
                }
            }// while
            sb.append("\n");
        }// tc
        System.out.println(sb.toString());

    }//
}