import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int e = Integer.parseInt(br.readLine());

        int[] in_degree = new int[e + 1];
        int[][] list = new int[e + 1][2];

        for (int i = 1; i <= e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            in_degree[a]++;
            in_degree[b]++;
            list[i][0] = a;
            list[i][1] = b;
        }

        HashSet<Integer> set = new HashSet<>();

        // BFS를 활용해서 위상정렬을 출력해주자.
        Queue<Integer> Q = new LinkedList<>();
        for (int i = 1; i <= e; i++) {
            if (in_degree[i] < 2) {
                set.add(i);
                Q.add(i);
            }
        }

        while (!Q.isEmpty()) {
            int V = Q.poll();
            for (int i = 0; i < 2; i++) {
                in_degree[list[V][i]]--;
                if (in_degree[list[V][i]] < 2 && !set.contains(list[V][i])) {
                    Q.add(list[V][i]);
                    set.add(list[V][i]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int ans = 0;
        for (int i = 0; i <= e; i++) {
            if (in_degree[i] == 2) {
                ans++;
                sb.append(i + " ");
            }
        }
        System.out.println(ans);
        System.out.println(sb.toString());
    }// main

}
