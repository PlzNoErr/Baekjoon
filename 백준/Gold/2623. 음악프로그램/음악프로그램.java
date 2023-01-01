import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int v = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= v; i++) {
            list.add(new ArrayList<>());
        }
        int[] in_degree = new int[v + 1];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int before = Integer.parseInt(st.nextToken());
            for (int j = 1; j < num; j++) {
                int singer = Integer.parseInt(st.nextToken());
                list.get(before).add(singer);
                in_degree[singer]++;
                before = singer;
            }
        }

        // BFS를 활용해서 위상정렬을 출력해주자.
        Queue<Integer> Q = new LinkedList<>();
        // in_degree의 개수가 0인 정점들을 먼저 Q에 집어 넣는다.
        for (int i = 1; i <= v; i++) {
            if (in_degree[i] == 0) {
                Q.add(i);
            }
        }

        // in_degree가 0인 친구들은 이제 업무를 수행해도 된다는 뜻이다.
        // 아직 0이 안된 친구들은 선행 경로가 끝날때 까지 Q에 넣지 않는다.
        int check = 0;
        while (!Q.isEmpty()) {
            int V = Q.poll();
            if (in_degree[V] == 0) {
                sb.append(V + "\n");
                check++;
                for (int vt : list.get(V)) {
                    in_degree[vt]--;
                    if (in_degree[vt] == 0)
                        Q.add(vt);
                }
            }
        }
        System.out.print(check == v ? sb.toString() : 0);
    }//
}