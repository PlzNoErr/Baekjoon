import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s, ":");
        StringBuilder sb = new StringBuilder();
        if (st.countTokens() != 8) {
            int n = 8 - st.countTokens();
            String input = "";
            while (n-- > 0) {
                input += ":0:";
            }
            s = s.replace("::", input);
            st = new StringTokenizer(s, ":");
        }

        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (token.length() == 1) sb.append("000" + token + ":");
            else if (token.length() == 2) sb.append("00" + token + ":");
            else if (token.length() == 3) sb.append("0" + token + ":");
            else sb.append(token + ":");
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb.toString());

    }//
}