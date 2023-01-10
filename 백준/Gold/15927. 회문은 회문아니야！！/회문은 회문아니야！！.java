import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        // 모두가 같은 문자인지 확인
        int i = 0;
        for (i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) != s.charAt(i + 1)) break;
        }
        if (i == s.length() - 1 && s.charAt(0) == s.charAt(s.length() - 1)) {
            System.out.println(-1);
            return;
        }

        int low = 0;
        int high = s.length() - 1;
        boolean check = true;
        while (low < high) {
            if (s.charAt(low) == s.charAt(high)) {
                low++;
                high--;
            } else {
                check = false;
                break;
            }
        }
        if (!check) System.out.println(s.length());
        else System.out.println(s.length() - 1);

    }//
}