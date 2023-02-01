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
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        int[] in_degree = new int[v + 1];

        // 간선의 정보를 입력할 list.
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= v; i++) {
            list.add(new ArrayList<>());
        }
        // 작업하는데 걸리는 시간, 쏘는 간선의 개수를 체크해서 list에 넣어준다.
        for (int i = 1; i <= e; i++) {
            st = new StringTokenizer(br.readLine());
            int required = Integer.parseInt(st.nextToken());
            int lec = Integer.parseInt(st.nextToken());
            in_degree[lec]++;
            list.get(required).add(lec);
        }

        // BFS를 활용해서 위상정렬을 출력해주자.
        Queue<Integer> Q = new LinkedList<>();
        int time = 1;
        // in_degree의 개수가 0인 정점들을 먼저 PQ에 집어 넣는다.
        // 동시에 처음 시작하는 작업들의 시간도 입력한다.
        int[] process = new int[v + 1];
        for (int i = 1; i <= v; i++) {
            if (in_degree[i] == 0) {
                Q.add(i);
                process[i] = time;
            }
        }
        // in_degree가 0인 친구들은 이제 업무를 수행해도 된다는 뜻이다.
        // 아직 0이 안된 친구들은 선행 경로가 끝날때 까지 Q에 넣지 않는다.
        // 들어오는 정점이 0이라서 업무가 수행가능한 친구들 중에서 가능한 번호가 작은 수부터 내보낸다.
        while (!Q.isEmpty()) {
            int size = Q.size();
            time++;
            for (int k = 0; k < size; k++) {
                int V = Q.poll();
                for (int i = 0; i < list.get(V).size(); i++) {
                    in_degree[list.get(V).get(i)]--;
                    if (in_degree[list.get(V).get(i)] == 0) {
                        Q.add(list.get(V).get(i));
                        process[list.get(V).get(i)] = time;
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= v; i++) {
            sb.append(process[i] + " ");
        }
        System.out.println(sb.toString());
    }// main

}