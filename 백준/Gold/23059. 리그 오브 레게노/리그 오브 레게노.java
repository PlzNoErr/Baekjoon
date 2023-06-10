import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        HashMap<String, TreeSet<String>> map = new HashMap<String, TreeSet<String>>();
        HashMap<String, Integer> degree = new HashMap<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String preItem = st.nextToken();
            String followItem = st.nextToken();

            if (!map.containsKey(preItem)) map.put(preItem, new TreeSet<>());
            map.get(preItem).add(followItem);

            if (!degree.containsKey(preItem)) degree.put(preItem, 0);
            if (!degree.containsKey(followItem)) degree.put(followItem, 0);
            degree.put(followItem, degree.get(followItem) + 1);
        }

        // BFS를 활용해서 위상정렬을 출력해주자.
        LinkedList<String> Q = new LinkedList<>();
        PriorityQueue<String> PQ = new PriorityQueue<>((s1, s2) -> s1.compareTo(s2));

        // 필요 아이탬 개수가 0인 정점들을 먼저 Q에 집어 넣는다.
        String[] list = new String[degree.size()];
        degree.keySet().toArray(list);
        for (int i = 0; i < list.length; i++) {
            if (degree.get(list[i]) == 0) {
                Q.add(list[i]);
                degree.remove(list[i]);
            }
        }
        Collections.sort(Q);
        while (true) {
            while (!Q.isEmpty()) {
                String item = Q.poll();
                sb.append(item + "\n");
                if (map.get(item) == null) continue;
                String[] needItem = new String[map.get(item).size()];
                map.get(item).toArray(needItem);
                for (int i = 0; i < needItem.length; i++) {
                    degree.replace(needItem[i], degree.get(needItem[i]) - 1);
                    if (degree.get(needItem[i]) == 0) {
                        PQ.add(needItem[i]);
                        degree.remove(needItem[i]);
                    }
                }
            }
            if (PQ.isEmpty()) break;
            while (!PQ.isEmpty()) Q.add(PQ.poll());
        }
        System.out.println(degree.size() == 0 ? sb.toString() : -1);
    }//
}