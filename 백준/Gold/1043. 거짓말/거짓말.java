import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList());
        }
        int party_num = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int true_num = Integer.parseInt(st.nextToken());
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < true_num; i++) {
            set.add(Integer.parseInt(st.nextToken()));
        }

        int[][] party = new int[party_num][];
        boolean[] visit = new boolean[party_num];
        for (int i = 0; i < party_num; i++) {
            st = new StringTokenizer(br.readLine());
            party[i] = new int[Integer.parseInt(st.nextToken())];
            for (int j = 0; j < party[i].length; j++) {
                party[i][j] = Integer.parseInt(st.nextToken());
                list.get(party[i][j]).add(i);
            }
        }
        Queue<Integer> Q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (set.contains(i)) {
                for (int j = 0; j < list.get(i).size(); j++) {
                    visit[list.get(i).get(j)] = true;
                    Q.add(list.get(i).get(j));
                }
            }
        }

        while (!Q.isEmpty()) {
            int par = Q.poll();
            for (int i = 0; i < party[par].length; i++) {
                set.add(party[par][i]);
                for (int j = 0; j < list.get(party[par][i]).size(); j++) {
                    if (!visit[list.get(party[par][i]).get(j)]) {
                        visit[list.get(party[par][i]).get(j)] = true;
                        Q.add(list.get(party[par][i]).get(j));
                    }
                }
            }

        }
        int ans = 0;
        for (int i = 0; i < party_num; i++) {
            if (!set.contains(party[i][0])) ans++;
        }
        System.out.println(ans);

    }
}