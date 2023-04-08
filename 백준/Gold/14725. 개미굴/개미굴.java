import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        StringTokenizer st;
        Node root = new Node();
        while (tc-- > 0) {
            st = new StringTokenizer(br.readLine());
            int depth = Integer.parseInt(st.nextToken());
            Node cur = root;

            for (int j = 0; j < depth; j++) {
                String s = st.nextToken();

                if (!cur.child.containsKey(s)) {
                    cur.child.put(s, new Node());
                }
                cur = cur.child.get(s);
            }
        }
        sb = new StringBuilder();
        print(root, "");
        System.out.println(sb);
    }

    public static void print(Node root, String bar) {
        Object[] key = root.child.keySet().toArray();
        Arrays.sort(key);

        for (Object s : key) {
            sb.append(bar + s + "\n");
            print(root.child.get(s), bar + "--");
        }
    }

    static class Node {
        HashMap<String, Node> child = new HashMap<>();
    }
}
