import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = br.readLine().split("");
        Stack<String> stack = new Stack<String>();
        for(int i = 0; i < arr.length; i++){
            if(!arr[i].equals(")")) {stack.push(arr[i]);}
            else {
                int cnt = 0;
                while (!stack.peek().equals("(")) {
                    String topStr = stack.pop();
                    if (topStr.equals("+")) {
                        int len = Integer.parseInt(stack.pop());
                        cnt += len;
                    } else cnt++;
                }
                stack.pop(); 
                int k = Integer.parseInt(stack.pop());
                cnt = cnt * k;
                stack.push(String.valueOf(cnt));
                stack.push("+");
            }
     }
        int answer = 0;
        while(!stack.empty()){
            if(stack.peek().equals("+")){
                stack.pop();
                answer += Integer.parseInt(stack.pop());
            }
            else{
                stack.pop();
                answer++;
            }
        }
        System.out.println(answer);
    }
}