import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (tc-- > 0) {
            String s = br.readLine();
            int len = Integer.parseInt(br.readLine());
            int[] count = new int[26];

            ArrayList<ArrayList<Integer>> list = new ArrayList<>();
            for (int i = 0; i < 26; i++) {
                list.add(new ArrayList<>());
            }

            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;

            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                count[ch - 'a']++;
                list.get(ch - 'a').add(i);
            }

            for (int i = 0; i < 26; i++) {
                if (count[i] >= len) {
                    for (int j = 0; j <= list.get(i).size() - len; j++) {
                        min = Math.min(min, list.get(i).get(j + len - 1) - list.get(i).get(j) + 1);
                        max = Math.max(max, list.get(i).get(j + len - 1) - list.get(i).get(j) + 1);
                    }
                }
            }

            if (min == Integer.MAX_VALUE) {
                sb.append(-1 + "\n");
            } else {
                sb.append(min + " " + max + "\n");
            }
        }
        System.out.println(sb.toString());
    }
}