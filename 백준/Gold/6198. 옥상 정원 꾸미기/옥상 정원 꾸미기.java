import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        long ans = 0;
        while (n-- > 0) {
            int height = Integer.parseInt(br.readLine());

            if (stack.isEmpty()) {
                stack.add(height);
                continue;
            }

            if (stack.peek() > height) {
                ans += stack.size();
                stack.add(height);
                continue;
            }

            while (!stack.isEmpty() && stack.peek() <= height) {
                stack.pop();
            }

            if (!stack.isEmpty()) {
                ans += stack.size();
            }
            stack.add(height);
        }//
        System.out.println(ans);
    }
}
