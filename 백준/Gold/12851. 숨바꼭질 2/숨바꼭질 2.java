import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        boolean[] visit = new boolean[200000];
        int time = Integer.MAX_VALUE;
        int number = 0;
        LinkedList<int[]> DQ = new LinkedList<>();
        DQ.add(new int[]{N, 0});
        visit[N] = true;

        while (!DQ.isEmpty()) {
            int[] now = DQ.poll();

            if (now[0] == K) {
                if (now[1] < time) {
                    time = now[1];
                    number = 1;
                    continue;
                } else if (now[1] == time) {
                    number++;
                    continue;
                }
            }

            if (now[1] > time) continue;

            visit[now[0]] = true;

            if (now[0] < K) {
                if (!visit[now[0] + 1]) {
                    DQ.add(new int[]{now[0] + 1, now[1] + 1});
                }
                if (2 * now[0] <= 2 * K) {
                    DQ.add(new int[]{now[0] * 2, now[1] + 1});
                }
            }

            if (now[0] - 1 >= 0 && !visit[now[0] - 1]) {
                DQ.add(new int[]{now[0] - 1, now[1] + 1});
            }
        }//
        System.out.println(time);
        System.out.println(number);
    }
}