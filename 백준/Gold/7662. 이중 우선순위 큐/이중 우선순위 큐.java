import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        while (tc-- > 0) {
            int n = Integer.parseInt(br.readLine());
            TreeMap<Integer, Integer> map = new TreeMap<>();

            while (n-- > 0) {
                st = new StringTokenizer(br.readLine());
                String s = st.nextToken();
                int k = Integer.parseInt(st.nextToken());
                
                // I 연산이면 삽입하고 끝냄
                if (s.equals("I")) {
                    map.put(k, map.getOrDefault(k, 0) + 1);
                } else {
                    if (map.size() == 0) continue;
                    int num = k == 1 ? map.lastKey() : map.firstKey();
                    if (map.get(num) == 1) map.remove(num);
                    else map.put(num, map.get(num) - 1);
                }

            }
            if (map.size() == 0) sb.append("EMPTY" + "\n");
            else sb.append(map.lastKey() + " " + map.firstKey() + "\n");
        }//
        System.out.println(sb.toString());
    }
}
