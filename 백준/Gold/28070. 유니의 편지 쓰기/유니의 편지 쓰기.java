import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        int[] arr = new int[999914];
        StringTokenizer st;
        while (tc-- > 0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken().replace("-", ""));
            int end = Integer.parseInt(st.nextToken().replace("-", ""));
            arr[start]++;
            arr[end + 1]--;
        }//
        int max = 0;
        int date = 0;
        for (int i = 1; i < 999914; i++) {
            arr[i] += arr[i - 1];
            if (arr[i] > max) {
                max = arr[i];
                date = i;
            }
        }
        String ans = date + "";
        StringBuilder sb = new StringBuilder();
        sb.append(ans.charAt(0) + "" + ans.charAt(1) + "" + ans.charAt(2) + "" + ans.charAt(3) + "-");
        sb.append(ans.charAt(4) + "" + ans.charAt(5));
        System.out.println(sb.toString());
    }
}
