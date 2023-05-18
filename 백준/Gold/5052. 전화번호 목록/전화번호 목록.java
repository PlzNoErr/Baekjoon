import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        here:
        while (tc-- > 0) {
            int n = Integer.parseInt(br.readLine());
            HashSet<String> set = new HashSet<>();
            String[] arr = new String[n];
            for (int i = 0; i < n; i++) {
                arr[i] = br.readLine();
                set.add(arr[i]);
            }
            for (int i = 0; i < n; i++) {
                String s = "";
                for (int j = 0; j < arr[i].length() - 1; j++) {
                    s += arr[i].charAt(j);
                    if (set.contains(s)) {
                        System.out.println("NO");
                        continue here;
                    }
                }
            }
            System.out.println("YES");
        }//
    }
}
