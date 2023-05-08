import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Pattern P = Pattern.compile("(100+1+|01)+");

        int tc = Integer.parseInt(br.readLine());

        while (tc-- > 0) {
            if (P.matcher(br.readLine()).matches()) sb.append("YES\n");
            else sb.append("NO\n");
        }

        System.out.println(sb);
    }
}