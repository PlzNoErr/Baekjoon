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
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        // 정점의 개수가 주어진다.
        int v = Integer.parseInt(br.readLine());

        // 간선의 정보를 입력할 list.
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= v; i++) {
            list.add(new ArrayList<>());
        }

        // 위상정렬에서 "들어오는 간선"의 개수를 기록해준다.
        // 정점별로 소요 시간이 있으니 이것도 기록한다.
        int[] in_degree = new int[v + 1];
        int[] time = new int[v + 1];

        // 비용을 기록해 준 뒤 사전작업이 필요한 횟수를 in_degree에 넣어준다.
        for (int i = 1; i <= v; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            while (true) {
                int pre_work = Integer.parseInt(st.nextToken());
                if (pre_work == -1) break;
                list.get(pre_work).add(i);
                in_degree[i]++;
            }
        }

        // BFS를 활용해서 위상정렬을 출력해주자.
        Queue<Integer> Q = new LinkedList<>();
        // in_degree의 개수가 0인 정점들을 먼저 Q에 집어 넣는다.
        // 동시에 처음 시작하는 작업들의 시간도 입력한다.
        int[] process = new int[v + 1];
        for (int i = 1; i <= v; i++) {
            if (in_degree[i] == 0) {
                Q.add(i);
                process[i] = time[i];
            }
        }

        // in_degree가 0인 친구들은 이제 업무를 수행해도 된다는 뜻이다.
        // 아직 0이 안된 친구들은 선행 경로가 끝날때 까지 Q에 넣지 않는다.
        // 들어오는 정점이 0이라서 업무가 수행가능한 친구들은 Q에 넣되; process시간을 최대 시간으로 계속 업데이트 해준다.
        while (!Q.isEmpty()) {
            int V = Q.poll();
            if (in_degree[V] == 0)
                for (int i = 0; i < list.get(V).size(); i++) {
                    in_degree[list.get(V).get(i)]--;
                    if (process[list.get(V).get(i)] < process[V] + time[list.get(V).get(i)]) {
                        process[list.get(V).get(i)] = process[V] + time[list.get(V).get(i)];
                    }
                    if (in_degree[list.get(V).get(i)] == 0)
                        Q.add(list.get(V).get(i));
                }
        }

        for (int i = 1; i <= v; i++) {
            sb.append(process[i] + "\n");
        }

        System.out.println(sb.toString());
    }//
}