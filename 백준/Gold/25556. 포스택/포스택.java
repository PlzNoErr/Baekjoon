import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());
        Stack<Integer>[] list = new Stack[4];
        for (int i = 0; i < 4; i++) {
            list[i] = new Stack<>();
            list[i].push(0);
        }
        boolean check = true;
        here:
        for (int i = 0; i < n; i++) {
            if (!check) break;
            for (Stack stack : list) {
                if ((int) stack.peek() < arr[i]) {
                    stack.push(arr[i]);
                    continue here;
                }
            }
            check = false;
        }
        System.out.println(check ? "YES" : "NO");
    }
}
