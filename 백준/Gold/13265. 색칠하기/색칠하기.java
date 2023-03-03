import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<ArrayList<Integer>> list;
    static boolean findAns;

    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        sb = new StringBuilder();
        StringTokenizer st;
        while (tc-- > 0) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int line = Integer.parseInt(st.nextToken());
            boolean[] check = new boolean[num + 1];
            int[] color = new int[num + 1];
            list = new ArrayList<>();
            findAns = false;
            for (int i = 0; i <= num; i++) list.add(new ArrayList<>());
            for (int i = 0; i < line; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                list.get(a).add(b);
                list.get(b).add(a);
            }
            for (int i = 1; i <= num; i++) {
                if (!check[i]) dfs(check, color, i);
            }
            if (!findAns) sb.append("possible" + "\n");
        }//
        System.out.println(sb.toString());
    }

    static void dfs(boolean[] check, int[] color, int vt) {
        for (int go : list.get(vt)) {
            if (findAns) return;
            if (!check[go]) {
                check[go] = true;
                color[go] = color[vt] == 1 ? 2 : 1;
                dfs(check, color, go);
            } else {
                if (color[vt] == color[go]) {
                    findAns = true;
                    sb.append("impossible" + "\n");
                }
            }
        }
    }
}