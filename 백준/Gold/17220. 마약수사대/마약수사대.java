import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int v, e, count;
    static ArrayList<ArrayList<Integer>> graph;
    static boolean[] check;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        int[] Arr = new int[v];
        graph = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < e; i++) {
            String s = br.readLine();
            int x = s.charAt(0) - 'A';
            int y = s.charAt(2) - 'A';
            Arr[y]++;
            graph.get(x).add(y);
        }

        check = new boolean[v];
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            check[st.nextToken().charAt(0) - 'A'] = true;
        }

        count = 0;
        for (int i = 0; i < v; i++) {
            if (Arr[i] == 0 && !check[i]) dfs(i);
        }

        System.out.println(count);
    }

    static void dfs(int V) {
        for (int child : graph.get(V)) {
            if (!check[child]) {
                check[child] = true;
                count++;
                dfs(child);
            }
        }
    }//

}//